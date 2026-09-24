package backEnd.src.model.coringas;

import backEnd.src.Factory.*;
import backEnd.src.model.Efeitos.EfeitoCoringa;
import backEnd.src.model.contextos.ContextoMao;
import backEnd.src.model.enums.*;

public class Coringa {
    /* ------------------ VARIÁVEIS ------------------ */

    private int idJoker;
    private String nome;
    private Raridade raridade;
    private Modificador modificador;
    private Gatilho tempoDeAtivicao;
    private float preco;
    private EfeitoCoringa efeitoCoringa;

    /* ------------------ CONSTRUTOR ------------------ */

    public Coringa(int idJoker, String nome, Raridade raridade, Gatilho tempoDeAtivicao, float preco,
            EfeitoCoringa efeitoCoringa) {
        this.idJoker = idJoker;
        this.nome = nome;
        this.raridade = raridade;
        this.tempoDeAtivicao = tempoDeAtivicao;
        this.modificador = Modificador.CORG_BASE;
        this.preco = preco;
        this.efeitoCoringa = efeitoCoringa;
    }

    public Coringa(int idJoker, String nome, Raridade raridade, float preco,
            EfeitoCoringa efeitoCoringa) {
        this.idJoker = idJoker;
        this.nome = nome;
        this.raridade = raridade;
        this.modificador = Modificador.CORG_BASE;
        this.tempoDeAtivicao = Gatilho.PORCARTA;
        this.preco = preco;
        this.efeitoCoringa = efeitoCoringa;
    }

    public Coringa() {
    }

    /* ------------------ MéTODOS ------------------ */

    public ContextoMao efeitoCoringa(ContextoMao maoCarta) {
        return this.efeitoCoringa.aplicarEfeito(maoCarta);
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

    public Raridade getRaridade() {
        return raridade;
    }

    public void setRaridade(Raridade raridade) {
        this.raridade = raridade;
    }

    public Modificador getModificador() {
        return modificador;
    }

    public void setModificador(Modificador modificador) {
        this.modificador = modificador;
    }

    public Gatilho getTempoDeAtivicao() {
        return tempoDeAtivicao;
    }

    public void setTempoDeAtivicao(Gatilho tempoDeAtivicao) {
        this.tempoDeAtivicao = tempoDeAtivicao;
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
