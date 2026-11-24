package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import factory.BlindFactory;
import model.blinds.Ante;
import model.blinds.Blind;
import model.blinds.BossBlind;

public class BlindDao {

    public static void inserirBlind(int idGameState, BossBlind blind) {
        blindNotNull(blind);

        String sql = "INSERT INTO boss_blind_instancia (id_game_state,id_ingame,nome) VALUES (?,?,?);";

        try (Connection conexao = Conexao.conectarBD();
                PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            stmt.setInt(1, idGameState);
            stmt.setInt(2, blind.getIdIngame());
            stmt.setString(3, blind.getNome());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    blind.setIdBlind(rs.getInt(1));
                }
            }

            // System.out.println("Blinds inseridos com sucesso ");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir o blind no banco: " + e.getMessage(), e);
        }

    }

    public static void updateBlind(int idGameState, BossBlind blind) {
        blindNotNull(blind);
        String sql = "UPDATE boss_blind_instancia SET id_game_state = ?, id_ingame = ?, nome = ? WHERE id_boss_blind = ?;";

        try (Connection conexao = Conexao.conectarBD();
                PreparedStatement stmt = conexao.prepareStatement(sql);) {

            stmt.setInt(1, idGameState);
            stmt.setInt(2, blind.getIdIngame());
            stmt.setString(3, blind.getNome());
            stmt.setInt(4, blind.getIdBlind());
            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o blind no banco: " + e.getMessage(), e);
        }
    }

    public static void deletarBlind(int idGameState, BossBlind blind) {
        blindNotNull(blind);
        String sql = "DELETE FROM boss_blind_instancia WHERE id_game_state = ? AND id_boss_blind = ?;";

        try (Connection conexao = Conexao.conectarBD();
                PreparedStatement stmt = conexao.prepareStatement(sql);) {

            stmt.setInt(1, idGameState);
            stmt.setInt(2, blind.getIdBlind());
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar o blind no banco: " + e.getMessage(), e);

        }
    }

    public static void receberDados(int idGameState, int anteAtual) {
        String sql = "SELECT * FROM boss_blind_instancia WHERE id_game_state  = ? ORDER BY id_boss_blind DESC LIMIT 1;";

        Ante.atualizarBlind(anteAtual);

        try (Connection conexao = Conexao.conectarBD();
                PreparedStatement stmt = conexao.prepareStatement(sql);) {
            stmt.setInt(1, idGameState);

            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    BossBlind blind = BlindFactory.criarBlind(rs.getInt("id_ingame"));
                    blind.setIdBlind(rs.getInt("id_boss_blind"));

                    Blind[] blindsAtuais = Ante.getBlindsAtuais();
                    if (blindsAtuais != null && blindsAtuais.length > 2) {
                        blindsAtuais[2] = blind;
                    }

                }

            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao receber dados dos blinds: " + e.getMessage(), e);
        }
    }

    private static void blindNotNull(BossBlind blind) {
        if (blind == null) {
            throw new IllegalArgumentException("BossBlind não pode ser nulo.");
        }
    }
}
