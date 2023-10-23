package ifpr.pgua.eic.tarefas.model.daos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.model.entities.Categoria;

public interface CategoriaDAO {
    public Resultado criar(Categoria categoria);

    public Resultado listar();
    public Resultado buscarPorId(int id);

    public Resultado editar(int id, Categoria nova);

    public Resultado excluir(int id);
}
