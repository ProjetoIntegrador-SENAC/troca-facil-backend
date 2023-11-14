package br.com.trocafacil.ems.apps.trade.controller;

import br.com.trocafacil.ems.apps.main.repository.AccountRepository;
import br.com.trocafacil.ems.apps.main.repository.ProductRepository;
import br.com.trocafacil.ems.apps.trade.repository.TradeRepository;
import br.com.trocafacil.ems.apps.trade.service.TradeService;
import br.com.trocafacil.ems.domain.model.account.Account;
import br.com.trocafacil.ems.domain.model.account.User;
import br.com.trocafacil.ems.domain.model.product.Product;
import br.com.trocafacil.ems.domain.model.trade.Trade;
import br.com.trocafacil.ems.domain.model.trade.dto.TradeCreateDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
    @Transactional
    public ResponseEntity<Trade> open(@RequestBody @Valid TradeCreateDto tradeDto, @AuthenticationPrincipal User user){
        Trade trade = tradeService.create(tradeDto, user);
        return ResponseEntity.ok(trade);
    }



    //TODO GET FAZER OFERTA POR PRODUTO
        //TODO POST
    //TODO GET CANCELAR PROPOSTA {ID_TRADE}

    @GetMapping("/delete/{id}")
    public ResponseEntity<Trade> delete(@PathVariable Long id){
        try{
            tradeRepository.deleteById(id);
        }catch (Exception exception){
            throw new EntityNotFoundException("Negociação não encontrada");
        }
        return ResponseEntity.noContent().build();

    }

    //TODO GET ACEITAR TRADE {TRADE_ID, DATA_FECHAMENTO(DT_REQUISICAO)} --> ALTERAR OUTRAS PROPOSTAS PARA RECUSADAS.
        //TODO ATUALIZAR TABELA DELIVERY
        //TODO SETAR DEMAIS TRADES PARA STATUS CANCELADO/RECUSADO

    @GetMapping("/accept/{id}")
    public ResponseEntity<Void> acceptTrade(@PathVariable Long id){
        tradeService.acceptTrade(id);
        return ResponseEntity.ok().build();
    }


}
