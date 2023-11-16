package ifpr.pgua.eic.tarefas.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.App;
import ifpr.pgua.eic.tarefas.model.entities.Usuario;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CadastrarUsuario implements Initializable{

    @FXML
    private Button btRegistrar;

    @FXML
    private Label label;

    @FXML
    private TextField tfId;

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
    private Usuario anterior;

    public CadastrarUsuario(RepositorioUsuario repositorio){
        this.repositorio = repositorio;
    }

    public CadastrarUsuario(RepositorioUsuario repositorio, Usuario anterior){
        this.repositorio = repositorio;
        this.anterior = anterior;
    }

    @FXML
    void registrar(ActionEvent event) {

        String id = tfId.getText();
        String nome = tfNome.getText();
        String nomeUsuario = tfNomeUsuario.getText();
        String senha = pfSenha.getText();
        String telefone = tfWhatsapp.getText();
        String idade = tfIdade.getText();

        Resultado msg;
        if(anterior == null){
            msg = repositorio.criarUsuario(nome, nomeUsuario, senha, Integer.valueOf(telefone), Integer.valueOf(idade));
        } else {
            msg = repositorio.alterarUsuario(Integer.valueOf(id), nome, nomeUsuario, senha, Integer.valueOf(telefone), Integer.valueOf(idade));
            if(msg.foiSucesso()){
                anterior = null;
            }
        }

        Alert alert = new Alert(AlertType.INFORMATION,msg.getMsg());
        alert.showAndWait();

        tfNome.clear();
        tfNomeUsuario.clear();
        pfSenha.clear();
        tfWhatsapp.clear();
        tfIdade.clear();

        App.pushScreen("PRINCIPAL");
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
            tfNomeUsuario.setText(anterior.getNomeUsuario());
            pfSenha.setText(anterior.getSenha());
            tfWhatsapp.setText(anterior.getTelefone()+"");
            tfIdade.setText(anterior.getIdade()+"");

            btRegistrar.setText("ATUALIZAR");
            label.setText("ATUALIZE SEU PERFIL");
        }
        
    }

}

