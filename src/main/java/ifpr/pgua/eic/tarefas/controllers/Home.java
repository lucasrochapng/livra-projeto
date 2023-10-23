package ifpr.pgua.eic.tarefas.controllers;

import ifpr.pgua.eic.tarefas.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Home {

    @FXML
    private Label lbBiblioteca;

    @FXML
    private ListView<?> lstLivros;

    @FXML
    private TextArea taDetalhes;

    @FXML
    private TextField tfProcureLivros;

    @FXML
    void abrirEditar(ActionEvent event) {

    }

    @FXML
    void abrirHome(ActionEvent event) {

    }

    @FXML
    void abrirLivros(ActionEvent event) {

    }

    @FXML
    void procurarLivro(ActionEvent event) {

    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

}

