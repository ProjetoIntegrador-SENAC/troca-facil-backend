package br.com.trocafacil.ems.apps.trade.controller;

import br.com.trocafacil.ems.apps.main.repository.AccountRepository;
import br.com.trocafacil.ems.apps.main.repository.ProductRepository;
import br.com.trocafacil.ems.apps.trade.repository.TradeRepository;
import br.com.trocafacil.ems.apps.trade.service.TradeService;
import br.com.trocafacil.ems.domain.helpers.enums.Status;
import br.com.trocafacil.ems.domain.model.account.User;
import br.com.trocafacil.ems.domain.model.trade.Trade;
import br.com.trocafacil.ems.domain.model.trade.response.AcceptTradeResponse;
import br.com.trocafacil.ems.domain.model.trade.request.TradeCreateRequest;
import br.com.trocafacil.ems.domain.model.trade.response.TradeProprosalResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("trade")
public class TradeController {

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TradeService tradeService;

    //TODO POST ABRIR NEGOCIACAO {DATA_ABERTURA(DT_REQUISICAO), PRODUTO_ALVO, PRODUTO_TROCA, STATUS_INICIAL}

    @PostMapping("/create")
    public ResponseEntity<Trade> open(@RequestBody @Valid TradeCreateRequest tradeDto, @AuthenticationPrincipal User user){
        Trade trade = tradeService.create(tradeDto, user);
        return ResponseEntity.ok(trade);
    }

    @GetMapping("/findProposals")
    public ResponseEntity<List<TradeProprosalResponse>> findProposals(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(tradeService.findProposals(user));
    }

    //TODO GET FAZER OFERTA POR PRODUTO
        //TODO POST
    //TODO GET CANCELAR PROPOSTA {ID_TRADE}

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Trade> delete(@PathVariable Long id){
        try{
            tradeRepository.deleteById(id);
        }catch (Exception exception){
            throw new EntityNotFoundException("Negociação não encontrada");
        }
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/accept/{id}")
    public ResponseEntity<AcceptTradeResponse> acceptTrade(@PathVariable Long id){
        var trade = tradeService.acceptTrade(id);
        return ResponseEntity.ok().body(trade);
    }

    @GetMapping("/cancelled/{id}")
    public ResponseEntity<Trade> cancelledTrade(@PathVariable Long id){
        Trade trade = tradeService.cancellTrade(id);
        return ResponseEntity.ok().body(trade);
    }

    @GetMapping("/amount/{status}")
    public Long a(@PathVariable(
            name = "status", required = false)
                      Status status){
        return tradeRepository.countDistinctByStatus(Status.CONCLUIDO);
    }
}
