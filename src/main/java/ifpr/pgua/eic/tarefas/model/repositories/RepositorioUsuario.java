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
            return Resultado.erro("O nome não pode estar em branco!");
        }

        if(nomeUsuario.isEmpty() || nomeUsuario.isBlank()){
            return Resultado.erro("O nome de usuário não pode estar em branco!");
        }

        if(senha.isEmpty() || senha.isBlank()){
            return Resultado.erro("A senha não pode estar em branco!");
        }

        if(telefone == 0){
            return Resultado.erro("O telefone não pode estar em branco!");
        }

        if(idade <= 17 || idade == 0){
            return Resultado.erro("Usuários menores de idade não podem se registrar!");
        }

        Usuario usuario = new Usuario(nome, nomeUsuario, senha, telefone, idade);
        return dao.criar(usuario);
    }

    public Resultado alterarUsuario(int id, String nome, String nomeUsuario, String senha, int telefone, int idade) {
        
        if(nome.isEmpty() || nome.isBlank()){
            return Resultado.erro("O nome não pode estar em branco!");
        }

        if(nomeUsuario.isEmpty() || nomeUsuario.isBlank()){
            return Resultado.erro("O nome de usuário não pode estar em branco!");
        }

        if(senha.isEmpty() || senha.isBlank()){
            return Resultado.erro("A senha não pode estar em branco!");
        }

        if(telefone == 0){
            return Resultado.erro("O telefone não pode estar em branco!");
        }

        if(idade <= 17 || idade == 0){
            return Resultado.erro("Usuários menores de idade não podem se registrar!");
        }
        
        Usuario novo = new Usuario(id, nome, nomeUsuario, senha, telefone, idade);
        Resultado resultado = dao.editar(id, novo);
        return resultado;
    }

    public Resultado login(String nomeUsuario, String senha){

        if(nomeUsuario.isEmpty() || nomeUsuario.isBlank()) {
            return Resultado.erro("Nome de Usuário Inválido!");
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


