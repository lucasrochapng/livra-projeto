package ifpr.pgua.eic.tarefas.model.repositories;

import java.util.ArrayList;
import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.model.daos.AutorDAO;
import ifpr.pgua.eic.tarefas.model.daos.LivroDAO;
import ifpr.pgua.eic.tarefas.model.entities.Autor;
import ifpr.pgua.eic.tarefas.model.entities.Livro;

public class RepositorioLivro {

    private ArrayList<Livro> livros;

    private LivroDAO dao;
    private AutorDAO autorDAO;

    public RepositorioLivro(LivroDAO dao, AutorDAO autorDAO) {
        livros = new ArrayList<>();
        this.dao = dao;
        this.autorDAO = autorDAO;
    }

    public String cadastrarLivro(String titulo, Autor autor, String genero, String descricao) {
        Livro livro = new Livro(titulo, autor, genero, descricao);
        Resultado resultado = dao.criar(livro);
        return resultado.getMsg();
    }

    public String alterarLivro(int id, String titulo, Autor autor, String genero, String descricao){
        Livro novo = new Livro(titulo, autor, genero, descricao);
        Resultado resultado = dao.atualizar(id, novo);
        return resultado.getMsg();
    }

    public Resultado listar(){
        return dao.listar();

    }


    
}