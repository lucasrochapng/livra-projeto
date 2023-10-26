package ifpr.pgua.eic.tarefas.model.repositories;

import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.model.daos.UsuarioDAO;
import ifpr.pgua.eic.tarefas.model.entities.Usuario;

public class RepositorioUsuario {
    
    private ArrayList<Usuario> usuarios;

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
            return Resultado.erro("Lamentamos informar que não podemos concluir o seu processo de cadastro, uma vez que a legislação vigente exige que os usuários sejam maiores de idade para utilizar nossos serviços!");
        }

        Usuario usuario = new Usuario(nome, nomeUsuario, senha, telefone, idade);
        return dao.criar(usuario);
    }

}

/*
    public Resultado listarCategoria(){
        return dao.listar();
    }
 */
