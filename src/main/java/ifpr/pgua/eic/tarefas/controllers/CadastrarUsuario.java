package ifpr.pgua.eic.tarefas.controllers;

import ifpr.pgua.eic.tarefas.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CadastrarUsuario {

    @FXML
    private Label lbRegistre;

    @FXML
    private PasswordField pfRepetirSenha;

    @FXML
    private PasswordField pfSenha;

    @FXML
    private TextField tfIdade;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfNomeUsuario;

    @FXML
    private TextField tfWhatsapp;

    @FXML
    void registrar(ActionEvent event) {

    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

}

