package ifpr.pgua.eic.tarefas.model.repositories;

import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.model.daos.UsuarioDAO;
import ifpr.pgua.eic.tarefas.model.entities.Usuario;

public class RepositorioUsuario {
    
    private ArrayList<Usuario> usuarios;
    //private Usuario logado;
    private Usuario contaLogada;

    private UsuarioDAO dao;

    public RepositorioUsuario(UsuarioDAO dao){
        usuarios = new ArrayList<>();
        this.dao = dao;
    }

    public Resultado criarUsuario(String nome, String nomeUsuario, String senha, int telefone, int idade){

        if(nome.isEmpty() || nome.isBlank()){
            return Resultado.erro("Nome inválido!");
        }

        if(nomeUsuario.isEmpty() || nomeUsuario.isBlank()){
            return Resultado.erro("Nome de usuário inválido!");
        }

        if(senha.isEmpty() || senha.isBlank()){
            return Resultado.erro("Senha inválida!");
        }

        if(telefone <= 0){
            return Resultado.erro("Telefone inválido!");
        }

        if(idade <= 17){
            return Resultado.erro("Idade inválida!");
        }

        Usuario usuario = new Usuario(nome, nomeUsuario, senha, telefone, idade);
        return dao.criar(usuario);
    }

    public Resultado alterarUsuario(int id, String nome, String nomeUsuario, String senha, int telefone, int idade) {
        Usuario novo = new Usuario(id, nome, nomeUsuario, senha, telefone, idade);
        Resultado resultado = dao.editar(id, novo);
        return resultado;
    }

    public Resultado login(String nomeUsuario, String senha){
        if(nomeUsuario.isEmpty() || nomeUsuario.isBlank()) {
            return Resultado.erro("Nome Inválido!");
        }
        if(senha.isEmpty() || senha.isBlank()) {
            return Resultado.erro("Senha Inválida!");
        }
        Resultado resultado = dao.logar(nomeUsuario, senha);
        if(resultado.foiSucesso()) {
            setContaLogada(resultado);
        }
        return resultado;
    }

    public void setContaLogada(Resultado resultado){
        contaLogada = (Usuario) resultado.comoSucesso().getObj();
    }

    public Usuario contaLogada(){
        return contaLogada;
    }

    











}

/*
    public Resultado listarCategoria(){
        return dao.listar();
    }
 */
