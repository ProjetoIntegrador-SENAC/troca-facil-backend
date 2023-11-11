package br.com.trocafacil.ems.apps.trade.service;

import br.com.trocafacil.ems.apps.main.service.ProductService;
import br.com.trocafacil.ems.apps.trade.repository.TradeRepository;
import br.com.trocafacil.ems.domain.helpers.enums.Status;
import br.com.trocafacil.ems.domain.model.product.Product;
import br.com.trocafacil.ems.domain.model.trade.Trade;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TradeService {

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private ProductService productService;

    public void acceptTrade(Long id){
        Optional<Trade> tradeOptional = tradeRepository.findById(id);

        if(tradeOptional.isEmpty()){
            throw new EntityNotFoundException();
        }

        Trade trade = tradeOptional.get();
        trade.setStatus(Status.FECHADO);
        tradeRepository.save(trade);

        log.info("Trade has been changed to closed!!");
        closeTrades(trade);

    }

    private void closeTrades(Trade trade){
        Product productPosted = trade.getProduct_posted();
        Product productProprosal = trade.getProduct_proposal();

        productService.updateProductToExchanged(productPosted);
        productService.updateProductToExchanged(productProprosal);

        var trades = tradeRepository.findByProduct_posted(productPosted);
        List<Product> productsToUpdate = new ArrayList<>();
        log.info("Updating other trades with the same product target");
        for (Trade trade1 : trades) {
            if (trade1.getId() != trade.getId()){
                trade1.setStatus(Status.CANCELADO);
                tradeRepository.save(trade1);
                productsToUpdate.add(trade1.getProduct_posted());
            }
        }
        productService.updateProductsToCancelled(productsToUpdate);
    }
}
