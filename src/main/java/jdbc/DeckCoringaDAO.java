package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import factory.CoringaFactory;
import model.coringas.Coringa;
import model.coringas.DeckCoringa;
import model.enums.Modificador;


public class DeckCoringaDAO {

    /*
     * Método que insere todos os coringas que o jogador possue no BD
     */
    public static void inserirCoringa(int idGameState, List<Coringa> maoCoringa) {
        maoCoringaNotNull(maoCoringa);
        String sql = "INSERT INTO coringa_instancia (id_game_state,id_ingame,nome,modificador,incremento) VALUES (?,?,?,?,?);";

        try (Connection conexao = Conexao.conectarBD();
                PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            conexao.setAutoCommit(false);
            try {
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
                        maoCoringa.get(i).setIdBD(rs.getInt(1));
                        i++;
                    }
                }

                // System.out.println("Coringas inseridos com sucesso ");
            } catch (Exception e) {
                try {
                    conexao.rollback();
                } catch (SQLException rollbackEx) {
                    e.addSuppressed(rollbackEx);
                }
                throw e;
            } finally {
                conexao.setAutoCommit(true);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir coringas no banco: " + e.getMessage(), e);
        }

    }

    /*
     * Método que atualiza todos os coringas que estão na maoCoringa no BD
     * Ele pega em base a mão de coringas atual pelo jogador para altera-las nos BD
     */
    public static void updateCoringa(int idGameState, List<Coringa> maoCoringa) {
        maoCoringaNotNull(maoCoringa);
        String sql = "UPDATE coringa_instancia SET id_game_state = ?, id_ingame = ?, nome = ?,modificador = ?, incremento = ? WHERE id_coringa = ?;";

        try (Connection conexao = Conexao.conectarBD();
                PreparedStatement stmt = conexao.prepareStatement(sql);) {

            conexao.setAutoCommit(false);

            try {
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
                try {
                    conexao.rollback();
                } catch (SQLException rollbackEx) {
                    e.addSuppressed(rollbackEx);
                }
                throw e;
            } finally {
                conexao.setAutoCommit(true);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar os coringas no banco: " + e.getMessage(), e);
        }
    }

    /*
     * Método que DELETA um coringa específico que está no BD
     * Ele pega em base o id do coringa que quero deletar para assim deletar no BD
     */
    public static void deletarCoringa(int idCorgDel, int idGameState) {
        String sql = "DELETE FROM coringa_instancia WHERE id_game_state = ? AND id_coringa = ?;";

        try (Connection conexao = Conexao.conectarBD();
                PreparedStatement stmt = conexao.prepareStatement(sql);) {

            stmt.setInt(1, idGameState);
            stmt.setInt(2, idCorgDel);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar coringa no banco: " + e.getMessage(), e);
        }
    }

    /*
     * Método que recebe os dados coringas que estão no BD
     * Ele pega em base o idInGame do coringa
     * Para assim recriar ele usando a CoringaFactory
     */
    public static void receberDados(int idGameState) {
        String sql = "SELECT * FROM coringa_instancia WHERE id_game_state = ? ORDER BY id_coringa ASC;";
        List<Coringa> coringas = new ArrayList<>();

        try (Connection conexao = Conexao.conectarBD();
                PreparedStatement stmt = conexao.prepareStatement(sql);) {
            stmt.setInt(1, idGameState);

            try (ResultSet rs = stmt.executeQuery();) {
                while (rs.next()) {
                    Coringa corg = CoringaFactory.getCoringaPorId(rs.getInt("id_ingame"));
                    if (corg != null) {
                        corg.setModificador(getModificadorCerto(rs.getString("modificador")));
                        corg.setIncremento(rs.getInt("incremento"));
                        corg.setIdBD(rs.getInt("id_coringa"));
                        coringas.add(corg);
                    }

                }
                DeckCoringa.setMaoCoringa(new ArrayList<>(coringas));

            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao receber dados dos coringas: " + e.getMessage(), e);
        }
    }

    /*
     * Simplificação para recuperar o modificar de maneira correta
     * Do Coringa que está no BD
     */
    private static Modificador getModificadorCerto(String strMod) {
        for (Modificador mod : Modificador.values()) {
            if (strMod.equals(mod.getNomeExibicao())) {
                return mod;
            }
        }
        return null;
    }

    /*
     * Simplificação para evitar repetir código
     */
    private static void maoCoringaNotNull(List<Coringa> maoCoringa) {
        if (maoCoringa == null || maoCoringa.isEmpty()) {
            return;
        }
    }
}
