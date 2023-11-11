package ifpr.pgua.eic.tarefas.model.daos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.model.entities.Autor;

public interface AutorDAO {

    public Resultado criar(Autor autor);

    public Resultado listar();

    public Resultado buscarPorId(int id);

    public Resultado buscarAutorLivro(int LivroId);

    public Resultado atualizar(int id, Autor novo);

    public Resultado excluir(int id);


    
}