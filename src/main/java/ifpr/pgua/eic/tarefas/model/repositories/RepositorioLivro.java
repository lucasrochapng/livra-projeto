package ifpr.pgua.eic.tarefas.model.repositories;

import java.util.ArrayList;
import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.model.daos.AutorDAO;
import ifpr.pgua.eic.tarefas.model.daos.LivroDAO;
import ifpr.pgua.eic.tarefas.model.daos.UsuarioDAO;
import ifpr.pgua.eic.tarefas.model.entities.Autor;
import ifpr.pgua.eic.tarefas.model.entities.Livro;
import ifpr.pgua.eic.tarefas.model.entities.Usuario;

public class RepositorioLivro {

    private ArrayList<Livro> livros;

    private LivroDAO dao;
    private AutorDAO autorDAO;
    private UsuarioDAO usuarioDAO;

    public RepositorioLivro(LivroDAO dao, AutorDAO autorDAO, UsuarioDAO usuarioDAO) {
        livros = new ArrayList<>();
        this.dao = dao;
        this.autorDAO = autorDAO;
        this.usuarioDAO = usuarioDAO;
    }

    public Resultado cadastrarLivro(String titulo, Autor autor, String genero, String descricao, String contato, Usuario usuario) {
        
        if(titulo.isEmpty() || titulo.isBlank()){
            return Resultado.erro("Digite o título do livro para realizar o cadastro!");
        }

        if(genero.isEmpty() || genero.isBlank()){
            return Resultado.erro("Digite o gênero do livro para realizar o cadastro!");
        }

        if(descricao.isEmpty() || descricao.isBlank()){
            return Resultado.erro("Digite a descrição do livro para realizar o cadastro!");
        }

        if(contato.isEmpty() || contato.isBlank()){
            return Resultado.erro("Digite o contato do livro para realizar o cadastro!");
        }

        Livro livro = new Livro(titulo, autor, genero, descricao, contato, usuario);
        Resultado resultado = dao.criar(livro);
        return resultado;
    }

    public Resultado alterarLivro(int id, String titulo, Autor autor, String genero, String descricao, String contato, Usuario usuario){
        Livro novo = new Livro(id, titulo, autor, genero, descricao, contato, usuario);
        Resultado resultado = dao.atualizar(id, novo);
        return resultado;
    }
    
    private Resultado montaLivro(Livro livro) {
        Resultado r1 = usuarioDAO.buscarUsuarioLivro(livro.getId());
        if(r1.foiErro()) {
            return r1;
        }
        Usuario usuario = (Usuario) r1.comoSucesso().getObj();
        livro.setUsuario(usuario);

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

    public Resultado listarPorUsuario(int usuarioId){
        Resultado resultado = dao.listarPorUsuario(usuarioId);

        if(resultado.foiSucesso()) {
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

    /*
    public Resultado listarPorContato(String contato){

        Resultado resultado = dao.listarPorContato(contato);

        if(resultado.foiSucesso()) {
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
    */

    //buscar
    public Resultado buscarLivro(String titulo) {
        Resultado resultado = dao.buscarLivro(titulo);

        if(resultado.foiSucesso()) {
            List<Livro> lista = (List<Livro>) resultado.comoSucesso().getObj();

            for(Livro livro : lista){
                Resultado r1 = montaLivro(livro);

                if(r1.foiErro()){
                    return r1;
                }
            }
        }
        return resultado;
    }
   
    public Resultado excluirLivro(int id) {
        Resultado resultado = dao.excluir(id);
        return resultado;
    }

       
}