package model.Efeitos;

import model.contextos.ContextoMao;
import model.enums.*;

/**
 * Interface funcional e fábrica de estratégias para os efeitos dos
 * Coringas[cite: 20].
 * Contém métodos estáticos que retornam lambdas com regras de cálculo de
 * multiplicador[cite: 20].
 */
@FunctionalInterface
public interface EfeitoCoringa {

    /**
     * Aplica a lógica do efeito do Coringa no contexto da mão[cite: 20].
     * 
     * @param contextoMao Contexto de pontuação atual[cite: 20].
     * @return Contexto da mão modificado[cite: 20].
     */
    ContextoMao aplicarEfeito(ContextoMao contextoMao);

    /**
     * Adiciona um valor de multiplicador se o naipe da carta auxiliar for igual ao
     * naipe especificado[cite: 20].
     * 
     * @param naipe Naipe exigido para ativar o efeito[cite: 20].
     * @param valor Quantidade de multiplicador a adicionar[cite: 20].
     * @return Instância de EfeitoCoringa executável[cite: 20].
     */
    static EfeitoCoringa addMultSeNaipe(Naipe naipe, int valor) {
        return ctx -> {
            if (ctx.getCartaAuxiliar().getNaipe().equals(naipe)) {
                ctx.adicionarMult(valor);
            }
            return ctx;
        };
    }

    /**
     * Adiciona o multiplicador caso o naipe da carta auxiliar
     * corresponder ao informado[cite: 20].
     * 
     * @param valor valor passado como aumento do multiplicador
     * @param naipe Naipe exigido[cite: 20].
     * @return Instância de EfeitoCoringa executável[cite: 20].
     */
    static EfeitoCoringa addMultSeNaipe(int valor, Naipe naipe) {
        return ctx -> {
            if (ctx.getCartaAuxiliar().getNaipe().equals(naipe)) {
                ctx.adicionarMult(valor);
            }
            return ctx;
        };
    }

    /**
     * Adiciona o multiplicador padrão (+4) naipe da carta auxiliar
     * corresponder ao informado[cite: 20].
     * 
     * @param naipe Naipe exigido[cite: 20].
     * @return Instância de EfeitoCoringa executável[cite: 20].
     */
    static EfeitoCoringa addMultSeNaipe(Naipe naipe) {
        return ctx -> {
            if (ctx.getCartaAuxiliar().getNaipe().equals(naipe)) {
                ctx.adicionarMult(4); // Incrementa o valor padrão
            }
            return ctx;
        };
    }

    /**
     * Adiciona multiplicador caso a mão jogada corresponda ao tipo
     * especificado[cite: 20].
     * 
     * @param valor    Quantidade de multiplicador.
     * @param maoPoker Nome da mão de poker exigida.
     * @return Instância de EfeitoCoringa executável[cite: 20].
     */
    static EfeitoCoringa addMultSeMaoPoker(int valor, MaoPoker maoPoker) {
        return ctx -> {
            if (ctx.getTipoMao() == maoPoker) {
                ctx.adicionarMult(valor);
            }
            return ctx;
        };
    }

    /**
     * Adiciona multiplicador incondicionalmente[cite: 20]..
     * 
     * @param valor Quantidade de multiplicador.
     * @return Instância de EfeitoCoringa executável[cite: 20].
     */
    static EfeitoCoringa addMult(int valor) {
        return ctx -> {
            ctx.adicionarMult(valor);
            return ctx;
        };
    }

    /**
     * Adiciona ficha caso a mão jogada corresponda ao tipo
     * especificado[cite: 20].
     * 
     * @param valor    Quantidade de multiplicador.
     * @param maoPoker Nome da mão de poker exigida.
     * @return Instância de EfeitoCoringa executável[cite: 20].
     */
    static EfeitoCoringa addFichaSeMaoPoker(int valor, MaoPoker maoPoker) {
        return ctx -> {
            if (ctx.getTipoMao() == maoPoker) {
                ctx.adicionarMult(valor);
            }
            return ctx;
        };
    }

    static EfeitoCoringa TODO() {
        return ctx -> {
            return ctx;
        };
    }
}