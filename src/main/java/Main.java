import model.GameState;
import util.TesteSaida;

public class Main {
    public static void main(String[] args) {
        GameState.iniciarSeed("BANANA");
        GameState.iniciarRodada();
        GameState.controleJogo();
    }
}
