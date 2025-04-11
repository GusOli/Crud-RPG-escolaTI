package com.example.rpggustavo.Controller;


import com.example.rpggustavo.Model.ItemMagico;
import com.example.rpggustavo.Model.Personagem;
import com.example.rpggustavo.Service.PersonagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personagens")
public class PersonagemController {

    @Autowired
    private PersonagemService service;

    // Cadastrar Personagem
    @PostMapping
    public ResponseEntity<Personagem> cadastrarPersonagem(@RequestBody Personagem personagem) {
        return ResponseEntity.ok(service.cadastrarPersonagem(personagem));
    }

    // Listar todos os Personagens
    @GetMapping
    public ResponseEntity<List<Personagem>> listarPersonagens() {
        return ResponseEntity.ok(service.listarPersonagens());
    }

    // Buscar Personagem por ID
    @GetMapping("/{id}")
    public ResponseEntity<Personagem> buscarPersonagemPorId(@PathVariable Long id) {
        return service.buscarPersonagemPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Atualizar Nome Aventureiro por ID
    @PutMapping("/{id}/nome-aventureiro")
    public ResponseEntity<Personagem> atualizarNomeAventureiro(@PathVariable Long id, @RequestBody String nomeAventureiro) {
        return ResponseEntity.ok(service.atualizarNomeAventureiro(id, nomeAventureiro));
    }

    // Remover Personagem
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPersonagem(@PathVariable Long id) {
        service.removerPersonagem(id);
        return ResponseEntity.noContent().build();
    }

    // Adicionar Item Mágico ao Personagem
    @PostMapping("/{personagemId}/itens/{itemId}")
    public ResponseEntity<ItemMagico> adicionarItemMagico(@PathVariable Long personagemId, @PathVariable Long itemId) {
        return ResponseEntity.ok(service.adicionarItemMagico(personagemId, itemId));
    }

    // Remover Item Mágico do Personagem
    @DeleteMapping("/{personagemId}/itens/{itemId}")
    public ResponseEntity<Void> removerItemMagico(@PathVariable Long personagemId, @PathVariable Long itemId) {
        service.removerItemMagico(personagemId, itemId);
        return ResponseEntity.noContent().build();
    }

    // Listar Itens Mágicos por Personagem
    @GetMapping("/{personagemId}/itens")
    public ResponseEntity<List<ItemMagico>> listarItensPorPersonagem(@PathVariable Long personagemId) {
        return ResponseEntity.ok(service.listarItensPorPersonagem(personagemId));
    }

    // Buscar Amuleto do Personagem
    @GetMapping("/{personagemId}/amuleto")
    public ResponseEntity<ItemMagico> buscarAmuleto(@PathVariable Long personagemId) {
        return service.buscarAmuleto(personagemId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}