package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;
import org.mindrot.jbcrypt.BCrypt;

import model.usuario.Usuario;

public class UsuarioDAO {

    public static void inserir(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome,senha,jogo_ativo) VALUES (?,?,?);";
        String senhaHash = BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt(12));

        try (Connection conexao = Conexao.conectarBD();
                PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, senhaHash);
            stmt.setBoolean(3, usuario.isJogoAtivo());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next())
                usuario.setId_Usuario(rs.getInt(1));
            System.out.println("Usuario inserido com sucesso");

        } catch (SQLException ex) {
            System.out.println("Erro ao inserir usuario " + ex.getMessage());
        }
    }

    public static void listar() {
        String sql = "SELECT * FROM usuario;";
        ArrayList<Usuario> users = new ArrayList<>();

        try (Connection conexao = Conexao.conectarBD();
                PreparedStatement stmt = conexao.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId_Usuario(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setJogoAtivo(rs.getBoolean("jogo_ativo"));
                users.add(usuario);
                if (usuario.isJogoAtivo()) {
                    System.out.printf("Usuario %s com o id %d está no estado de jogando\n", usuario.getNome(),
                            usuario.getId_Usuario());
                } else {
                    System.out.printf("Usuario %s com o id %d não está no estado de jogando\n", usuario.getNome(),
                            usuario.getId_Usuario());
                }

            }

        } catch (SQLException ex) {
            System.out.println("Erro ao listar usuarios " + ex.getMessage());
        }
    }

    public static Usuario selectUnico(String nome, String senha) {
        String sqlSelect = "SELECT * FROM usuario WHERE nome = ?;";

        try (Connection conexao = Conexao.conectarBD();
                PreparedStatement stmtSelect = conexao.prepareStatement(sqlSelect);) {

            stmtSelect.setString(1, nome);

            try (ResultSet rs = stmtSelect.executeQuery()) {
                if (rs.next()) {
                    String senhaHashBanco = rs.getString("senha");

                    if (BCrypt.checkpw(senha, senhaHashBanco)) {
                        Usuario user = new Usuario();
                        user.setId_Usuario(rs.getInt("id_usuario"));
                        user.setNome(rs.getString("nome"));
                        user.setSenha(rs.getString("senha"));
                        user.setJogoAtivo(rs.getBoolean("jogo_ativo"));
                        System.out.println("Dados do usuário recuperado com sucesso!");
                        return user;
                    } else {
                        System.out.println("Erro ao recuperar dados usuário: Senha incorreta.");
                    }

                } else {
                    System.out.println("Erro ao recuperar dados do usuário: Usuário não encontrado.");
                }
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao recuperar dados do usuario " + ex.getMessage());
        }
        return new Usuario();
    }

    public static boolean verificarUsuarioExistente(String nome, String senha) {
        String sqlSelect = "SELECT * FROM usuario WHERE nome = ?;";

        try (Connection conexao = Conexao.conectarBD();
                PreparedStatement stmtSelect = conexao.prepareStatement(sqlSelect);) {

            stmtSelect.setString(1, nome);

            try (ResultSet rs = stmtSelect.executeQuery()) {
                if (rs.next()) {
                    String senhaHashBanco = rs.getString("senha");

                    if (BCrypt.checkpw(senha, senhaHashBanco)) {
                        System.out.println("Usuário já existe (RECUPERANDO DADOS DO USUÁRIO)!");
                        return true;
                    } else {
                        System.out.println("Erro ao recuperar dados usuário: Senha incorreta.");
                    }

                } else {
                    System.out.println("Erro ao recuperar dados do usuário: Usuário não encontrado.");
                }
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao recuperar dados do usuario " + ex.getMessage());
        }
        System.out.println("O usuário não existe!");
        return false;
    }

    public static void deletar(String nome, String senha) {
        String sqlDelete = "DELETE FROM usuario WHERE id_usuario = ?";
        String sqlSelect = "SELECT id_usuario, senha FROM usuario WHERE nome = ?;";

        try (Connection conexao = Conexao.conectarBD();
                PreparedStatement stmtSelect = conexao.prepareStatement(sqlSelect);) {

            stmtSelect.setString(1, nome);

            try (ResultSet rs = stmtSelect.executeQuery()) {
                if (rs.next()) {
                    int idUsuario = rs.getInt("id_usuario");
                    String senhaHashBanco = rs.getString("senha");

                    if (BCrypt.checkpw(senha, senhaHashBanco)) {
                        try (PreparedStatement stmtDelete = conexao.prepareStatement(sqlDelete)) {
                            stmtDelete.setInt(1, idUsuario);
                            stmtDelete.executeUpdate();
                            System.out.println("Usuário deletado com sucesso!");
                        }
                    } else {
                        System.out.println("Erro ao deletar usuário: Senha incorreta.");
                    }

                } else {
                    System.out.println("Erro ao deletar usuário: Usuário não encontrado.");
                }
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao deletar usuario " + ex.getMessage());
        }
    }

    public static void updateStatusJogo(Usuario usuario, Boolean novoStatusJogo) {
        String sqlUpdate = "UPDATE usuario SET jogo_ativo = ? WHERE id_usuario = ?";

        try (Connection conexao = Conexao.conectarBD();
                PreparedStatement stmtUpdate = conexao.prepareStatement(sqlUpdate);) {

            stmtUpdate.setBoolean(1, novoStatusJogo);
            stmtUpdate.setInt(2, usuario.getId_Usuario());

            int linhasAfetadas = stmtUpdate.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Status do jogo atualizado com sucesso!");
                usuario.setJogoAtivo(novoStatusJogo);
            } else {
                System.out.println("Erro ao atualizar status: Usuário não encontrado.");
            }
            
            stmtUpdate.executeUpdate();
            System.out.println("Usuário atualizado com sucesso!");

        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar usuario " + ex.getMessage());
        }
    }

    public static void updateSenha(Usuario usuario, String senhaNova) {
        String sqlUpdate = "UPDATE usuario SET senha = ? WHERE id_usuario = ?";
        String sqlSelect = "SELECT id_usuario, senha FROM usuario WHERE nome = ?;";

        try (Connection conexao = Conexao.conectarBD();
                PreparedStatement stmtSelect = conexao.prepareStatement(sqlSelect);) {

            stmtSelect.setString(1, usuario.getNome());

            try (ResultSet rs = stmtSelect.executeQuery()) {
                if (rs.next()) {
                    int idUsuario = rs.getInt("id_usuario");
                    String senhaHashBanco = rs.getString("senha");

                    if (BCrypt.checkpw(usuario.getSenha(), senhaHashBanco)) {
                        String senhaHash = BCrypt.hashpw(senhaNova, BCrypt.gensalt(12));

                        try (PreparedStatement stmtUpdate = conexao.prepareStatement(sqlUpdate)) {
                            stmtUpdate.setString(1, senhaHash);
                            stmtUpdate.setInt(2, idUsuario);
                            stmtUpdate.executeUpdate();
                            System.out.println("Usuário atualizado com sucesso!");
                        }

                    } else {
                        System.out.println("Erro ao atualizar usuário: Senha incorreta.");
                    }

                } else {
                    System.out.println("Erro ao atualizar usuário: Usuário não encontrado.");
                }
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar usuario " + ex.getMessage());
        }
    }

}
