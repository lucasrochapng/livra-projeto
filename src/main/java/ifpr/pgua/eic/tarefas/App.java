package ifpr.pgua.eic.tarefas;

import ifpr.pgua.eic.tarefas.controllers.ListarLivros;
import ifpr.pgua.eic.tarefas.controllers.CadastrarAutor;
import ifpr.pgua.eic.tarefas.controllers.CadastrarLivro;
import ifpr.pgua.eic.tarefas.controllers.CadastrarUsuario;
import ifpr.pgua.eic.tarefas.controllers.EditarDados;
import ifpr.pgua.eic.tarefas.controllers.EditarUsuario;
import ifpr.pgua.eic.tarefas.controllers.Home;
import ifpr.pgua.eic.tarefas.controllers.ListarAutores;
import ifpr.pgua.eic.tarefas.controllers.Principal;
import ifpr.pgua.eic.tarefas.model.daos.AutorDAO;
import ifpr.pgua.eic.tarefas.model.daos.FabricaConexoes;
import ifpr.pgua.eic.tarefas.model.daos.JDBCAutorDAO;
import ifpr.pgua.eic.tarefas.model.daos.JDBCLivroDAO;
import ifpr.pgua.eic.tarefas.model.daos.JDBCUsuarioDAO;
import ifpr.pgua.eic.tarefas.model.daos.LivroDAO;
import ifpr.pgua.eic.tarefas.model.daos.UsuarioDAO;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioAutor;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioLivro;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioUsuario;
import io.github.hugoperlin.navigatorfx.BaseAppNavigator;
import io.github.hugoperlin.navigatorfx.ScreenRegistryFXML;

/**
 * JavaFX App
 */
public class App extends BaseAppNavigator {

    private UsuarioDAO usuarioDAO = new JDBCUsuarioDAO(FabricaConexoes.getInstance());
    private RepositorioUsuario repositorioUsuario = new RepositorioUsuario(usuarioDAO);

    private AutorDAO autorDAO = new JDBCAutorDAO(FabricaConexoes.getInstance());
    private RepositorioAutor repositorioAutor = new RepositorioAutor(autorDAO);

    private LivroDAO livroDAO = new JDBCLivroDAO(FabricaConexoes.getInstance());
    private RepositorioLivro repositorioLivro = new RepositorioLivro(livroDAO, autorDAO);
    
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
        registraTela("PRINCIPAL", new ScreenRegistryFXML(App.class,"principal.fxml",o->new Principal(repositorioUsuario)));

        registraTela("CADASTRARUSUARIO", new ScreenRegistryFXML(App.class,"cadastrar_usuario.fxml",o->new CadastrarUsuario(repositorioUsuario)));

        registraTela("HOME", new ScreenRegistryFXML(App.class,"home.fxml",o->new Home(repositorioLivro)));
        
        registraTela("CADASTRARAUTOR", new ScreenRegistryFXML(App.class, "cadastrar_autor.fxml", o->new CadastrarAutor(repositorioAutor)));
        registraTela("LISTARAUTORES", new ScreenRegistryFXML(App.class, "listar_autores.fxml", o->new ListarAutores(repositorioAutor)));

        registraTela("CADASTRARLIVRO", new ScreenRegistryFXML(App.class,"cadastrar_livro.fxml",o->new CadastrarLivro(repositorioLivro, repositorioAutor)));                     
        registraTela("LISTARLIVROS", new ScreenRegistryFXML(App.class,"listar_livros.fxml",o->new ListarLivros(repositorioLivro, repositorioUsuario)));

        registraTela("EDITARUSUARIO", new ScreenRegistryFXML(App.class, "editar_usuario.fxml", o->new EditarUsuario(repositorioUsuario)));
        registraTela("EDITARDADOS", new ScreenRegistryFXML(App.class, "editar_dados.fxml", o->new EditarDados(repositorioUsuario)));

    }

}