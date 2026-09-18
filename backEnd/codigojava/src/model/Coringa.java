package backEnd.codigojava.src.model;

import backEnd.codigojava.src.Factory.*;

public class Coringa {
    /* ------------------ VARIÁVEIS ------------------ */

    private int idJoker;
    private String nome;
    private String raridade;
    private String modificador = "Base";
    private String tempoDeAtivicao = "Agora";
    private float preco;
    private EfeitoCoringa efeitoCoringa;

    /* ------------------ CONSTRUTOR ------------------ */

    public Coringa(int idJoker, String nome, String raridade, String tempoDeAtivicao, float preco,
            EfeitoCoringa efeitoCoringa) {
        this.idJoker = idJoker;
        this.nome = nome;
        this.raridade = raridade;
        this.tempoDeAtivicao = tempoDeAtivicao;
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

    public Coringa(String modificador, Coringa outroCoringa) {
        this.idJoker = outroCoringa.idJoker;
        this.nome = outroCoringa.nome;
        this.raridade = outroCoringa.raridade;
        this.modificador = modificador;
        this.preco = outroCoringa.preco;
        this.efeitoCoringa = outroCoringa.efeitoCoringa;
    }

    public Coringa(Coringa outroCoringa) {
        this.idJoker = outroCoringa.idJoker;
        this.nome = outroCoringa.nome;
        this.raridade = outroCoringa.raridade;
        this.preco = outroCoringa.preco;
        this.efeitoCoringa = outroCoringa.efeitoCoringa;
    }

    public Coringa(int idCoringa) {
        Coringa auxCoringa = CoringaFactory.getCoringaPorId(idCoringa);
        this.idJoker = auxCoringa.idJoker;
        this.nome = auxCoringa.nome;
        this.raridade = auxCoringa.raridade;
        this.preco = auxCoringa.preco;
        this.efeitoCoringa = auxCoringa.efeitoCoringa;
    }

    /* ------------------ MéTODOS ------------------ */

    public ContextoMao realizarEfeitoCoringa(ContextoMao maoCarta) {
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

    public String getTempoDeAtivicao() {
        return tempoDeAtivicao;
    }

    public void setTempoDeAtivicao(String tempoDeAtivicao) {
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
