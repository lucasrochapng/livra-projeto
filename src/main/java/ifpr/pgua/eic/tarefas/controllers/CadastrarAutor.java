package ifpr.pgua.eic.tarefas.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.App;
import ifpr.pgua.eic.tarefas.model.entities.Autor;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioAutor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CadastrarAutor implements Initializable{

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfNome;

    @FXML
    private Button btAcao;

    @FXML
    private Label lbTexto;

    private RepositorioAutor repositorio;

    private Autor anterior;

    public CadastrarAutor(RepositorioAutor repositorio){
        this.repositorio = repositorio;
    }

    public CadastrarAutor(RepositorioAutor repositorio, Autor anterior){
        this.repositorio = repositorio;
        this.anterior = anterior;
    }

    @FXML
    void cadastrarAutor(ActionEvent event) {

        String nome = tfNome.getText();
        String id = tfId.getText();

        String msg;
        if(anterior == null){
            msg = repositorio.cadastrarAutor(nome);
        }else{
            msg = repositorio.alterarAutor(Integer.valueOf(id), nome);
        }
        
        Alert alert = new Alert(AlertType.INFORMATION,msg);
        alert.showAndWait();

    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if(anterior != null){
            tfId.setText(anterior.getId()+"");
            tfNome.setText(anterior.getNome());

            btAcao.setText("ATUALIZAR");
            lbTexto.setText("ATUALIZE SEU AUTOR");
        }
        
    }

}