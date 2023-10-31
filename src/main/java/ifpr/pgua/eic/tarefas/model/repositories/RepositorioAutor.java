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

    public Resultado criarAutor(String nome){
        if(nome.isEmpty() || nome.isBlank()){
            return Resultado.erro("Nome inválido!");
        }

        Autor autor = new Autor(nome);

        return dao.criar(autor);
    }

    public Resultado listarAutor(){
        return dao.listar();
    }

    
}
