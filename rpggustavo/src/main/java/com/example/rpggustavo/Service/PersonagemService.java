package com.example.rpggustavo.Service;

import com.example.rpggustavo.Model.ItemMagico;
import com.example.rpggustavo.Model.Personagem;
import com.example.rpggustavo.repository.ItemMagicoRepository;
import com.example.rpggustavo.repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonagemService {

    @Autowired
    private PersonagemRepository personagemRepository;

    @Autowired
    private ItemMagicoRepository itemMagicoRepository;

    // Cadastrar Personagem
    public Personagem cadastrarPersonagem(Personagem personagem) {
        return personagemRepository.save(personagem);
    }

    // Listar todos os Personagens
    public List<Personagem> listarPersonagens() {
        return personagemRepository.findAll();
    }

    // Buscar Personagem por ID
    public Optional<Personagem> buscarPersonagemPorId(Long id) {
        return personagemRepository.findById(id);
    }

    // Atualizar Nome Aventureiro por ID
    public Personagem atualizarNomeAventureiro(Long id, String nomeAventureiro) {
        Personagem personagem = personagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));
        personagem.setNomeAventureiro(nomeAventureiro);
        return personagemRepository.save(personagem);
    }

    // Remover Personagem
    public void removerPersonagem(Long id) {
        personagemRepository.deleteById(id);
    }

    // Adicionar Item Mágico ao Personagem
    public ItemMagico adicionarItemMagico(Long personagemId, Long itemId) {
        Personagem personagem = personagemRepository.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));
        ItemMagico item = itemMagicoRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        personagem.adicionarItemMagico(item); // Usa o método da entidade com validação de amuleto
        personagemRepository.save(personagem);
        return item;
    }

    // Remover Item Mágico do Personagem
    public void removerItemMagico(Long personagemId, Long itemId) {
        Personagem personagem = personagemRepository.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));
        ItemMagico item = itemMagicoRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        personagem.getItensMagicos().remove(item);
        item.setPersonagem(null);
        personagemRepository.save(personagem);
    }

    // Listar Itens Mágicos por Personagem
    public List<ItemMagico> listarItensPorPersonagem(Long personagemId) {
        Personagem personagem = personagemRepository.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));
        return personagem.getItensMagicos();
    }

    // Buscar Amuleto do Personagem
    public Optional<ItemMagico> buscarAmuleto(Long personagemId) {
        Personagem personagem = personagemRepository.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));
        return personagem.getItensMagicos().stream()
                .filter(item -> item.getTipo() == ItemMagico.TipoItem.AMULETO)
                .findFirst();
    }
}