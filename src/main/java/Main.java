import jdbc.GameStateDAO;
import jdbc.UsuarioDAO;
import model.GameState;
import model.usuario.Usuario;

public class Main {
    public static void main(String[] args) {
        Usuario usuario = new Usuario("Angelo Miguel", "05290", false);
        UsuarioDAO.deletar("Angelo Miguel", "05290");
        UsuarioDAO.inserir(usuario);
        GameStateDAO.inserirGeral(usuario);
        // UsuarioDAO.updateStatusJogo(usuario);
        // UsuarioDAO.listar();

    }
}
