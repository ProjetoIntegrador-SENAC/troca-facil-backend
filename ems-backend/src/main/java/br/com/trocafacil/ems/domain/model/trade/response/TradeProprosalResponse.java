package br.com.trocafacil.ems.domain.model.trade.response;

import br.com.trocafacil.ems.domain.helpers.enums.Status;
import br.com.trocafacil.ems.domain.model.product.dto.ProductPhotoDto;
import br.com.trocafacil.ems.domain.model.trade.Trade;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TradeProprosalResponse {
    private Long id;
    private LocalDate createDate;
    private LocalDate settleDate;
    private ProductPhotoDto productPosted;
    private ProductPhotoDto productProposal;
    private Status status;


    public static TradeProprosalResponse of(Trade trade, String photoProductProposal, String photoOnwerProposal, String photoProductPosted, String photoOwnerPosted){
        TradeProprosalResponse td = new TradeProprosalResponse();
         td.setId(trade.getId());
         td.setCreateDate(trade.getCreateDate());
         td.setSettleDate(trade.getSettleDate());
         td.setProductProposal(new ProductPhotoDto(trade.getProductProposal(), photoProductProposal, photoOnwerProposal));
         td.setProductPosted(new ProductPhotoDto(trade.getProductPosted(), photoProductPosted, photoOwnerPosted));
         td.setStatus(trade.getStatus());

         return td;
    }
}
