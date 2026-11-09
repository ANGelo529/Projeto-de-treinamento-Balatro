package model.Efeitos;

import model.contextos.ContextoMao;

/**
 * Interface funcional que define o contrato para aplicação de efeitos das Blinds (Apostas/Chefões)[cite: 19].
 * Permite implementar modificadores de rodada (ex: desabilitar naipe, alterar multiplicadores)
 * via expressões Lambda ou referências de método[cite: 19].
 */
@FunctionalInterface
public interface EfeitoBlind {
    
    /**
     * Aplica o efeito específico da Blind sobre o contexto da mão atual[cite: 19].
     * 
     * @param contextoMao Objeto contendo os dados e pontuações da mão[cite: 19].
     * @return O objeto ContextoMao após a aplicação das alterações/regras[cite: 19].
     */
    abstract ContextoMao aplicarEfeito(ContextoMao contextoMao);
}