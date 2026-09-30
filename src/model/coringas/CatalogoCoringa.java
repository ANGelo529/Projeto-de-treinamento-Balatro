package model.coringas;

import model.Efeitos.EfeitoCoringa;
import model.enums.*;

/**
 * Enumeração que funciona como o catálogo definitivo/banco de dados estático
 * dos Coringas disponíveis no jogo.
 */
public enum CatalogoCoringa {

    /* ------------------ DEFINIÇÃO DOS CORINGAS (CONSTANTES) ------------------ */

    /* --------- CORINGAS COMUNS --------- */

    /** Concede multiplicador bônus ao pontuar cartas de Ouros */
    GANANCIOSO(1, "Coringa Ganancioso", Raridade.COMUM, Gatilho.PORCARTA, 5,
            EfeitoCoringa.addMultSeNaipe(4, Naipe.OUROS)),

    /** Concede multiplicador bônus ao pontuar cartas de Copas */
    VIGOROSO(2, "Coringa Robusto", Raridade.COMUM, Gatilho.PORCARTA, 5, EfeitoCoringa.addMultSeNaipe(4, Naipe.COPAS)),

    /** Concede multiplicador bônus ao pontuar cartas de Espadas */
    FURIOSO(3, "Coringa Irritado", Raridade.COMUM, Gatilho.PORCARTA, 5, EfeitoCoringa.addMultSeNaipe(4, Naipe.ESPADAS)),

    /** Concede multiplicador bônus ao pontuar cartas de Paus */
    GULOSO(4, "Coringa Guloso", Raridade.COMUM, Gatilho.PORCARTA, 5, EfeitoCoringa.addMultSeNaipe(4, Naipe.PAUS)),

    /** Coringa genérico acionado ao final do cálculo da mão */
    CORINGA(5, "Coringa", Raridade.COMUM, Gatilho.FINALMAO, 5, EfeitoCoringa.addMult(4)),

    /** Concede multiplicador bônus se jogou um par */
    ALEGRE(6, "Coringa Alegre", Raridade.COMUM, Gatilho.FINALMAO, 5, EfeitoCoringa.addMultSeMaoPoker(8, MaoPoker.PAR)),

    /** Concede multiplicador bônus se jogou uma trinca */
    BOBO(7, "Coringa Bobo", Raridade.COMUM, Gatilho.FINALMAO, 5, EfeitoCoringa.addMultSeMaoPoker(12, MaoPoker.TRINCA)),

    /** Concede multiplicador bônus se jogou dois pares */
    IRRITADO(8, "Coringa Irritado", Raridade.COMUM, Gatilho.FINALMAO, 5,
            EfeitoCoringa.addMultSeMaoPoker(10, MaoPoker.DOISPARES)),

    /** Concede multiplicador bônus se jogou uma sequência */
    MALUCO(9, "Coringa Maluco", Raridade.COMUM, Gatilho.FINALMAO, 5,
            EfeitoCoringa.addMultSeMaoPoker(12, MaoPoker.SEQUENCIA)),

    /** Concede multiplicador bônus se jogou um flush */
    ENGRACADO(10, "Coringa Engraçado", Raridade.COMUM, Gatilho.FINALMAO, 5,
            EfeitoCoringa.addMultSeMaoPoker(10, MaoPoker.FLUSH)),

    /** Concede ficha bônus se jogou um par */
    MALANDRO(11, "Coringa Malandro", Raridade.COMUM, Gatilho.FINALMAO, 5,
            EfeitoCoringa.addFichaSeMaoPoker(50, MaoPoker.PAR)),

    /** Concede ficha bônus se jogou uma trinca */
    SAGAZ(12, "Coringa Sagaz", Raridade.COMUM, Gatilho.FINALMAO, 5,
            EfeitoCoringa.addFichaSeMaoPoker(100, MaoPoker.TRINCA)),

    /** Concede ficha bônus se jogou dois pares */
    ASTUTO(13, "Coringa Astuto", Raridade.COMUM, Gatilho.FINALMAO, 5,
            EfeitoCoringa.addFichaSeMaoPoker(80, MaoPoker.DOISPARES)),

    /** Concede ficha bônus se jogou uma sequência */
    DESONESTO(14, "Coringa Desonesto", Raridade.COMUM, Gatilho.FINALMAO, 5,
            EfeitoCoringa.addFichaSeMaoPoker(100, MaoPoker.SEQUENCIA)),

    /** Concede ficha bônus se jogou um flush */
    ENGENHOSO(15, "Coringa Engenhoso", Raridade.COMUM, Gatilho.FINALMAO, 5,
            EfeitoCoringa.addFichaSeMaoPoker(80, MaoPoker.FLUSH)),

    /* --------- CORINGAS INCOMUNS --------- */

    /** Diminui a quantidadde necessária para fazer um flush e uma sequência */
    QUATRODEDOS(16, "Quatro Dedos", Raridade.INCOMUM, Gatilho.PASSIVO, 7,
            EfeitoCoringa.TODO()),

    /**
     * BlindSelecionado == true destrói o coringa da direita e ganha o dobro do seu
     * valor de venda como mult(valor Acumulativo do coringa)
     */
    // TODO: ver como acumular valores
    ADAGACERIMONIAL(17, "Adaga Cerimonial", Raridade.INCOMUM, Gatilho.BLINDSELECIONADO, 7, EfeitoCoringa.TODO()),

    /** Multiplica o mult por 4x a cada 6 mão jogadas */
    CARTALEALDADE(18, "Carta de Lealdade", Raridade.INCOMUM, Gatilho.FINALMAO, 7, EfeitoCoringa.TODO()),

    /** Cada Ás,2,3,5,8 jogados dão 8 de multi */
    FIBONNACI(19, "Fibonnaci", Raridade.INCOMUM, Gatilho.PORCARTA, 7, EfeitoCoringa.TODO()),

    /** Reativa cada carta com 2, 3, 4, 5 */
    // TODO: ver como reativar as cartas
    IMPOSTOR(20, "Impostor", Raridade.INCOMUM, Gatilho.PORCARTA, 7, EfeitoCoringa.TODO()),

    /** Toda carta passa a ser considerada da REALEZA */
    // TODO: ver como aplicar esse efeito a todas as cartas
    PAREIDOLIA(21, "Pareidolia", Raridade.INCOMUM, Gatilho.PASSIVO, 7, EfeitoCoringa.TODO()),

    /** Tem uma chance de 1/4 de upar a mão jogada */
    ESPACIAL(22, "Coringa espacial", Raridade.INCOMUM, Gatilho.FINALMAO, 7, EfeitoCoringa.TODO()),

    /** 3x mult se todas as cartas da mão forem ESPADAS ou PAUS */
    QUADRONEGRO(23, "Quadro negro", Raridade.INCOMUM, Gatilho.FINALMAO, 7, EfeitoCoringa.TODO()),

    /** Zera os descartes e ganha +3 mãos */
    LADRAO(24, "Ladrão", Raridade.INCOMUM, Gatilho.BLINDSELECIONADO, 7, EfeitoCoringa.TODO()),

    /** Adiciona +5 de mult a carta jogada(valor Acumulativo na carta) */
    // TODO: ver como acumular valores
    CAMINHANTE(25, "Caminhante", Raridade.INCOMUM, Gatilho.PORCARTA, 7, EfeitoCoringa.TODO()),

    /** 3x mult se a MAOPOKER já tiver sido jogada */
    CARTAAFIADA(26, "Carta Afiada", Raridade.INCOMUM, Gatilho.FINALMAO, 7, EfeitoCoringa.TODO()),

    /**
     * Destrói um coringa aleatório quando o blind for selecionado e ganha 0,5x de
     * mult(valor Acumulativo do coringa)
     */
    // TODO: ver como acumular valores
    INSANIDADE(27, "Insanidade", Raridade.INCOMUM, Gatilho.BLINDSELECIONADO, 7, EfeitoCoringa.TODO()),

    /**
     * Adiciona 1$ no fim da rodada aumenta em 2$ a cada BossBlind(valor Acumulativo
     * do Coringa)
     */
    // TODO: ver como acumular valores
    FOGUETE(28, "Foguete", Raridade.INCOMUM, Gatilho.FINALRODADA, 7, EfeitoCoringa.TODO()),

    /** +1$ a cada 5$ que o jogador possua (limite == 5) */
    PARALUA(29, "Para a lua", Raridade.INCOMUM, Gatilho.FINALRODADA, 7, EfeitoCoringa.TODO()),

    /** +2 fichas a cada 1$ que o jogador possua (sem limite) */
    TOURO(30, "Touro", Raridade.INCOMUM, Gatilho.FINALMAO, 7, EfeitoCoringa.TODO()),

    /* --------- CORINGAS RAROS --------- */

    /** +250 de fichas porém -2 tamanhoMao */
    DUBLE(31, "Dublê", Raridade.RARO, Gatilho.FINALMAO, 8, EfeitoCoringa.TODO()),

    /** Copia o efeito do Coringa mais a esquerda */
    // TODO: ver como fazer pra repetir o efeito
    BOAIDEIA(32, "Boa ideia", Raridade.RARO, Gatilho.PASSIVO, 8, EfeitoCoringa.TODO()),

    /** Copia o efeito do Coringa à sua direita */
    // TODO: ver como fazer pra repetir o efeito
    PROJETO(33, "Projeto", Raridade.RARO, Gatilho.PASSIVO, 8, EfeitoCoringa.TODO()),

    /**
     * Cada carta jogada com (Naipe aleatório) dá 1,5x de mult (Troca de naipe no
     * fim da rodada)
     */
    // TODO: ver uma forma de alterar o naipe
    ANTIG0(34, "Coringa Antigo", Raridade.RARO, Gatilho.PORCARTA, 8, EfeitoCoringa.TODO()),

    /** Cria um Tarot se a mão for jogada com 4 reais ou menos */
    VAGABUNDO(35, "Vagabundo", Raridade.RARO, Gatilho.FINALRODADA, 8, EfeitoCoringa.TODO()),

    /** 2x mult se a mão CONTÉM um PAR */
    ADUPLA(36, "A Dupla", Raridade.RARO, Gatilho.FINALMAO, 8, EfeitoCoringa.TODO()),

    /** 3x mult se a mão CONTÉM uma TRINCA */
    OTRIO(37, "O Trio", Raridade.RARO, Gatilho.FINALMAO, 8, EfeitoCoringa.TODO()),

    /** 4x mult se a mão CONTÉM uma QUADRA */
    AFAMILIA(38, "A Familia", Raridade.RARO, Gatilho.FINALMAO, 8, EfeitoCoringa.TODO()),

    /** 3x mult se a mão CONTÉM uma sequência */
    AORDEM(39, "A Ordem", Raridade.RARO, Gatilho.FINALMAO, 8, EfeitoCoringa.TODO()),

    /** 2x mult se a mão CONTÉM um FLUSH */
    ATRIBO(40, "A Tribo", Raridade.RARO, Gatilho.FINALMAO, 8, EfeitoCoringa.TODO()),

    /* --------- CORINGAS LENDÁRIOS --------- */

    /** +1x se uma carta da REALEZA é destruída(valor Acumulativo Coringa) */
    // TODO: ver como acumular valores
    CAINO(41, "Caino", Raridade.LENDARIO, Gatilho.FINALMAO, EfeitoCoringa.TODO()),

    /** K e Q jogados dão 2x mult */
    TRIBOULET(42, "Triboulet", Raridade.LENDARIO, Gatilho.FINALMAO, EfeitoCoringa.TODO()),

    /**
     * +1x mult a cada 23 descartes após a ter essa carta(valor Acumulativo do
     * Coringa)
     */
    // TODO: ver como acumular valores
    YORICK(43, "Yorick", Raridade.LENDARIO, Gatilho.FINALMAO, EfeitoCoringa.TODO()),

    /** Desativa o efeito do BossBlind */
    CHICOT(44, "Chicot", Raridade.LENDARIO, Gatilho.PASSIVO, EfeitoCoringa.TODO()),

    /** Cria uma cópia negativa de um Consumível aleatório que o jogador possua */
    PERKEO(45, "Perkeo", Raridade.LENDARIO, Gatilho.PASSIVO, EfeitoCoringa.TODO());

    /* ------------------ ATRIBUTOS DO ENUM ------------------ */

    private final int idJoker;
    private final String nome;
    private final Raridade raridade;
    private final Gatilho tempoDeAtivicao;
    private final int preco;
    private final EfeitoCoringa efeitoCoringa;

    /* ------------------ CONSTRUTORES ------------------ */

    /**
     * Construtor para Coringas com tempo de ativação explicitado (ex: FINALMAO,
     * PASSIVO).
     */
    private CatalogoCoringa(int idJoker, String nome, Raridade raridade, Gatilho tempoDeAtivicao, int preco,
            EfeitoCoringa efeitoCoringa) {
        this.idJoker = idJoker;
        this.nome = nome;
        this.raridade = raridade;
        this.tempoDeAtivicao = tempoDeAtivicao;
        this.preco = preco;
        this.efeitoCoringa = efeitoCoringa;
    }

    /**
     * Construtor simplificado. Assume o preco como 10 (só acontece isso nos
     * coringas lendários)
     */
    private CatalogoCoringa(int idJoker, String nome, Raridade raridade, Gatilho tempoDeAtivicao,
            EfeitoCoringa efeitoCoringa) {
        this.idJoker = idJoker;
        this.nome = nome;
        this.raridade = raridade;
        this.tempoDeAtivicao = tempoDeAtivicao;
        this.preco = 10; // Somente tem esse valor para conseguir ativar alguns coringas
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