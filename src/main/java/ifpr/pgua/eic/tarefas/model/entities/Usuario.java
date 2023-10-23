package ifpr.pgua.eic.tarefas.model.entities;

import java.util.ArrayList;

public class Usuario {
    
    private int id;
    private String nome;
    private String nomeUsuario;
    private String senha;
    private int telefone;
    private int idade;
    private ArrayList<Livro> meusLivros = new ArrayList<>();


    public Usuario(int id, String nome, String nomeUsuario, String senha, int telefone, int idade) {
        this.id = id;
        this.nome = nome;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.telefone = telefone;
        this.idade = idade;
    }

    public Usuario(String nome, String nomeUsuario, String senha, int telefone, int idade){
        this.nome = nome;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.telefone = telefone;
        this.idade = idade;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }
    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getTelefone() {
        return telefone;
    }
    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }

    public ArrayList<Livro> getMeusLivros() {
        return meusLivros;
    }
    public void setMeusLivros(ArrayList<Livro> meusLivros) {
        this.meusLivros = meusLivros;
    }

    @Override
    public String toString() {
        return nome;
    }



}
