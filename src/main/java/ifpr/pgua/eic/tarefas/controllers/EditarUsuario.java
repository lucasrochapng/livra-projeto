package ifpr.pgua.eic.tarefas.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.tarefas.App;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EditarUsuario implements Initializable{

    @FXML
    private ImageView abrirAutores;

    @FXML
    private ImageView abrirHome;

    @FXML
    private ImageView abrirLivros;

    @FXML
    private HBox barraMenu;

    @FXML
    private Button btEditar;

    @FXML
    private Button btEditarUsuario;

    @FXML
    private Button btMenu;

    @FXML
    private Label labelIdade;

    @FXML
    private Label labelTelefone;

    @FXML
    private VBox telaEditarUsuario;

    @FXML
    private TextField tfIdade;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfNomeUsuario;

    @FXML
    private TextField tfTelefone;

    private RepositorioUsuario repositorioUsuario;

    public EditarUsuario(RepositorioUsuario repositorioUsuario){
        this.repositorioUsuario = repositorioUsuario;
    }

    @FXML
    void editarUsuario(ActionEvent event) {
        App.pushScreen("EDITARDADOS");
    }

    @FXML
    void abrirAutores(ActionEvent event) {
        App.pushScreen("LISTARAUTORES");
    }

    @FXML
    void abrirEditar(ActionEvent event) {
        App.pushScreen("EDITARUSUARIO");
    }

    @FXML
    void abrirHome(ActionEvent event) {
        App.pushScreen("HOME");
    }

    @FXML
    void abrirLivros(ActionEvent event) {
        App.pushScreen("LISTARLIVROS");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        tfNome.setText(repositorioUsuario.contaLogada().getNome());
        tfNomeUsuario.setText(repositorioUsuario.contaLogada().getNomeUsuario());
        tfIdade.setText(repositorioUsuario.contaLogada().getIdade()+"");
        tfTelefone.setText(repositorioUsuario.contaLogada().getTelefone()+"");
    
    }

}

