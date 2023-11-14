package ifpr.pgua.eic.tarefas.model.daos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.model.entities.Livro;

public interface LivroDAO {
    
    public Resultado criar(Livro livro);

    public Resultado listar();

    public Resultado listarPorContato(String contato);

    public Resultado buscarPorId(int id);

    public Resultado atualizar(int id, Livro novo);

    public Resultado excluir(int id);

    public Resultado buscarLivro(String titulo);


}
