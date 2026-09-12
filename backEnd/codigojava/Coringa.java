package backEnd.codigojava;

import java.util.ArrayList;

import backEnd.codigojava.EfeitoCoringa.ContextoCoringa;

public class Coringa {
    /* ------------------ VARIÁVEIS ------------------ */

    private int idJoker;
    private String nome;
    private String raridade;
    private String modificador = "Base";
    private float preco;
    private EfeitoCoringa efeitoCoringa;

    /* ------------------ CONSTRUTOR ------------------ */

    public Coringa(int idJoker, String nome, String raridade, String modificador, float preco,
            EfeitoCoringa efeitoCoringa) {
        this.idJoker = idJoker;
        this.nome = nome;
        this.raridade = raridade;
        this.modificador = modificador;
        this.preco = preco;
        this.efeitoCoringa = efeitoCoringa;
    }

    public Coringa(int idJoker, String nome, String raridade, float preco,
            EfeitoCoringa efeitoCoringa) {
        this.idJoker = idJoker;
        this.nome = nome;
        this.raridade = raridade;
        this.preco = preco;
        this.efeitoCoringa = efeitoCoringa;
    }

    /* ------------------ MéTODOS ------------------ */

    public ContextoCoringa realizarEfeitoCoringa(ContextoMao maoCarta){
        return efeitoCoringa.aplicarEfeito(maoCarta);
    }

    /* ------------------ GETTERS & SETTERS ------------------ */

    public int getIdJoker() {
        return idJoker;
    }

    public void setIdJoker(int idJoker) {
        this.idJoker = idJoker;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaridade() {
        return raridade;
    }

    public void setRaridade(String raridade) {
        this.raridade = raridade;
    }

    public String getModificador() {
        return modificador;
    }

    public void setModificador(String modificador) {
        this.modificador = modificador;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public EfeitoCoringa getEfeitoCoringa() {
        return efeitoCoringa;
    }

    public void setEfeitoCoringa(EfeitoCoringa efeitoCoringa) {
        this.efeitoCoringa = efeitoCoringa;
    }
}
