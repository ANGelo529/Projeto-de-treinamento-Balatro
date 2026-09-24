package backEnd.src.model.Efeitos;

import backEnd.src.model.contextos.ContextoMao;

@FunctionalInterface
public interface EfeitoCoringa {
    ContextoMao aplicarEfeito(ContextoMao contextoMao);

    static EfeitoCoringa addMultSeNaipe(String naipe, int valor) {
        return ctx -> {
            if (ctx.getCartaAuxiliar().getNaipe().equals(naipe)) {
                ctx.adicionarMult(valor);
            }
            return ctx;
        };
    }

    static EfeitoCoringa addMultSeNaipe(String naipe) {
        return ctx -> {
            if (ctx.getCartaAuxiliar().getNaipe().equals(naipe)) {
                ctx.adicionarMult();
            }
            return ctx;
        };
    }

    static EfeitoCoringa addMultSeMaoPoker(int valor, String maoPoker) {
        return ctx -> {
            if (ctx.getCartaAuxiliar().getTipoDeCarta().equals(maoPoker)) {
                ctx.adicionarMult(2);
            }
            return ctx;
        };
    }

    static EfeitoCoringa addMult(int valor){
        return ctx -> {
            ctx.adicionarMult(2);
            return ctx;
        };
        
    }
}
