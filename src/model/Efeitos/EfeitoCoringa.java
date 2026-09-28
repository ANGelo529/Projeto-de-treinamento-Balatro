package model.Efeitos;

import model.contextos.ContextoMao;
import model.enums.Naipe;

/**
 * Interface funcional e fábrica de estratégias para os efeitos dos Coringas[cite: 20].
 * Contém métodos estáticos que retornam lambdas com regras de cálculo de multiplicador[cite: 20].
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
     * Adiciona um valor de multiplicador se o naipe da carta auxiliar for igual ao naipe especificado[cite: 20].
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
     * Adiciona o multiplicador padrão (+4) se o naipe da carta auxiliar corresponder ao informado[cite: 20].
     * 
     * @param naipe Naipe exigido[cite: 20].
     * @return Instância de EfeitoCoringa executável[cite: 20].
     */
    static EfeitoCoringa addMultSeNaipe(Naipe naipe) {
        return ctx -> {
            if (ctx.getCartaAuxiliar().getNaipe().equals(naipe)) {
                ctx.adicionarMult(); // Incrementa o valor padrão fixado em ContextoMao[cite: 20]
            }
            return ctx;
        };
    }

    /**
     * Adiciona multiplicador caso a mão jogada corresponda ao tipo especificado[cite: 20].
     * 
     * ATENÇÃO (BUGS IDENTIFICADOS):
     * 1. O argumento 'valor' recebido é ignorado, pois o código executa fixo 'ctx.adicionarMult(2)'[cite: 20].
     * 2. O método lê 'ctx.getCartaAuxiliar().getTipoDeCarta()' em vez de verificar o tipo global 
     *    da mão em 'ctx.getTipoMao()'[cite: 20].
     * 
     * @param valor Quantidade de multiplicador.
     * @param maoPoker Nome da mão de poker exigida.
     * @return Instância de EfeitoCoringa executável[cite: 20].
     */
    static EfeitoCoringa addMultSeMaoPoker(int valor, String maoPoker) {
        return ctx -> {
            if (ctx.getCartaAuxiliar().getTipoDeCarta().equals(maoPoker)) {
                ctx.adicionarMult(2); // BUG: Deveria ser 'ctx.adicionarMult(valor)'[cite: 20]
            }
            return ctx;
        };
    }

    /**
     * Adiciona multiplicador incondicionalmente[cite: 20].
     * 
     * ATENÇÃO (BUG IDENTIFICADO):
     * O parâmetro 'valor' é ignorado, executando sempre o incremento fixo de '2'[cite: 20].
     * 
     * @param valor Quantidade de multiplicador.
     * @return Instância de EfeitoCoringa executável[cite: 20].
     */
    static EfeitoCoringa addMult(int valor){
        return ctx -> {
            ctx.adicionarMult(2); // BUG: Deveria ser 'ctx.adicionarMult(valor)'[cite: 20]
            return ctx;
        };
    }
}