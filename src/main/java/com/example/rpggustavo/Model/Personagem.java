package com.example.rpggustavo.Model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String nomeAventureiro;

    @Enumerated(EnumType.STRING)
    private Classe classe;
    private int level;

    @OneToMany(mappedBy = "personagem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<com.example.rpggustavo.Model.ItemMagico> itensMagicos = new ArrayList<>();

    private int forcaBase;
    private int defesaBase;

    public enum Classe {
        GUERREIRO, MAGO, ARQUEIRO, LADINO, BARDO
    }


    public Personagem() {}

    public Personagem(String nome, String nomeAventureiro, Classe classe, int level, int forcaBase, int defesaBase) {
        this.nome = nome;
        this.nomeAventureiro = nomeAventureiro;
        this.classe = classe;
        this.level = level;
        setForcaBase(forcaBase);
        setDefesaBase(defesaBase);
        validarDistribuicaoPontos();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeAventureiro() {
        return nomeAventureiro;
    }

    public void setNomeAventureiro(String nomeAventureiro) {
        this.nomeAventureiro = nomeAventureiro;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<com.example.rpggustavo.Model.ItemMagico> getItensMagicos() {
        return itensMagicos;
    }

    public void setItensMagicos(List<com.example.rpggustavo.Model.ItemMagico> itensMagicos) {
        this.itensMagicos = itensMagicos;
    }

    public int getForcaBase() {
        return forcaBase;
    }

    public void setForcaBase(int forcaBase) {
        if (forcaBase < 0 || forcaBase > 10) throw new IllegalArgumentException("Força base deve ser entre 0 e 10");
        this.forcaBase = forcaBase;
        validarDistribuicaoPontos();
    }

    public int getDefesaBase() {
        return defesaBase;
    }

    public void setDefesaBase(int defesaBase) {
        if (defesaBase < 0 || defesaBase > 10) throw new IllegalArgumentException("Defesa base deve ser entre 0 e 10");
        this.defesaBase = defesaBase;
        validarDistribuicaoPontos();
    }

    // Métodos para calcular força e defesa total
    public int getForcaTotal() {
        return forcaBase + itensMagicos.stream().mapToInt(com.example.rpggustavo.Model.ItemMagico::getForca).sum();
    }

    public int getDefesaTotal() {
        return defesaBase + itensMagicos.stream().mapToInt(com.example.rpggustavo.Model.ItemMagico::getDefesa).sum();
    }

    // Validação dos 10 pontos
    private void validarDistribuicaoPontos() {
        if (forcaBase + defesaBase > 10) {
            throw new IllegalArgumentException("A soma de Força e Defesa não pode exceder 10 pontos");
        }
    }

    // Método para adicionar item com validação de amuleto
    public void adicionarItemMagico(com.example.rpggustavo.Model.ItemMagico item) {
        if (item.getTipo() == com.example.rpggustavo.Model.ItemMagico.TipoItem.AMULETO) {
            long amuletos = itensMagicos.stream()
                    .filter(i -> i.getTipo() == com.example.rpggustavo.Model.ItemMagico.TipoItem.AMULETO)
                    .count();
            if (amuletos >= 1) {
                throw new IllegalStateException("Personagem já possui um amuleto");
            }
        }
        itensMagicos.add(item);
        item.setPersonagem(this);
    }
}