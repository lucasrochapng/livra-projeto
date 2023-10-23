package ifpr.pgua.eic.tarefas.model.repositories;

import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.model.daos.CategoriaDAO;
import ifpr.pgua.eic.tarefas.model.entities.Categoria;

public class RepositorioCategoria {
    
    private ArrayList<Categoria> categorias;

    private CategoriaDAO dao;

    public RepositorioCategoria(CategoriaDAO dao) {
        categorias = new ArrayList<>();
        this.dao = dao;
    }

    public Resultado criarCategoria(String nome, String descricao){
        if(nome.isEmpty() || nome.isBlank()){
            return Resultado.erro("Nome inválido!");
        }

        if(descricao.isEmpty() || descricao.isBlank()){
            return Resultado.erro("Descrição inválida!");
        }

        Categoria categoria = new Categoria(nome, descricao);

        return dao.criar(categoria);
    }

    public Resultado listarCategoria(){
        return dao.listar();
    }
}