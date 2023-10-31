package ifpr.pgua.eic.tarefas.controllers;

import ifpr.pgua.eic.tarefas.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ListarLivros {

    @FXML
    private Label lbLivros;

    @FXML
    private ListView<?> lstLivros;

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

    }

    @FXML
    void adicionarLivro(ActionEvent event) {
        App.pushScreen("CADASTRARLIVRO");
    }

    @FXML
    void deletarLivro(ActionEvent event) {

    }

    @FXML
    void editarLivro(ActionEvent event) {
        App.pushScreen("EDITARLIVRO");
    }

}
