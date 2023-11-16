package ifpr.pgua.eic.tarefas.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.App;
import ifpr.pgua.eic.tarefas.model.entities.Autor;
import ifpr.pgua.eic.tarefas.model.entities.Livro;
import ifpr.pgua.eic.tarefas.model.entities.Usuario;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioAutor;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioLivro;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CadastrarLivro implements Initializable{

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfTitulo;

    @FXML
    private ComboBox<Autor> cbAutor;

    @FXML
    private TextField tfGenero;

    @FXML
    private TextField tfDescricao;

    @FXML
    private TextField tfContato;

    // @FXML
    // private TextField tfIdUsuario;

    @FXML
    private Button btAcao;

    @FXML
    private Label label;

    private RepositorioLivro repositorio;
    private RepositorioAutor repositorioAutor;
    private RepositorioUsuario repositorioUsuario;
    private Livro anterior;

    public CadastrarLivro(RepositorioLivro repositorio, RepositorioAutor repositorioAutor, RepositorioUsuario repositorioUsuario){
        this.repositorio = repositorio;
        this.repositorioAutor = repositorioAutor;
        this.repositorioUsuario = repositorioUsuario;
    }

    public CadastrarLivro(RepositorioLivro repositorio, RepositorioAutor repositorioAutor, RepositorioUsuario repositorioUsuario, Livro anterior){
        this.repositorio = repositorio;
        this.repositorioAutor = repositorioAutor;
        this.repositorioUsuario = repositorioUsuario;
        this.anterior = anterior;
    }

    @FXML
    void cadastrarLivro(ActionEvent event) {

        String id = tfId.getText();
        String titulo = tfTitulo.getText();
        Autor autor = cbAutor.getValue();
        String genero = tfGenero.getText();
        String descricao = tfDescricao.getText();
        String contato = tfContato.getText();
        Usuario usuario = repositorioUsuario.contaLogada();


        Resultado msg;
        if(anterior == null){
            msg = repositorio.cadastrarLivro(titulo, autor, genero, descricao, contato, usuario);
        } 
        else {
            msg = repositorio.alterarLivro(Integer.valueOf(id), titulo, autor, genero, descricao, contato, usuario);
        }

        Alert alert = new Alert(AlertType.INFORMATION,msg.getMsg());
        alert.showAndWait();

        tfTitulo.clear();
        tfGenero.clear();
        tfDescricao.clear();
        tfContato.clear();

    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        Resultado r1 = repositorioAutor.listarAutor();

        if(r1.foiSucesso()){
            List<Autor> list = (List)r1.comoSucesso().getObj();
            cbAutor.getItems().addAll(list);
        } 

        else if(anterior != null){
            List<Autor> list = (List)r1.comoSucesso().getObj();
            tfId.setText(anterior.getId()+"");
            tfTitulo.setText(anterior.getTitulo());
            cbAutor.getItems().addAll(list);
            tfGenero.setText(anterior.getGenero());
            tfDescricao.setText(anterior.getDescricao());
            tfContato.setText(anterior.getContato());

            btAcao.setText("ATUALIZAR");            
            label.setText("ATUALIZAR SEU LIVRO");
        }

        else {
            Alert alert = new Alert(AlertType.ERROR,r1.getMsg());
            alert.showAndWait();
        }
        

    }

    

}
