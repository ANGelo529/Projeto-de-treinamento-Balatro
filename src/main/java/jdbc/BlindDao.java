package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import factory.BlindFactory;
import factory.CoringaFactory;
import model.GameState;
import model.blinds.Ante;
import model.blinds.Blind;
import model.blinds.BossBlind;
import model.coringas.Coringa;
import model.coringas.DeckCoringa;

public class BlindDao {

    public static void inserirBlind(int idGameState, BossBlind blind) {
        String sql = "INSERT INTO boss_blind_instancia (id_game_state,id_ingame,nome) VALUES (?,?,?);";

        try (Connection conexao = Conexao.conectarBD();
                PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            stmt.setInt(1, idGameState);
            stmt.setInt(2, blind.getIdIngame());
            stmt.setString(3, blind.getNome());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGerado = rs.getInt(1);
                    blind.setIdBlind(idGerado);
                }
            }

            System.out.println("Blinds inseridos com sucesso ");
        } catch (Exception e) {
            System.out.println("Erro ao inserir os blinds " + e.getMessage());
        }

    }

    public static void updateBlind(int idGameState, BossBlind blind) {
        String sql = "UPDATE boss_blind_instancia SET id_game_state = ?, id_ingame = ?, nome = ? WHERE id_boss_blind = ?;";

        try (Connection conexao = Conexao.conectarBD();
                PreparedStatement stmt = conexao.prepareStatement(sql);) {

            stmt.setInt(1, idGameState);
            stmt.setInt(2, blind.getIdIngame());
            stmt.setString(3, blind.getNome());
            stmt.setInt(4, blind.getIdBlind());
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Erro ao atualizar os blinds " + e.getMessage());
        }
    }

    public static void deletarBlind(int idGameState, BossBlind blind) {
        String sql = "DELETE FROM boss_blind_instancia WHERE id_game_state = ? AND id_boss_blind = ?;";

        try (Connection conexao = Conexao.conectarBD();
                PreparedStatement stmt = conexao.prepareStatement(sql);) {

            stmt.setInt(1, idGameState);
            stmt.setInt(2, blind.getIdBlind());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro ao deletar os blinds " + e.getMessage());

        }
    }

    public static void receberDados(int idGameState, int anteAtual) {
        String sql = "SELECT * FROM boss_blind_instancia WHERE id_game_state  = ?;";
        Ante.atualizarBlind(anteAtual);
        try (Connection conexao = Conexao.conectarBD();
                PreparedStatement stmt = conexao.prepareStatement(sql);) {
            stmt.setInt(1, idGameState);

            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    BossBlind blind = BlindFactory.criarBlind(rs.getInt("id_ingame"));
                    blind.setIdBlind(rs.getInt("id_boss_blind"));
                    Ante.getBlindsAtuais()[2] = blind;
                }

            } catch (Exception e) {
                System.out.println("Erro ao receber dados dos blinds " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Erro ao receber dados dos blinds " + e.getMessage());
        }
    }
}
