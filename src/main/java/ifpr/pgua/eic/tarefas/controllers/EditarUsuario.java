package ifpr.pgua.eic.tarefas.controllers;

import ifpr.pgua.eic.tarefas.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditarUsuario {

    @FXML
    private Label lbEditar;

    @FXML
    private TextField tfNovaIdade;

    @FXML
    private TextField tfNovaSenha;

    @FXML
    private TextField tfNovoNome;

    @FXML
    private TextField tfNovoNomeUsuario;

    @FXML
    private TextField tfNovoTelefone;

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
        App.pushScreen("CADASTRARLIVRO");
    }

    @FXML
    void salvar(ActionEvent event) {

    }

}
