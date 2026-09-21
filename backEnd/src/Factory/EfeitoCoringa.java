package backEnd.src.Factory;

import backEnd.src.model.contextos.ContextoMao;

@FunctionalInterface
public interface EfeitoCoringa {
    ContextoMao aplicarEfeito(ContextoMao contextoMao);
}
