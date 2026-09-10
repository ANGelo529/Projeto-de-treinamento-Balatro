package backEnd.codigojava;

@FunctionalInterface
public interface EfeitoCoringa {
    public record ContextoCoringa(int fichas, int multi) {
    }

    ContextoCoringa aplicarEfeito(ContextoMao contextoMao);
}
