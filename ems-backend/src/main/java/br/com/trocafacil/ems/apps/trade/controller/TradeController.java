package br.com.trocafacil.ems.apps.trade.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("trade")
public class TradeController {

    //TODO POST ABRIR NEGOCIACAO {DATA_ABERTURA(DT_REQUISICAO), PRODUTO_ALVO, PRODUTO_TROCA, STATUS_INICIAL}
    //TODO GET FAZER OFERTA POR PRODUTO
        //TODO POST
    //TODO GET CANCELAR PROPOSTA {ID_TRADE}
    //TODO GET ACEITAR TRADE {TRADE_ID, DATA_FECHAMENTO(DT_REQUISICAO)} --> ALTERAR OUTRAS PROPOSTAS PARA RECUSADAS.
        //TODO ATUALIZAR TABELA DELIVERY
        //TODO SETAR DEMAIS TRADES PARA STATUS CANCELADO/RECUSADO
}
