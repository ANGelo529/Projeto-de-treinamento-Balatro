package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.GameState;
import model.cartas.Carta;
import model.cartas.DeckCarta;
import model.enums.Naipe;
import model.enums.OrdemDeValor;

public class DeckCartaDao {

    public static void inserirBaralho() {
        String sql = "INSERT INTO carta_instancia (id_game_state,ordem_de_valor,naipe,melhoria,selo,edicao,tipo_de_carta,posicao_carta) VALUES (?,?,?,?,?,?,?,?);";
        try (Connection conexao = Conexao.conectarBD();
                PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            conexao.setAutoCommit(false);
            ArrayList<Carta> cartasInseridas = new ArrayList<>();
            for (Naipe naipe : Naipe.values()) {
                ArrayList<Carta> cartas = DeckCarta.getBaralho().get(naipe);
                if (cartas == null)
                    continue;
                for (Carta carta : cartas) {
                    stmt.setInt(1, GameState.getIdGameState());
                    stmt.setString(2, carta.getOrdemDeValor().getNomeSimplificado());
                    stmt.setString(3, carta.getNaipe().getNomeExibicao());
                    stmt.setString(4, carta.getMelhoria());
                    stmt.setString(5, carta.getSelo());
                    stmt.setString(6, carta.getEdicao());
                    stmt.setString(7, carta.getTipoDeCarta());
                    stmt.setString(8, "BARALHO");
                    stmt.addBatch();
                    cartasInseridas.add(carta);
                }
            }

            stmt.executeBatch();
            conexao.commit();

            try (ResultSet rs = stmt.getGeneratedKeys();) {
                int i = 0;
                while (rs.next() && i < cartasInseridas.size()) {
                    int idGerado = rs.getInt(1);
                    cartasInseridas.get(i).setIdCarta(idGerado);
                    i++;
                }
            }

            System.out.println("Baralho padrão inserido com sucesso! Total de cartas: " + cartasInseridas.size());

        } catch (Exception e) {
            System.out.println("Erro ao inserir as cartas do Baralho " + e.getMessage());
        }
    }

    public static void updateMaoJogador() {
        String sql = "UPDATE carta_instancia SET id_game_state = ?,ordem_de_valor = ?, naipe = ?,melhoria = ?, selo = ?,edicao = ?, tipo_de_carta = ?, posicao_carta = ? WHERE id_carta_instancia = ?";

        try (Connection conexao = Conexao.conectarBD();
                PreparedStatement stmt = conexao.prepareStatement(sql);) {

            conexao.setAutoCommit(false);

            for (Carta carta : DeckCarta.getMaoJogador()) {
                stmt.setInt(1, GameState.getIdGameState());
                stmt.setString(2, carta.getOrdemDeValor().getNomeSimplificado());
                stmt.setString(3, carta.getNaipe().getNomeExibicao());
                stmt.setString(4, carta.getMelhoria());
                stmt.setString(5, carta.getSelo());
                stmt.setString(6, carta.getEdicao());
                stmt.setString(7, carta.getTipoDeCarta());
                stmt.setString(8, "MAOJOGADOR");
                stmt.setInt(9, carta.getIdCarta());
                stmt.addBatch();
            }

            stmt.executeBatch();
            conexao.commit();

            System.out.println("Dados atualizados do baralho (agora na mão jogada) com sucesso");

        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar cartas do baralho (para ir para a mão jogada)  " + ex.getMessage());
        }
    }

    public static void updateCartasDescartadas() {
        String sql = "UPDATE carta_instancia SET id_game_state = ?,ordem_de_valor = ?, naipe = ?,melhoria = ?, selo = ?,edicao = ?, tipo_de_carta = ?, posicao_carta = ? WHERE id_carta_instancia = ?";

        try (Connection conexao = Conexao.conectarBD();
                PreparedStatement stmt = conexao.prepareStatement(sql);) {

            conexao.setAutoCommit(false);

            for (Carta carta : DeckCarta.getMaoJogador()) {
                stmt.setInt(1, GameState.getIdGameState());
                stmt.setString(2, carta.getOrdemDeValor().getNomeSimplificado());
                stmt.setString(3, carta.getNaipe().getNomeExibicao());
                stmt.setString(4, carta.getMelhoria());
                stmt.setString(5, carta.getSelo());
                stmt.setString(6, carta.getEdicao());
                stmt.setString(7, carta.getTipoDeCarta());
                stmt.setString(8, "DESCARTADAS");
                stmt.setInt(9, carta.getIdCarta());
                stmt.addBatch();
            }

            stmt.executeBatch();
            conexao.commit();

            System.out.println("Dados atualizados do baralho (agora na mão descartes) com sucesso");

        } catch (SQLException ex) {
            System.out
                    .println("Erro ao atualizar cartas do baralho (para ir para a mão descartes)  " + ex.getMessage());
        }
    }

    public static void deletarBaralho() {
        String sqlDelete = "DELETE FROM carta_instancia WHERE id_game_state = ?";

        try (Connection conexao = Conexao.conectarBD();
                PreparedStatement stmtDelete = conexao.prepareStatement(sqlDelete);) {

            stmtDelete.setInt(1, GameState.getIdGameState());
            int qtdAlterados = stmtDelete.executeUpdate();

            if (qtdAlterados > 0) {
                System.out.println("Baralho deletado com sucesso!");
            } else {
                System.out.println("Erro ao deletar Baralho: Baralho não encontrado.");
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao deletar o Baralho " + ex.getMessage());
        }
    }

    public static void receberBaralho() {
        String sql = "SELECT * FROM carta_instancia WHERE id_game_state = ?;";

        try (Connection conexao = Conexao.conectarBD();
                PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, GameState.getIdGameState());

            try (ResultSet rs = stmt.executeQuery();) {
                while (rs.next()) {
                    Carta carta = new Carta();
                    carta.setIdCarta(rs.getInt(1));
                    carta.setOrdemDeValor(strParaOrdemValor(rs.getString(3)));
                    carta.setNaipe(strParaNaipe(rs.getString(4)));
                    carta.setMelhoria(rs.getString(5));
                    carta.setSelo(rs.getString(6));
                    carta.setEdicao(rs.getString(7));
                    carta.setTipoDeCarta(rs.getString(8));
                    carta.setPosicaoCarta(rs.getString(9));

                    if (carta.getPosicaoCarta().equals("BARALHO")) {
                        DeckCarta.getBaralho().get(carta.getNaipe()).add(carta);
                    } else if (carta.getPosicaoCarta().equals("MAOJOGADOR")) {
                        DeckCarta.getMaoJogador().add(carta);
                    } else if (carta.getPosicaoCarta().equals("DESCARTADAS")) {
                        DeckCarta.getCartasDescartadas().add(carta);
                    }
                }
                
                System.out.println("Todas as cartas já foram lidas ");

            } catch (Exception e) {
                System.out.println("Erro ao recuperar dados das cartas " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Erro ao recuperar dados das cartas " + e.getMessage());
        }
    }

    /*
     * Esse método serve como um método auxiliar para o recebimento de dados do
     * banco sql
     * Trocando atavés de uma String para o Enum OrdemDeValor.
     */
    private static OrdemDeValor strParaOrdemValor(String strOrdemValor) {
        for (OrdemDeValor ODV : DeckCarta.getListaordemdevalor()) {
            if (strOrdemValor.equals(ODV.getNomeSimplificado())) {
                return ODV;
            }
        }
        System.out.println("String para troca não é um valor compátivel");
        return null;
    }

    /*
     * Esse método serve como um método auxiliar para o recebimento de dados do
     * banco sql
     * Trocando atavés de uma String para o Enum Naipe.
     */
    private static Naipe strParaNaipe(String strOrdemValor) {
        for (Naipe ODV : DeckCarta.getListanaipes()) {
            if (strOrdemValor.equals(ODV.getNomeExibicao())) {
                return ODV;
            }
        }

        System.out.println("String para troca não é um valor compátivel");
        return null;
    }

    public static boolean verificarExistente() {
        String sqlSelect = "SELECT * FROM carta_instancia WHERE id_game_state = ?;";

        try (Connection conexao = Conexao.conectarBD();
                PreparedStatement stmtSelect = conexao.prepareStatement(sqlSelect);) {

            stmtSelect.setInt(1, GameState.getIdGameState());

            try (ResultSet rs = stmtSelect.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Alguma carta já existe (RECUPERANDO DADOS DAS CARTAS)!");
                    return true;

                }
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao recuperar dados das cartas: " + ex.getMessage());
        }

        System.out.println("As cartas não foram inseridas ainda!");
        return false;
    }
}
