package backEnd.src.model.coringas;

import backEnd.src.Factory.EfeitoCoringa;

public enum CatalogoCoringa {
    /* ------------------ VARIÁVEIS ------------------ */

    GANANCIOSO(1, "Coringa Ganancioso", "Comum", 5, ctx -> {
        if ("Ouros".equals(ctx.getCartaAuxiliar().getNaipe())) {
            ctx.setMultAtual(ctx.getMultAtual() + 4);
        }
        return ctx;
    }),
    VIGOROSO(2, "Coringa Robusto", "Comum", 5, ctx -> {
        if ("Copas".equals(ctx.getCartaAuxiliar().getNaipe())) {
            ctx.setMultAtual(ctx.getMultAtual() + 4);
        }
        return ctx;
    }),
    FURIOSO(3, "Coringa Irritado", "Comum", 5, ctx -> {
        if ("Espadas".equals(ctx.getCartaAuxiliar().getNaipe())) {
            ctx.setMultAtual(ctx.getMultAtual() + 4);
        }
        return ctx;
    }),
    GULOSO(4, "Coringa Guloso", "Comum", 5, ctx -> {
        if ("Paus".equals(ctx.getCartaAuxiliar().getNaipe())) {
            ctx.setMultAtual(ctx.getMultAtual() + 4);
        }
        return ctx;
    }),
    CORINGA(5, "Coringa", "Comum", "FinalMao", 5, ctx -> {
        ctx.setMultAtual(ctx.getMultAtual() + 4);
        return ctx;
    }),
    ALEGRE(6, "Coringa Guloso", "Comum", "FinalMao", 5, ctx -> {
        if (ctx.getCartaAuxiliar().getTipoDeCarta().equals("DoisPares")) {
            ctx.setMultAtual(ctx.getMultAtual() + 2);
        }
        return ctx;
    }),
    BOBO(6, "Coringa Guloso", "Comum", "FinalMao", 5, ctx -> {
        if (ctx.getCartaAuxiliar().getTipoDeCarta().equals("DoisPares")) {
            ctx.setMultAtual(ctx.getMultAtual() + 2);
        }
        return ctx;
    }),
    IRRITADO(6, "Coringa Guloso", "Comum", "FinalMao", 5, ctx -> {
        if (ctx.getCartaAuxiliar().getTipoDeCarta().equals("DoisPares")) {
            ctx.setMultAtual(ctx.getMultAtual() + 2);
        }
        return ctx;
    }),
    MALUCO(6, "Coringa Guloso", "Comum", "FinalMao", 5, ctx -> {
        if (ctx.getCartaAuxiliar().getTipoDeCarta().equals("DoisPares")) {
            ctx.setMultAtual(ctx.getMultAtual() + 2);
        }
        return ctx;
    }),
    ENGRACADO(6, "Coringa Guloso", "Comum", "FinalMao", 5, ctx -> {
        if (ctx.getCartaAuxiliar().getTipoDeCarta().equals("DoisPares")) {
            ctx.setMultAtual(ctx.getMultAtual() + 2);
        }
        return ctx;
    }),
    QUATRODEDOS(7, "Quatro Dedos", "Incomun", "Passivo", 7, ctx -> {
        if (ctx.getCartaAuxiliar().getTipoDeCarta().equals("DoisPares")) {
            ctx.setMultAtual(ctx.getMultAtual() + 2);
        }
        return ctx;
    });

    private final int idJoker;
    private final String nome;
    private final String raridade;
    private final String tempoDeAtivicao;
    private final float preco;
    private final EfeitoCoringa efeitoCoringa;

    /* ------------------ CONSTRUTOR ------------------ */

    private CatalogoCoringa(int idJoker, String nome, String raridade, String tempoDeAtivicao, float preco,
            EfeitoCoringa efeitoCoringa) {
        this.idJoker = idJoker;
        this.nome = nome;
        this.raridade = raridade;
        this.tempoDeAtivicao = tempoDeAtivicao;
        this.preco = preco;
        this.efeitoCoringa = efeitoCoringa;
    }

    private CatalogoCoringa(int idJoker, String nome, String raridade, float preco,
            EfeitoCoringa efeitoCoringa) {
        this.idJoker = idJoker;
        this.nome = nome;
        this.raridade = raridade;
        this.tempoDeAtivicao = "PorCarta";
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

    public String getRaridade() {
        return raridade;
    }

    public String getTempoDeAtivicao() {
        return tempoDeAtivicao;
    }

    public float getPreco() {
        return preco;
    }

    public EfeitoCoringa getEfeitoCoringa() {
        return efeitoCoringa;
    }
}
