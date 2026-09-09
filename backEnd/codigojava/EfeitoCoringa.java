package backEnd.codigojava;

@FunctionalInterface
public interface EfeitoCoringa {
    public record ContextoJogo(int fichas, int multi) {
    }

    ContextoJogo aplicarEfeito(ContextoMao contextoMao);
}
