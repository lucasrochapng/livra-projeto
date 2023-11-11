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

public class Principal {

    @FXML
    private Label lbOu;

    @FXML
    private Label lbRegistro;

    @FXML
    private PasswordField pfSenha;

    @FXML
    private TextField tfNomeUsuario;

    private RepositorioUsuario repositorioUsuario;

    public Principal(RepositorioUsuario repositorioUsuario){
        this.repositorioUsuario = repositorioUsuario;
    }

    /*@FXML
    void entrar(ActionEvent event) {

        //acessar repositorioUsuario... ok
        //chamar metodo de login
        //se for sucesso
        App.pushScreen("HOME");
        //senão
        //mensagem de erro...
    } */

    @FXML
    void entrar(ActionEvent event) {

        String nomeUsuario = tfNomeUsuario.getText();
        String senha = pfSenha.getText();

        Resultado resultado = repositorioUsuario.login(nomeUsuario, senha);
        
        Alert alert;
        if(resultado.foiErro()){
            alert = new Alert(AlertType.ERROR, resultado.getMsg());
        } else {
            alert = new Alert(AlertType.INFORMATION, resultado.getMsg());
            App.pushScreen("HOME");
        }

        alert.showAndWait();
        

        

    }

    @FXML
    void registrar(ActionEvent event) {
        App.pushScreen("CADASTRARUSUARIO");
    }


}
