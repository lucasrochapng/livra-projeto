package ifpr.pgua.eic.tarefas.controllers;

import ifpr.pgua.eic.tarefas.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CadastrarLivro {

    @FXML
    private TextField tfAutor;

    @FXML
    private TextField tfDescricao;

    @FXML
    private TextField tfGenero;

    @FXML
    private TextField tfTitulo;

    @FXML
    void cadastrarLivro(ActionEvent event) {
        
    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

}
