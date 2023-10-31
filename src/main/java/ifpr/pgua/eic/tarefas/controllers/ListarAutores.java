package ifpr.pgua.eic.tarefas.controllers;

import ifpr.pgua.eic.tarefas.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ListarAutores {

    @FXML
    private Label lbAutores;

    @FXML
    private ListView<?> lstAutores;

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

}
