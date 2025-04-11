package com.example.rpggustavo.Controller;


import com.example.rpggustavo.Model.ItemMagico;
import com.example.rpggustavo.repository.ItemMagicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens-magicos")
public class ItemMagicoController {

    @Autowired
    private ItemMagicoRepository repository;

    // Cadastrar Item Mágico
    @PostMapping
    public ResponseEntity<ItemMagico> cadastrarItemMagico(@RequestBody ItemMagico item) {
        if (item.getForca() == 0 && item.getDefesa() == 0) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(repository.save(item));
    }

    // Listar todos os Itens Mágicos
    @GetMapping
    public ResponseEntity<List<ItemMagico>> listarItensMagicos() {
        return ResponseEntity.ok(repository.findAll());
    }

    // Buscar Item Mágico por ID
    @GetMapping("/{id}")
    public ResponseEntity<ItemMagico> buscarItemMagicoPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}