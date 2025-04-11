package com.example.rpggustavo.Model;
import jakarta.persistence.*;

@Entity
public class ItemMagico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoItem tipo;

    private int forca;
    private int defesa;

    @ManyToOne
    @JoinColumn(name = "personagem_id")
    private Personagem personagem;

    public enum TipoItem {
        ARMA, ARMADURA, AMULETO
    }


    public ItemMagico() {}

    public ItemMagico(String nome, TipoItem tipo, int forca, int defesa) {
        this.nome = nome;
        this.tipo = tipo;
        setForca(forca); // Validação no setter
        setDefesa(defesa); // Validação no setter
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

    public TipoItem getTipo() {
        return tipo;
    }

    public void setTipo(TipoItem tipo) {
        this.tipo = tipo;
        if (tipo == TipoItem.ARMA) this.defesa = 0;
        if (tipo == TipoItem.ARMADURA) this.forca = 0;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        if (forca < 0 || forca > 10) throw new IllegalArgumentException("Força deve ser entre 0 e 10");
        if (tipo == TipoItem.ARMADURA && forca != 0) throw new IllegalArgumentException("Armadura não pode ter força");
        if (forca == 0 && defesa == 0) throw new IllegalArgumentException("Item não pode ter força e defesa zerados");
        this.forca = forca;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        if (defesa < 0 || defesa > 10) throw new IllegalArgumentException("Defesa deve ser entre 0 e 10");
        if (tipo == TipoItem.ARMA && defesa != 0) throw new IllegalArgumentException("Arma não pode ter defesa");
        if (forca == 0 && defesa == 0) throw new IllegalArgumentException("Item não pode ter força e defesa zerados");
        this.defesa = defesa;
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }
}