package ifpr.pgua.eic.tarefas.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.App;
import ifpr.pgua.eic.tarefas.model.entities.Autor;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioAutor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Alert.AlertType;

public class ListarAutores implements Initializable{

    @FXML
    private Label lbAutores;

    @FXML
    private ListView<Autor> lstAutores;

    private RepositorioAutor repositorio;

    public ListarAutores(RepositorioAutor repositorio){
        this.repositorio = repositorio;
    }

    @FXML
    void abrirAutores(ActionEvent event) {

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

    @FXML
    void adicionarAutor(ActionEvent event) {
        App.pushScreen("CADASTRARAUTOR");
    }

    @FXML
    void deletarAutor(ActionEvent event) {

    }

    @FXML
    void editarAutor(ActionEvent event) {
        App.pushScreen("EDITARAUTOR");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        lstAutores.getItems().clear();
        lstAutores.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        Resultado r = repositorio.listarAutor();

        if(r.foiSucesso()){
            ArrayList<Autor> autores = (ArrayList)r.comoSucesso().getObj();
            lstAutores.getItems().addAll(autores);
        }else{
            Alert alert = new Alert(AlertType.ERROR,r.getMsg());
            alert.showAndWait();
        }

    }

}
