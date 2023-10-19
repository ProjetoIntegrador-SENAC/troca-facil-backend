package br.com.trocafacil.ems.controller;

import br.com.trocafacil.ems.domain.entity.Item;
import br.com.trocafacil.ems.domain.repository.ItemRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/ems")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @PostMapping
    public ResponseEntity<Item> save(@RequestBody @Valid  Item item){
        Item nItem = itemRepository.save(item);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(nItem.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<Item>> readAll(){
        List<Item> items = itemRepository.findAll();
        return ResponseEntity.ok(items);
    }




}
