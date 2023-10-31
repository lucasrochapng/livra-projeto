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

    public String cadastrarAutor(String nome){

        Autor autor = new Autor(nome);
        Resultado resultado = dao.criar(autor);
        return resultado.getMsg();
    }

    public String alterarAutor(int id, String nome){
        Autor novo = new Autor(id, nome);

        Resultado resultado = dao.atualizar(id, novo);

        return resultado.getMsg();
    }

    public Resultado listarAutor(){
        return dao.listar();
    }

    
}
