package ifpr.pgua.eic.tarefas.controllers;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.App;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioAutor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CadastrarAutor {

    @FXML
    private TextField tfNome;

    private RepositorioAutor repositorio;

    public CadastrarAutor(RepositorioAutor repositorio){
        this.repositorio = repositorio;
    }

    @FXML
    void cadastrarAutor(ActionEvent event) {

        String nome = tfNome.getText();

        Resultado resultado = repositorio.criarAutor(nome);

        Alert alert;

        if(resultado.foiErro()){
            alert = new Alert(AlertType.ERROR, resultado.getMsg());
        }
        else{
            alert = new Alert(AlertType.INFORMATION, resultado.getMsg());
            tfNome.clear();
        }

        alert.showAndWait();

    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

}
