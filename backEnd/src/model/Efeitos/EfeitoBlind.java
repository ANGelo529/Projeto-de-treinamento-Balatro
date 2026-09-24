package backEnd.src.model.Efeitos;

import backEnd.src.model.contextos.ContextoMao;

@FunctionalInterface
public interface EfeitoBlind {
    abstract ContextoMao aplicarEfeito(ContextoMao contextoMao);
}
