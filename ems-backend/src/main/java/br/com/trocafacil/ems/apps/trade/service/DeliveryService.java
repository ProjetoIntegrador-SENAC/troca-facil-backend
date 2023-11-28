package br.com.trocafacil.ems.apps.trade.service;

import br.com.trocafacil.ems.apps.main.repository.DeliveryRepository;
import br.com.trocafacil.ems.apps.trade.repository.TradeRepository;
import br.com.trocafacil.ems.domain.helpers.enums.DeliveryStatus;
import br.com.trocafacil.ems.domain.model.trade.Delivery;
import br.com.trocafacil.ems.domain.model.trade.Trade;
import br.com.trocafacil.ems.domain.model.trade.request.DeliveryRequest;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private TradeService tradeService;

    public Delivery create(DeliveryRequest deliveryRequest){
        Optional<Trade> tradeOptional = tradeRepository.findById(deliveryRequest.tradeId());

        if (tradeOptional.isEmpty()){
            throw new EntityNotFoundException();
        }
        Delivery delivery = deliveryRequest.createDelivery(tradeOptional.get());
        return deliveryRepository.save(delivery);
    }

    public Delivery concluded(Long id){
        Optional<Delivery> delivery = deliveryRepository.findById(id);
        if (delivery.isEmpty()){
            throw new EntityNotFoundException("Erro ao buscar o status da entrega");
        }
        delivery.get().setStatus(DeliveryStatus.ENTREGUE);

        Trade trade = delivery.get().getTrade();
        tradeService.conclude(trade);

        return deliveryRepository.save(delivery.get());
    }

}
