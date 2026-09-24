package backEnd.src.model.coringas;

import backEnd.src.model.Efeitos.EfeitoCoringa;
import backEnd.src.model.enums.*;

public enum CatalogoCoringa {
    /* ------------------ VARIÁVEIS CONSTANTES------------------ */

    GANANCIOSO(1,
        "Coringa Ganancioso",
        Raridade.COMUM,
        5,
        EfeitoCoringa.addMultSeNaipe("Ouros")),

    VIGOROSO(2,
        "Coringa Robusto",
        Raridade.COMUM,
        5,
        EfeitoCoringa.addMultSeNaipe("Copas")),

    FURIOSO(3,
        "Coringa Irritado",
        Raridade.COMUM,
        5,
        EfeitoCoringa.addMultSeNaipe("Espadas")),

    GULOSO(4,
        "Coringa Guloso",
        Raridade.COMUM,
        5,
        EfeitoCoringa.addMultSeNaipe("Paus")),

    CORINGA(5,
        "Coringa",
        Raridade.COMUM,
        Gatilho.FINALMAO,
        5,
        EfeitoCoringa.addMult(4)),

    ALEGRE(6,
        "Coringa Guloso",
        Raridade.COMUM,
        Gatilho.FINALMAO,
        5,
        EfeitoCoringa.addMultSeMaoPoker(4, "Par")),

    BOBO(7,
        "Coringa Guloso",
        Raridade.COMUM,
        Gatilho.FINALMAO,
        5.0f,
        EfeitoCoringa.addMultSeMaoPoker(4, "Trinca")),

    IRRITADO(8,
        "Coringa Guloso",
        Raridade.COMUM,
        Gatilho.FINALMAO,
        5,
        EfeitoCoringa.addMultSeMaoPoker(4, "DoisPares")),

    MALUCO(9,
        "Coringa Guloso",
        Raridade.COMUM,
        Gatilho.FINALMAO,
        5,
        EfeitoCoringa.addMultSeMaoPoker(4, "Sequência")),

    ENGRACADO(10,
        "Coringa Guloso",
        Raridade.COMUM,
        Gatilho.FINALMAO,
        5,
        EfeitoCoringa.addMultSeMaoPoker(4, "Flush")),

    QUATRODEDOS(11,
        "Quatro Dedos",
        Raridade.INCOMUM,
        Gatilho.PASSIVO,
        7,
        EfeitoCoringa.addMultSeMaoPoker(4, "DoisPares"));

    /* ------------------ VARIÁVEIS ------------------ */
    
    private final int idJoker;
    private final String nome;
    private final Raridade raridade;
    private final Gatilho tempoDeAtivicao;
    private final float preco;
    private final EfeitoCoringa efeitoCoringa;

    /* ------------------ CONSTRUTOR ------------------ */

    private CatalogoCoringa(int idJoker, String nome, Raridade raridade, Gatilho tempoDeAtivicao, float preco,
            EfeitoCoringa efeitoCoringa) {
        this.idJoker = idJoker;
        this.nome = nome;
        this.raridade = raridade;
        this.tempoDeAtivicao = tempoDeAtivicao;
        this.preco = preco;
        this.efeitoCoringa = efeitoCoringa;
    }

    private CatalogoCoringa(int idJoker, String nome, Raridade raridade, float preco,
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
