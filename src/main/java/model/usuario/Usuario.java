package model.usuario;

import java.util.Scanner;

public class Usuario {
    private int id_Usuario;
    private String nome;
    private String senha;
    private boolean jogoAtivo = false;

    public Usuario(String nome, String senha, boolean jogoAtivo) {
        this.nome = nome;
        this.senha = senha;
        this.jogoAtivo = jogoAtivo;
    }
    
    public Usuario() {
    }

    public static Usuario definirUsuario(){
        Scanner LER = new Scanner(System.in);
        Usuario usuario = new Usuario();
        System.out.println("INFORME SEU NOME DE USUARIO E SUA SENHA");
        System.out.print("NOME DE USUÁRIO: ");
        usuario.setNome(LER.nextLine());
        System.out.print("SENHA: ");
        usuario.setSenha(LER.nextLine());
        LER.close();
        return usuario;
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
