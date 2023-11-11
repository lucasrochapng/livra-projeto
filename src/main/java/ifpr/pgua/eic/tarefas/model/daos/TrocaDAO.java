package ifpr.pgua.eic.tarefas.model.daos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.model.entities.Troca;

public interface TrocaDAO {

    public Resultado criar(Troca troca);

    public Resultado listar();

    
}
