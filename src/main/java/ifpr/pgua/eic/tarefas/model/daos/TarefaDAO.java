package ifpr.pgua.eic.tarefas.model.daos;

import java.sql.Date;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.model.entities.Tarefa;

public interface TarefaDAO {
    public Resultado criar(Tarefa tarefa);

    public Resultado listar();

    public Resultado editar(int id, Tarefa nova);

    public Resultado excluir(int id);
}
