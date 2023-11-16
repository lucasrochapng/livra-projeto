package ifpr.pgua.eic.tarefas.model.repositories;

import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.model.daos.AutorDAO;
import ifpr.pgua.eic.tarefas.model.entities.Autor;

public class RepositorioAutor {

    private ArrayList<Autor> autores;

    private AutorDAO dao;

    public RepositorioAutor(AutorDAO dao){
        autores = new ArrayList<>();
        this.dao = dao;
    }

    public Resultado cadastrarAutor(String nome){

        if(nome.isEmpty() || nome.isBlank()){
            return Resultado.erro("Digite o nome do autor para realizar o cadastro!");
        }

        Autor autor = new Autor(nome);
        Resultado resultado = dao.criar(autor);
        return resultado;
    }

    public Resultado alterarAutor(int id, String nome){

        if(nome.isEmpty() || nome.isBlank()){
            return Resultado.erro("Digite o nome do autor para realizar a atualização!");
        }

        Autor novo = new Autor(id, nome);

        Resultado resultado = dao.atualizar(id, novo);

        return resultado;
    }

    public Resultado listarAutor(){
        return dao.listar();
    }


    

    
}
