package model.Efeitos;

import model.contextos.ContextoMao;

@FunctionalInterface
public interface EfeitoBlind {
    abstract ContextoMao aplicarEfeito(ContextoMao contextoMao);
}
