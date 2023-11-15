package br.com.trocafacil.ems.domain.model.trade.dto;

import br.com.trocafacil.ems.domain.helpers.enums.DeliveryStatus;
import br.com.trocafacil.ems.domain.model.trade.Delivery;
import br.com.trocafacil.ems.domain.model.trade.Trade;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public record DeliveryDto (
        @NotNull Long tradeId,
        @NotNull String cep,
        @NotNull Integer number
)
{
    public Delivery createDelivery(Trade trade){
        Delivery delivery = new Delivery();
        delivery.setCep(this.cep);
        delivery.setNumber(this.number);
        delivery.setStatus(DeliveryStatus.AGUARDANDO_ENTREGA);
        return delivery;
    }
}
