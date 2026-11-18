import jdbc.DeckCartaDao;
import jdbc.GameStateDAO;
import jdbc.UsuarioDAO;
import model.GameState;
import model.usuario.Usuario;
import util.TesteSaida;

public class Main {
    
    public static void main(String[] args) {
        // TesteSaida.inserirUsuarioDev();
        Usuario usuario = UsuarioDAO.selectUnico("dev","dev");
        GameState.setUsuario(usuario);
        GameState.iniciarSeed("BANANA");
        GameState.iniciarRodada();
    }
}
