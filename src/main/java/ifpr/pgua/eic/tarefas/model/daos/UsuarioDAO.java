package ifpr.pgua.eic.tarefas.model.daos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.model.entities.Usuario;

public interface UsuarioDAO {

    public Resultado criar(Usuario usuario);

    public Resultado listar();

    public Resultado editar(int id, Usuario novo);

    public Resultado excluir(int id);

    public Resultado buscarPorId(int id);
    
}
