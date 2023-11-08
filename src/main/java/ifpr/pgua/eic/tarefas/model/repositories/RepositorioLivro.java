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

    public Resultado cadastrarLivro(String titulo, Autor autor, String genero, String descricao) {
        Livro livro = new Livro(titulo, autor, genero, descricao);
        Resultado resultado = dao.criar(livro);
        return resultado;
    }

    public Resultado alterarLivro(int id, String titulo, Autor autor, String genero, String descricao){
        Livro novo = new Livro(titulo, autor, genero, descricao);
        Resultado resultado = dao.atualizar(id, novo);
        return resultado;
    }
    
    private Resultado montaLivro(Livro livro) {
        Resultado r2 = autorDAO.buscarAutorLivro(livro.getId());
        if (r2.foiErro()) {
            return r2;
        }
        Autor autor = (Autor) r2.comoSucesso().getObj();
        livro.setAutor(autor);

        return Resultado.sucesso("Livro montado", livro);
    }

    public Resultado getById(int livroId) {

        Resultado r0 = dao.buscarPorId(livroId);

        if(r0.foiSucesso()) {
            Livro livro = (Livro) r0.comoSucesso().getObj();
            return montaLivro(livro);
        }
        return r0;
    }

    public Resultado listar() {

        Resultado resultado = dao.listar();

        if (resultado.foiSucesso()) {
            List<Livro> lista = (List<Livro>) resultado.comoSucesso().getObj();
            

            for(Livro livro : lista) {

                Resultado r1 = montaLivro(livro);

                if(r1.foiErro()){
                    return r1;
                }
            }
        }
        return resultado;
    }
    

       
}