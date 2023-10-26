package ifpr.pgua.eic.tarefas;

import ifpr.pgua.eic.tarefas.controllers.ListarLivros;
import ifpr.pgua.eic.tarefas.controllers.CadastrarLivro;
import ifpr.pgua.eic.tarefas.controllers.CadastrarUsuario;
import ifpr.pgua.eic.tarefas.controllers.CadastroCategoria;
import ifpr.pgua.eic.tarefas.controllers.CadastroTarefa;
import ifpr.pgua.eic.tarefas.controllers.EditarLivro;
import ifpr.pgua.eic.tarefas.controllers.EditarUsuario;
import ifpr.pgua.eic.tarefas.controllers.Home;
import ifpr.pgua.eic.tarefas.controllers.ListarCategoria;
import ifpr.pgua.eic.tarefas.controllers.ListarTarefa;
import ifpr.pgua.eic.tarefas.controllers.Principal;
import ifpr.pgua.eic.tarefas.model.daos.CategoriaDAO;
import ifpr.pgua.eic.tarefas.model.daos.FabricaConexoes;
import ifpr.pgua.eic.tarefas.model.daos.JDBCCategoriaDAO;
import ifpr.pgua.eic.tarefas.model.daos.JDBCTarefaDAO;
import ifpr.pgua.eic.tarefas.model.daos.JDBCUsuarioDAO;
import ifpr.pgua.eic.tarefas.model.daos.TarefaDAO;
import ifpr.pgua.eic.tarefas.model.daos.UsuarioDAO;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioCategoria;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioTarefa;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioUsuario;
import io.github.hugoperlin.navigatorfx.BaseAppNavigator;
import io.github.hugoperlin.navigatorfx.ScreenRegistryFXML;

/**
 * JavaFX App
 */
public class App extends BaseAppNavigator {

    private UsuarioDAO usuarioDAO = new JDBCUsuarioDAO(FabricaConexoes.getInstance());
    private RepositorioUsuario repositorioUsuario = new RepositorioUsuario(usuarioDAO);

    private CategoriaDAO categoriaDAO = new JDBCCategoriaDAO(FabricaConexoes.getInstance());
    private RepositorioCategoria repositorioCategoria = new RepositorioCategoria(categoriaDAO);

    private TarefaDAO tarefaDAO = new JDBCTarefaDAO(FabricaConexoes.getInstance());
    private RepositorioTarefa repositorioTarefa = new RepositorioTarefa(tarefaDAO,categoriaDAO);
    
    public static void main(String[] args) {
        launch();
    }

    @Override
    public String getHome() {
        // TODO Auto-generated method stub
        return "PRINCIPAL";
    }


    @Override
    public String getAppTitle() {
        // TODO Auto-generated method stub
        return "Livra";
    }

    @Override
    public void registrarTelas() {
        registraTela("PRINCIPAL", new ScreenRegistryFXML(App.class,"principal.fxml",o->new Principal()));
        registraTela("CADASTRARUSUARIO", new ScreenRegistryFXML(App.class,"cadastrar_usuario.fxml",o->new CadastrarUsuario(repositorioUsuario)));
        registraTela("HOME", new ScreenRegistryFXML(App.class,"home.fxml",o->new Home()));
        registraTela("LISTARLIVROS", new ScreenRegistryFXML(App.class,"listar_livros.fxml",o->new ListarLivros()));
        registraTela("CADASTRARLIVRO", new ScreenRegistryFXML(App.class,"cadastrar_livro.fxml",o->new CadastrarLivro()));
        registraTela("EDITARLIVRO", new ScreenRegistryFXML(App.class,"editar_livro.fxml",o->new EditarLivro()));
        registraTela("EDITARUSUARIO", new ScreenRegistryFXML(App.class, "editar_usuario.fxml", o->new EditarUsuario()));
        registraTela("CADASTRARCATEGORIA", new ScreenRegistryFXML(App.class,"cadastrar_categoria.fxml",o->new CadastroCategoria(repositorioCategoria)));
        registraTela("CADASTRARTAREFA", new ScreenRegistryFXML(App.class,"cadastrar_tarefa.fxml",o->new CadastroTarefa(repositorioTarefa, repositorioCategoria)));
        registraTela("LISTARCATEGORIA", new ScreenRegistryFXML(App.class, "listar_categoria.fxml", o->new ListarCategoria(repositorioCategoria)));
        registraTela("LISTARTAREFA", new ScreenRegistryFXML(App.class, "listar_tarefa.fxml", o->new ListarTarefa(repositorioTarefa)));
    }

}