package br.com.trocafacil.ems.domain.model.trade.dto;

import br.com.trocafacil.ems.domain.helpers.enums.Status;
import br.com.trocafacil.ems.domain.model.product.Product;
import br.com.trocafacil.ems.domain.model.trade.Trade;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public  record TradeCreateDto(

        @NotNull
        Long productPostedId,
        @NotNull
        Long productProposalId
) {

        public Trade createTrade(Product productProposal, Product productPosted){
                Trade trade = new Trade();
                trade.setCreateDate(LocalDate.now());
                trade.setProductPosted(productPosted);
                trade.setProductProposal(productProposal);
                trade.setStatus(Status.EM_NEGOCIACAO);
                return trade;
        }

}
