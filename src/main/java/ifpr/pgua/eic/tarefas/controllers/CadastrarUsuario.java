package ifpr.pgua.eic.tarefas.controllers;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.App;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

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

    private RepositorioUsuario repositorio;

    public CadastrarUsuario(RepositorioUsuario repositorio){
        this.repositorio = repositorio;
    }

    @FXML
    void registrar(ActionEvent event) {

        String nome = tfNome.getText();
        String nomeUsuario = tfNomeUsuario.getText();
        String senha = pfSenha.getText();
        int telefone = Integer.parseInt(tfWhatsapp.getText());
        int idade = Integer.parseInt(tfIdade.getText());

        Resultado resultado = repositorio.criarUsuario(nome, nomeUsuario, senha, telefone, idade);

        Alert alert;
        if(resultado.foiErro()){
            alert = new Alert(AlertType.ERROR, resultado.getMsg());
        } else {
            alert = new Alert(AlertType.INFORMATION, resultado.getMsg());
            tfNome.clear();
            tfNomeUsuario.clear();
            pfSenha.clear();
            pfRepetirSenha.clear();
            tfWhatsapp.clear();
            tfIdade.clear();
        }
        alert.showAndWait();

    }
  
    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

}

