import model.GameState;
import model.usuario.Usuario;
import util.TesteSaida;

public class Main {

    public static void main(String[] args) {
        Usuario usuario = TesteSaida.getUsuarioDev();
        GameState.setUsuario(usuario);
        GameState.iniciarSeed("BANANA");
        GameState.iniciarRodada();
    }
}
