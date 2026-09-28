package model.coringas;

import model.Efeitos.EfeitoCoringa;
import model.enums.*;

/**
 * Enumeração que funciona como o catálogo definitivo/banco de dados estático 
 * dos Coringas disponíveis no jogo.
 */
public enum CatalogoCoringa {

    /* ------------------ DEFINIÇÃO DOS CORINGAS (CONSTANTES) ------------------ */

    /** Concede multiplicador bônus ao pontuar cartas de Ouros */
    GANANCIOSO(1, "Coringa Ganancioso", Raridade.COMUM, 5, EfeitoCoringa.addMultSeNaipe(Naipe.OUROS)),

    /** Concede multiplicador bônus ao pontuar cartas de Copas */
    VIGOROSO(2, "Coringa Robusto", Raridade.COMUM, 5, EfeitoCoringa.addMultSeNaipe(Naipe.COPAS)),

    /** Concede multiplicador bônus ao pontuar cartas de Espadas */
    FURIOSO(3, "Coringa Irritado", Raridade.COMUM, 5, EfeitoCoringa.addMultSeNaipe(Naipe.ESPADAS)),

    /** Concede multiplicador bônus ao pontuar cartas de Paus */
    GULOSO(4, "Coringa Guloso", Raridade.COMUM, 5, EfeitoCoringa.addMultSeNaipe(Naipe.PAUS)),

    /** Coringa genérico acionado ao final do cálculo da mão */
    CORINGA(5, "Coringa", Raridade.COMUM, Gatilho.FINALMAO, 5, EfeitoCoringa.addMult(4)),

    /* 
     * ALERTA DE BUGS NOS CORINGAS ABAIXO:
     * 1. Nomes duplicados: "ALEGRE", "BOBO", "IRRITADO", "MALUCO" e "ENGRACADO" estão todos 
     *    com o nome visível "Coringa Guloso" em vez de seus nomes próprios.
     * 2. Tipo incompatível: Estão enviando Strings (ex: "Par", "Trinca") para 'addMultSeMaoPoker' 
     *    em vez de utilizar diretamente o Enum 'MaoPoker'.
     * 3. Configuração errada: QUATRODEDOS possui um efeito de bônus de multiplicador em "DoisPares"
     *    em vez de ser uma regra de alteração de tamanho de mão (Gatilho PASSIVO).
     */

    ALEGRE(6, "Coringa Alegre", Raridade.COMUM, Gatilho.FINALMAO, 5, EfeitoCoringa.addMultSeMaoPoker(4, "Par")),

    BOBO(7, "Coringa Bobo", Raridade.COMUM, Gatilho.FINALMAO, 5, EfeitoCoringa.addMultSeMaoPoker(4, "Trinca")),

    IRRITADO(8, "Coringa Irritado", Raridade.COMUM, Gatilho.FINALMAO, 5, EfeitoCoringa.addMultSeMaoPoker(4, "DoisPares")),

    MALUCO(9, "Coringa Maluco", Raridade.COMUM, Gatilho.FINALMAO, 5, EfeitoCoringa.addMultSeMaoPoker(4, "Sequência")),

    ENGRACADO(10, "Coringa Engraçado", Raridade.COMUM, Gatilho.FINALMAO, 5, EfeitoCoringa.addMultSeMaoPoker(4, "Flush")),

    QUATRODEDOS(11, "Quatro Dedos", Raridade.INCOMUM, Gatilho.PASSIVO, 7, EfeitoCoringa.addMultSeMaoPoker(4, "DoisPares"));

    /* ------------------ ATRIBUTOS DO ENUM ------------------ */

    private final int idJoker;
    private final String nome;
    private final Raridade raridade;
    private final Gatilho tempoDeAtivicao;
    private final int preco;
    private final EfeitoCoringa efeitoCoringa;

    /* ------------------ CONSTRUTORES ------------------ */

    /** Construtor para Coringas com tempo de ativação explicitado (ex: FINALMAO, PASSIVO). */
    private CatalogoCoringa(int idJoker, String nome, Raridade raridade, Gatilho tempoDeAtivicao, int preco,
            EfeitoCoringa efeitoCoringa) {
        this.idJoker = idJoker;
        this.nome = nome;
        this.raridade = raridade;
        this.tempoDeAtivicao = tempoDeAtivicao;
        this.preco = preco;
        this.efeitoCoringa = efeitoCoringa;
    }

    /** Construtor simplificado. Assume o tempo de ativação padrão como PORCARTA. */
    private CatalogoCoringa(int idJoker, String nome, Raridade raridade, int preco,
            EfeitoCoringa efeitoCoringa) {
        this.idJoker = idJoker;
        this.nome = nome;
        this.raridade = raridade;
        this.tempoDeAtivicao = Gatilho.PORCARTA;
        this.preco = preco;
        this.efeitoCoringa = efeitoCoringa;
    }

    /* ------------------ GETTERS ------------------ */

    public int getIdJoker() {
        return idJoker;
    }

    public String getNome() {
        return nome;
    }

    public Raridade getRaridade() {
        return raridade;
    }

    public Gatilho getTempoDeAtivicao() {
        return tempoDeAtivicao;
    }

    public float getPreco() {
        return preco;
    }

    public EfeitoCoringa getEfeitoCoringa() {
        return efeitoCoringa;
    }
}