package model.usuario;

public class Usuario {
    private int id_Usuario;
    private String nome;
    private String senha;
    private boolean jogoAtivo;

    public Usuario(String nome, String senha, boolean jogoAtivo) {
        this.nome = nome;
        this.senha = senha;
        this.jogoAtivo = jogoAtivo;
    }

    public Usuario() {
    }

    public int getId_Usuario() {
        return id_Usuario;
    }

    public void setId_Usuario(int id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isJogoAtivo() {
        return jogoAtivo;
    }

    public void setJogoAtivo(boolean jogoAtivo) {
        this.jogoAtivo = jogoAtivo;
    }
}
