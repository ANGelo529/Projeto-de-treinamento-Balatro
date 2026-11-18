package jdbc;

import java.sql.Statement;
import java.util.Random;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.GameState;
import model.usuario.Usuario;

public class GameStateDAO {

    public static void inserirGeral(Usuario usuario) {
        String sql = "INSERT INTO game_state (" +
                " id_usuario, tamanho_mao, qtd_mao, qtd_descarte, " +
                " pontos_atuais, ante_atual, pos_blind_atual, " +
                " limite_coringa, limite_consumivel, tipo_organizacao,seed" +
                " ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" +
                " ON DUPLICATE KEY UPDATE" +
                " tamanho_mao = VALUES(tamanho_mao)," +
                " qtd_mao = VALUES(qtd_mao)," +
                " qtd_descarte = VALUES(qtd_descarte)," +
                " pontos_atuais = VALUES(pontos_atuais)," +
                " ante_atual = VALUES(ante_atual)," +
                " pos_blind_atual = VALUES(pos_blind_atual)," +
                " limite_coringa = VALUES(limite_coringa)," +
                " limite_consumivel = VALUES(limite_consumivel)," +
                " tipo_organizacao = VALUES(tipo_organizacao);";

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
            stmt.setString(11, GameState.getSeed() + "");
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next())
                GameState.setIdGameState(rs.getInt(1));

            stmt.executeUpdate();

            System.out.println("Dados inseridos com sucesso");

        } catch (SQLException ex) {
            System.out.println("Erro ao inserir gameState " + ex.getMessage());
        }
    }

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
                " tipo_organizacao = ?" +
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
            stmt.setString(9, "Naipe");
            stmt.setInt(10, 1);

            stmt.executeUpdate();

            System.out.println("Dados atualizados com sucesso");

        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar gameState " + ex.getMessage());
        }
    }

    public static void receberDados(Usuario usuario) {
        String sql = "SELECT * FROM game_state WHERE id_usuario = ?;";

        try (Connection conexao = Conexao.conectarBD();
                PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            stmt.setInt(1, usuario.getId_Usuario());

            try (ResultSet rs1 = stmt.executeQuery();) {
                if (rs1.next()) {
                    GameState.setIdGameState(rs1.getInt(1));
                    GameState.setTamanhoMao(rs1.getInt(2));
                    GameState.setQtdMao(rs1.getInt(3));
                    GameState.setQtdMaoDescarte(rs1.getInt(4));
                    GameState.setpontosAtuais(rs1.getLong(5));
                    GameState.setAnteAtual(rs1.getInt(6));
                    GameState.setPosBlindAtual(rs1.getInt(7));
                    GameState.setLimiteCoringas(rs1.getInt(8));
                    GameState.setLimiteConsumiveis(rs1.getInt(9));
                    GameState.setTipoDeOrganizacao(rs1.getString(10));
                    GameState.setRandom(new Random(rs1.getString(11).hashCode()));
                } else {
                    System.out.println("GameState não existe no banco de dados");
                }

            } catch (Exception e) {
                System.out.println("Erro ao recuperar dados do gameState " + e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println("Erro ao recuperar dados do gameState " + e.getMessage());
        }
    }

    public static void deletar(Usuario usuario) {
        String sqlDelete = "DELETE FROM game_state WHERE id_usuario = ?";

        try (Connection conexao = Conexao.conectarBD();
                PreparedStatement stmtDelete = conexao.prepareStatement(sqlDelete);) {

            stmtDelete.setInt(1, usuario.getId_Usuario());
            int qtdAlterados = stmtDelete.executeUpdate();

            if (qtdAlterados > 0) {
                System.out.println("gameState deletado com sucesso!");
            } else {
                System.out.println("Erro ao deletar gameState: Usuário não encontrado.");
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao deletar gameState " + ex.getMessage());
        }
    }

    public static boolean verificarGameStateExistente(Usuario usuario) {
        String sqlSelect = "SELECT * FROM game_state WHERE id_usuario = ?;";

        try (Connection conexao = Conexao.conectarBD();
                PreparedStatement stmtSelect = conexao.prepareStatement(sqlSelect);) {

            stmtSelect.setInt(1, usuario.getId_Usuario());

            try (ResultSet rs = stmtSelect.executeQuery()) {
                if (rs.next()) {
                    System.out.println("GameState já existe (RECUPERANDO DADOS DO GAMESTATE)!");
                    return true;

                } else {
                    System.out.println("Erro ao recuperar dados do GameState: GameState não encontrado.");
                }
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao recuperar dados do Gamestate " + ex.getMessage());
        }
        System.out.println("O GameState não existe!");
        return false;
    }
}
