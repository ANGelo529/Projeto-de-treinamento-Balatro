package backEnd.codigojava.src.Factory;

import backEnd.codigojava.src.model.Coringa;

public enum CoringaFactory {
    GANANCIOSO(1, "Coringa Ganancioso", "Comum", 5, ctx -> {
        if ("Ouros".equals(ctx.getCartaAuxiliar().getNaipe())) {
            ctx.setMultAtual(ctx.getMultAtual() + 4);
        }
        return ctx;
    }),
    ROBUSTO(2, "Coringa Robusto", "Comum", 5, ctx -> {
        if ("Copas".equals(ctx.getCartaAuxiliar().getNaipe())) {
            ctx.setMultAtual(ctx.getMultAtual() + 4);
        }
        return ctx;
    }),
    IRRITADO(3, "Coringa Irritado", "Comum", 5, ctx -> {
        if ("Espadas".equals(ctx.getCartaAuxiliar().getNaipe())) {
            ctx.setMultAtual(ctx.getMultAtual() + 4);
        }
        return ctx;
    }),
    GLUTAO(4, "Coringa Guloso", "Comum", 5, ctx -> {
        if ("Paus".equals(ctx.getCartaAuxiliar().getNaipe())) {
            ctx.setMultAtual(ctx.getMultAtual() + 4);
        }
        return ctx;
    }),
    PADRAO(5, "Coringa Padrão", "Comum", 5, ctx -> {
        ctx.setMultAtual(ctx.getMultAtual() + 1);
        return ctx;
    }),
    ALEGRE(6, "Coringa Guloso", "Comum","FinalMao", 5, ctx -> {
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

    private CoringaFactory(int idJoker, String nome, String raridade, String tempoDeAtivicao, float preco,
            EfeitoCoringa efeitoCoringa) {
        this.idJoker = idJoker;
        this.nome = nome;
        this.raridade = raridade;
        this.tempoDeAtivicao = tempoDeAtivicao;
        this.preco = preco;
        this.efeitoCoringa = efeitoCoringa;
    }

    private CoringaFactory(int idJoker, String nome, String raridade, float preco,
            EfeitoCoringa efeitoCoringa) {
        this.idJoker = idJoker;
        this.nome = nome;
        this.raridade = raridade;
        this.preco = preco;
        this.efeitoCoringa = efeitoCoringa;
        this.tempoDeAtivicao = "PorCarta";
    }

    private Coringa criarCoringa() {
        return new Coringa(this.idJoker, this.nome, this.raridade, this.preco, this.efeitoCoringa);
    }

    private Coringa criarCoringaTempoDeAtivacao() {
        return new Coringa(this.idJoker, this.nome, this.raridade, this.tempoDeAtivicao, this.preco,
                this.efeitoCoringa);
    }

    public static Coringa getCoringaPorId(int id) {
        for (CoringaFactory coringa : values()) {
            if (coringa.idJoker == id) {
                if ("Agora".equals(coringa.tempoDeAtivicao)) {
                    return coringa.criarCoringa();
                } else {
                    return coringa.criarCoringaTempoDeAtivacao();
                }
            }

        }
        throw new IllegalArgumentException("Coringa não encontrado com ID: " + id);
    }
}
