package ifpr.pgua.eic.tarefas.controllers;

import ifpr.pgua.eic.tarefas.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditarLivro {

    @FXML
    private Label lbInformacoes;

    @FXML
    private TextField tfAutor;

    @FXML
    private TextField tfDescricao;

    @FXML
    private TextField tfGenero;

    @FXML
    private TextField tfTitulo;

    @FXML
    void editarLivro(ActionEvent event) {

    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

}
