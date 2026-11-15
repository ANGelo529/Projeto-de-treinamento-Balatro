package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.GameState;
import model.usuario.Usuario;

public class GameStateDAO {

    public static void inserirGeral(Usuario usuario) {
        String sql = "INSERT INTO game_state (" +
                " id_usuario, tamanho_mao, qtd_mao, qtd_descarte, " +
                " pontos_atuais, ante_atual, pos_blind_atual, " +
                " limite_coringa, limite_consumivel, tipo_organizacao" +
                " ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" +
                " ON DUPLICATE KEY UPDATE" +
                " tamanho_mao = VALUES(tamanho_mao)," +
                " qtd_mao = VALUES(qtd_mao)," +
                " qtd_descarte = VALUES(qtd_descarte)," +
                " pontos_atuais = VALUES(pontos_atuais)," +
                " ante_atual = VALUES(ante_atual)," +
                " pos_blind_atual = VALUES(pos_blind_atual)," +
                " limite_coringa = VALUES(limite_coringa)," +
                " limite_consumivel = VALUES(limite_consumivel)," +
                " tipo_organizacao = VALUES(tipo_organizacao)";

        try (Connection conexao = Conexao.conectarBD();
                PreparedStatement stmt = conexao.prepareStatement(sql);) {

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
            stmt.executeUpdate();

            System.out.println("Dados inseridos com sucesso");

        } catch (SQLException ex) {
            System.out.println("Erro ao inserir gameState " + ex.getMessage());
        }
    }

}
