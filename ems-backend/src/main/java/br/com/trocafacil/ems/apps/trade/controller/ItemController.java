package br.com.trocafacil.ems.apps.trade.controller;

import br.com.trocafacil.ems.apps.trade.repository.ItemRepository;
import br.com.trocafacil.ems.domain.model.product.Product;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("ems")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @PostMapping("/item")
    public ResponseEntity<Product> save(@RequestBody @Valid Product item){
        Product nItem = itemRepository.save(item);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(nItem.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/item")
    public ResponseEntity<List<Product>> readAll(){
        List<Product> items = itemRepository.findAll();
        return ResponseEntity.ok(items);
    }

}
