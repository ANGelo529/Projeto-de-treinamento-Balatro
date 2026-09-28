package model.coringas;

import model.Efeitos.EfeitoCoringa;
import model.contextos.ContextoMao;
import model.enums.*;

/**
 * Representa uma instância concreta de um Coringa em tempo de execução no jogo.
 */
public class Coringa {

    /* ------------------ ATRIBUTOS ------------------ */

    private int idJoker;
    private String nome;
    private Raridade raridade;
    private Modificador modificador;
    private Gatilho tempoDeAtivicao;
    private float preco;

    /** Comportamento executável (interface funcional/lambda) do efeito do Coringa */
    private EfeitoCoringa efeitoCoringa;

    /* ------------------ CONSTRUTORES ------------------ */

    /** Construtor completo com definição explícita do gatilho de ativação. */
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

    /** Construtor secundário. Assume automaticamente o gatilho PORCARTA. */
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

    /** Construtor padrão sem argumentos. */
    public Coringa() {
    }

    /* ------------------ MÉTODOS DE NEGÓCIO ------------------ */

    /**
     * Aplica a regra de negócio/efeito deste Coringa sobre o contexto da mão jogada.
     * 
     * @param maoCarta Contexto atual do cálculo de pontuação.
     * @return O objeto ContextoMao modificado após a aplicação do efeito.
     */
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