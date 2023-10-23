package ifpr.pgua.eic.tarefas.controllers;

import ifpr.pgua.eic.tarefas.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Principal {

    @FXML
    private Label lbOu;

    @FXML
    private Label lbRegistro;

    @FXML
    private PasswordField pfSenha;

    @FXML
    private TextField tfNomeUsuario;

    @FXML
    void entrar(ActionEvent event) {

    }

    @FXML
    void registrar(ActionEvent event) {
        App.pushScreen("CADASTRARUSUARIO");
    }

    //----------------------------------------

    @FXML
    void cadastrarCategoria(ActionEvent event) {
        App.pushScreen("CADASTRARCATEGORIA");
    }

    @FXML
    void cadastrarTarefa(ActionEvent event) {
        App.pushScreen("CADASTRARTAREFA");
    }

    @FXML
    void listarCategoria(ActionEvent event) {
        App.pushScreen("LISTARCATEGORIA");
    }

    @FXML
    void listarTarefa(ActionEvent event) {
        App.pushScreen("LISTARTAREFA");
    }

}
