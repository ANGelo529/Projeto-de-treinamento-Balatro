package jdbc;

import java.sql.Statement;
import java.util.Random;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.GameState;
import model.usuario.Usuario;

public class GameStateDAO {

    /*
     * Método que insere o GameState no Banco de dados
     * Se baseia nos dados estáticos do GameState
     */
    public static void inserirGeral(Usuario usuario) {
        String sql = "INSERT INTO game_state ( id_usuario, tamanho_mao, qtd_mao, qtd_descarte, pontos_atuais, ante_atual, pos_blind_atual, limite_coringa, limite_consumivel, tipo_organizacao, seed ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try (Connection conexao = Conexao.conectarBD();
                PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            stmt.setInt(1, usuario.getId_Usuario());
            stmt.setInt(2, GameState.getTamanhoMao());
            stmt.setInt(3, GameState.getQtdMao());
            stmt.setInt(4, GameState.getQtdMaoDescarte());
            stmt.setLong(5, GameState.getpontosAtuais());
            stmt.setInt(6, GameState.getAnteAtual());
            stmt.setInt(7, GameState.getPosBlindAtual());
            stmt.setInt(8, GameState.getLimiteCoringas());
            stmt.setInt(9, GameState.getLimiteConsumiveis());
            stmt.setString(10, GameState.getTipoDeOrganizacao());
            stmt.setString(11, GameState.getSeed());

            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    GameState.setIdGameState(rs.getInt(1));
                }
            }

            // System.out.println("Dados inseridos com sucesso");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir gameState " + e.getMessage(), e);
        }
    }

    /*
     * Método que atualiza o GameState no Banco de dados
     * Se baseia nos dados estáticos do GameState
     */
    public static void update() {
        String sql = "UPDATE game_state SET" +
                " tamanho_mao = ?," +
                " qtd_mao = ?," +
                " qtd_descarte = ?," +
                " pontos_atuais = ?," +
                " ante_atual = ?," +
                " pos_blind_atual = ?," +
                " limite_coringa = ?," +
                " limite_consumivel = ?," +
                " tipo_organizacao = ?," +
                "seed = ? " +
                "WHERE id_usuario = ?;";

        try (Connection conexao = Conexao.conectarBD();
                PreparedStatement stmt = conexao.prepareStatement(sql);) {

            stmt.setInt(1, GameState.getTamanhoMao());
            stmt.setInt(2, GameState.getQtdMao());
            stmt.setInt(3, GameState.getQtdMaoDescarte());
            stmt.setLong(4, GameState.getpontosAtuais());
            stmt.setInt(5, GameState.getAnteAtual());
            stmt.setInt(6, GameState.getPosBlindAtual());
            stmt.setInt(7, GameState.getLimiteCoringas());
            stmt.setInt(8, GameState.getLimiteConsumiveis());
            stmt.setString(9, GameState.getTipoDeOrganizacao());
            stmt.setString(10, GameState.getSeed());
            stmt.setInt(11, GameState.getUsuario().getId_Usuario());

            stmt.executeUpdate();

            // System.out.println("Dados atualizados com sucesso");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar gameState " + e.getMessage(), e);
        }
    }

    /*
     * Método que recebe os dados do GameState que estão no Banco de dados
     */
    public static void receberDados(Usuario usuario) {
        String sql = "SELECT * FROM game_state WHERE id_usuario = ?;";

        try (Connection conexao = Conexao.conectarBD();
                PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            stmt.setInt(1, usuario.getId_Usuario());

            try (ResultSet rs1 = stmt.executeQuery();) {
                if (rs1.next()) {
                    GameState.setIdGameState(rs1.getInt(1));
                    GameState.setTamanhoMao(rs1.getInt(3));
                    GameState.setQtdMao(rs1.getInt(4));
                    GameState.setQtdMaoDescarte(rs1.getInt(5));
                    GameState.setpontosAtuais(rs1.getLong(6));
                    GameState.setAnteAtual(rs1.getInt(7));
                    GameState.setPosBlindAtual(rs1.getInt(8));
                    GameState.setLimiteCoringas(rs1.getInt(9));
                    GameState.setLimiteConsumiveis(rs1.getInt(10));
                    GameState.setTipoDeOrganizacao(rs1.getString(11));
                    GameState.setSeed(rs1.getString(12));
                    GameState.setRandom(new Random(GameState.getSeed().hashCode()));
                } // else {
                  // System.out.println("GameState não existe no banco de dados");
                  // }

            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao recuperar dados do gameState " + e.getMessage(), e);
        }
    }

    /*
     * Método que DELETA o gameState de um determinado usuário no BD
     */
    public static void deletar(Usuario usuario) {
        String sqlDelete = "DELETE FROM game_state WHERE id_usuario = ?";

        try (Connection conexao = Conexao.conectarBD();
                PreparedStatement stmtDelete = conexao.prepareStatement(sqlDelete);) {

            stmtDelete.setInt(1, usuario.getId_Usuario());
            stmtDelete.executeUpdate();

            // if (qtdAlterados > 0) {
            // System.out.println("gameState deletado com sucesso!");
            // } else {
            // System.out.println("Erro ao deletar gameState: Usuário não encontrado.");
            // }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar gameState " + e.getMessage(), e);
        }
    }

    /*
     * Método que verifica se já existe o gameState de determinado usuário no BD
     */
    public static boolean verificarGameStateExistente(Usuario usuario) {
        String sqlSelect = "SELECT * FROM game_state WHERE id_usuario = ?;";

        try (Connection conexao = Conexao.conectarBD();
                PreparedStatement stmtSelect = conexao.prepareStatement(sqlSelect);) {

            stmtSelect.setInt(1, usuario.getId_Usuario());

            try (ResultSet rs = stmtSelect.executeQuery()) {
                if (rs.next()) {
                    // System.out.println("GameState já existe (RECUPERANDO DADOS DO GAMESTATE)!");
                    return true;

                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao recuperar dados do Gamestate " + e.getMessage(), e);
        }

        // System.out.println("O GameState não existe!");
        return false;
    }
}
