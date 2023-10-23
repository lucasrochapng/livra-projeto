package ifpr.pgua.eic.tarefas.controllers;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.App;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioCategoria;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CadastroCategoria {

    @FXML
    private TextArea txaDescricao;

    @FXML
    private TextField tfNome;

    private RepositorioCategoria repositorio;

    public CadastroCategoria(RepositorioCategoria repositorio){
        this.repositorio = repositorio;
    }

    @FXML
    void cadastrar(ActionEvent event) {
        String nome = tfNome.getText();
        String descricao = txaDescricao.getText();

        Resultado resultado = repositorio.criarCategoria(nome, descricao);

        Alert alert;
        
        if(resultado.foiErro()){
            alert = new Alert(AlertType.ERROR, resultado.getMsg());
        }else{
            alert = new Alert(AlertType.INFORMATION, resultado.getMsg());
            tfNome.clear();
            txaDescricao.clear();
        }

        alert.showAndWait();
    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

}
