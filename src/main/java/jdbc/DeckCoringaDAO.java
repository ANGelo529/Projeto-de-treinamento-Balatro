package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import factory.CoringaFactory;
import model.GameState;
import model.coringas.Coringa;
import model.coringas.DeckCoringa;

public class DeckCoringaDAO {

    public static void inserirCoringa(int idGameState, List<Coringa> maoCoringa) {
        String sql = "INSERT INTO coringa_instancia (id_game_state,id_ingame,nome,modificador,incremento) VALUES (?,?,?,?,?);";

        try (Connection conexao = Conexao.conectarBD();
                PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            conexao.setAutoCommit(false);
            for (Coringa corg : maoCoringa) {
                stmt.setInt(1, idGameState);
                stmt.setInt(2, corg.getIdJoker());
                stmt.setString(3, corg.getNome());
                stmt.setString(4, corg.getModificador().getNomeExibicao());
                stmt.setInt(5, corg.getIncremento());
                stmt.addBatch();
            }

            stmt.executeBatch();
            conexao.commit();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                int i = 0;
                while (rs.next() && maoCoringa.size() > i) {
                    int idGerado = rs.getInt(1);
                    maoCoringa.get(i).setIdBD(idGerado);
                    i++;
                }
            }
            System.out.println("Coringas inseridos com sucesso ");
        } catch (Exception e) {
            System.out.println("Erro ao inserir coringas " + e.getMessage());
        }

    }

    public static void updateCoringa(int idGameState, List<Coringa> maoCoringa) {
        String sql = "UPDATE coringa_instancia SET id_game_state = ?, id_ingame = ?, nome = ?,modificador = ?, incremento = ? WHERE id_coringa = ?;";

        try (Connection conexao = Conexao.conectarBD();
                PreparedStatement stmt = conexao.prepareStatement(sql);) {

            conexao.setAutoCommit(false);
            for (Coringa corg : maoCoringa) {
                stmt.setInt(1, idGameState);
                stmt.setInt(2, corg.getIdJoker());
                stmt.setString(3, corg.getNome());
                stmt.setString(4, corg.getModificador().getNomeExibicao());
                stmt.setInt(5, corg.getIncremento());
                stmt.setInt(6, corg.getIdBD());
                stmt.addBatch();
            }

            stmt.executeBatch();
            conexao.commit();

        } catch (Exception e) {
            System.out.println("Erro ao atualizar os coringas " + e.getMessage());
        }
    }

    public static void deletarCoringa(int idCorgDel, int idGameState) {
        String sql = "DELETE FROM coringa_instancia WHERE id_game_state = ? AND id_coringa = ?;";

        try (Connection conexao = Conexao.conectarBD();
                PreparedStatement stmt = conexao.prepareStatement(sql);) {
            stmt.setInt(1, idGameState);
            stmt.setInt(2, idCorgDel);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro ao deletar coringas " + e.getMessage());
        }
    }

    public static void receberDados(int idGameState) {
        String sql = "SELECT * FROM coringa_instancia WHERE id_game_state = ?;";
        ArrayList<Coringa> coringas = new ArrayList<>();
        try (Connection conexao = Conexao.conectarBD();
                PreparedStatement stmt = conexao.prepareStatement(sql);) {
            stmt.setInt(1, idGameState);

            try (ResultSet rs = stmt.executeQuery();) {
                while (rs.next()) {
                    Coringa corg = CoringaFactory.getCoringaPorId(rs.getInt("id_ingame"));
                    corg.setIdBD(rs.getInt("id_coringa"));
                    coringas.add(corg);
                }
                DeckCoringa.setMaoCoringa(coringas);

            } catch (Exception e) {
                System.out.println("Erro ao receber dados dos coringas " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Erro ao receber dados dos Coringas " + e.getMessage());
        }
    }
}
