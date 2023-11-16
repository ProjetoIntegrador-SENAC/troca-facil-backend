package br.com.trocafacil.ems.apps.trade.controller;

import br.com.trocafacil.ems.apps.trade.service.DeliveryService;
import br.com.trocafacil.ems.domain.model.trade.Delivery;
import br.com.trocafacil.ems.domain.model.trade.dto.DeliveryDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("delivery")
public class DeliveryController {

    // TODO POST CRIAR NOVA_ENTREGA PAYLOAD {CEP, NUMBER, }

    @Autowired
    private DeliveryService deliveryService;

    @PostMapping("/create")
    public ResponseEntity<Delivery> create(@Valid @RequestBody DeliveryDto deliveryDto){
        return ResponseEntity.ok(deliveryService.create(deliveryDto));
    }

    @PostMapping("/concluded/{id}")
    public ResponseEntity<Delivery> concluded(@Valid @PathVariable(name = "id") Long id){
        return ResponseEntity.ok(deliveryService.concluded(id));
    }




}
