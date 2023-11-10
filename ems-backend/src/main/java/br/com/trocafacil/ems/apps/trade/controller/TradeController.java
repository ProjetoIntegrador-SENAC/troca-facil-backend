package br.com.trocafacil.ems.apps.trade.controller;

import br.com.trocafacil.ems.apps.trade.repository.TradeRepository;
import br.com.trocafacil.ems.domain.model.trade.Trade;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("trade")
public class TradeController {

    @Autowired
    private TradeRepository tradeRepository;

    //TODO POST ABRIR NEGOCIACAO {DATA_ABERTURA(DT_REQUISICAO), PRODUTO_ALVO, PRODUTO_TROCA, STATUS_INICIAL}

    @PostMapping("/create")
    public ResponseEntity<Trade> open(@RequestBody @Valid Trade trade){
        Trade trade1 = tradeRepository.save(trade);
        return ResponseEntity.ok(trade1);
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

    /*@GetMapping("/accept")
    public ResponseEntity<Trade> accept(){
        // TODO --- INCLUIR VALIDAÇÃO SE USUÁRIO PODE ACEITAR. MODIFICAR O STATUS DA REQUISICAO
    } */


}
