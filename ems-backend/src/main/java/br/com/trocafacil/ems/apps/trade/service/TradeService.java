package br.com.trocafacil.ems.apps.trade.service;

import br.com.trocafacil.ems.apps.main.repository.AccountRepository;
import br.com.trocafacil.ems.apps.main.repository.ProductRepository;
import br.com.trocafacil.ems.apps.main.service.AccountService;
import br.com.trocafacil.ems.apps.main.service.ProductService;
import br.com.trocafacil.ems.apps.trade.repository.TradeRepository;
import br.com.trocafacil.ems.domain.helpers.enums.ProductStatus;
import br.com.trocafacil.ems.domain.helpers.enums.Status;
import br.com.trocafacil.ems.domain.model.account.Account;
import br.com.trocafacil.ems.domain.model.account.User;
import br.com.trocafacil.ems.domain.model.product.Product;
import br.com.trocafacil.ems.domain.model.trade.Trade;
import br.com.trocafacil.ems.domain.model.trade.dto.TradeCreateDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TradeService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ProductService productService;

    @Transactional
    public Trade findById(Long id){
        var optional = tradeRepository.findById(id);
        if (optional.isEmpty()){
            throw new EntityNotFoundException();
        }
        return optional.get();
    }

    @Transactional
    public Trade acceptTrade(Long id){
        Trade trade = findById(id);
        trade.setStatus(Status.FECHADO);
        tradeRepository.save(trade);

        log.info("Trade has been changed to closed!!");
        closeTrades(trade);
        return trade;
    }

    @Transactional
    private void closeTrades(Trade trade){
        Product productPosted = trade.getProductPosted();
        Product productProprosal = trade.getProductProposal();

        productService.updateProductToExchanged(productPosted);
        productService.updateProductToExchanged(productProprosal);

        var trades = tradeRepository.findByProductPostedId(productPosted.getId());
        // List<Product> productsToUpdate = new ArrayList<>();
        log.info("Updating other trades with the same product target");
        for (Trade trade1 : trades) {
            if (trade1.getId() != trade.getId()){
                trade1.setStatus(Status.CANCELADO);
                tradeRepository.save(trade1);
               // productsToUpdate.add(trade1.getProductPosted());
            }
        }
        // productService.updateProductsToAvaliable(productsToUpdate);
    }

    @Transactional
    public Trade create(TradeCreateDto tradeDto, User user){
        Trade tradeVerify = getTradeByIds(tradeDto.productPostedId(), tradeDto.productProposalId());
        if(!(tradeVerify == null)){
            tradeVerify.setStatus(Status.EM_NEGOCIACAO);
            return tradeRepository.save(tradeVerify);
        }

        Account account = accountRepository.findByUserId(user.getId());
        Product productProposal = productRepository.findById(tradeDto.productProposalId()).orElseThrow();
        Product productPosted = productRepository.findById(tradeDto.productPostedId()).orElseThrow();

        if (!(productProposal.getAccount().getId() == account.getId())){
            throw new EntityNotFoundException();
        }

        Trade trade = tradeDto.createTrade(productProposal, productPosted);
        return tradeRepository.save(trade);
    }

    @Transactional
    public Trade getTradeByIds(Long productPostedId, Long productProposalId){
        Optional<Trade> trade = tradeRepository.findByProductPostedIdAndProductProposalIdOrProductPostedIdAndProductProposalId(
                productPostedId, productProposalId,
                productProposalId, productPostedId
        );

        if (trade.isPresent()){
            return trade.get();
        }

        return null;

    }

    @Transactional
    public Trade cancellTrade(Long id){
        Trade trade = findById(id);
        trade.setStatus(Status.CANCELADO);
        tradeRepository.save(trade);

        Product proprosal = trade.getProductProposal();
        productService.updateProductToAvaliable(proprosal);

        return trade;
    }

    @Transactional
    public void conclude(Trade trade){
        trade.getProductProposal().setStatus(ProductStatus.TROCADO);
        trade.getProductPosted().setStatus(ProductStatus.TROCADO);
        productRepository.save(trade.getProductProposal());
        productRepository.save(trade.getProductPosted());
    }

    public List<Trade> findProposals(User user) {
        Account account = accountService.getAccountByUserId(user.getId());
        List<Product> products = new ArrayList<>();
        List<Trade> totalTrades = new ArrayList<>();

        for (Product p : account.getProducts()){
            if (p.getTradesPosted().isEmpty()) continue;

            for (Trade trade : p.getTradesPosted()){
                if (trade.getStatus().equals(Status.EM_NEGOCIACAO)){
                    totalTrades.add(trade);
                }
            }
        }
        return totalTrades;
    }
}
