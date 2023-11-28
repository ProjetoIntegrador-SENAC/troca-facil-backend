package br.com.trocafacil.ems.domain.model.trade.request;

import br.com.trocafacil.ems.domain.helpers.enums.Status;
import br.com.trocafacil.ems.domain.model.product.Product;
import br.com.trocafacil.ems.domain.model.trade.Trade;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public  record TradeCreateRequest(

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
