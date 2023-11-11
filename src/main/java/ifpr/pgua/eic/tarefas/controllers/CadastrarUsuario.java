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

    /*
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

    } */

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
    }

    /*
    void cadastrarAutor(ActionEvent event) {

        String nome = tfNome.getText();
        String id = tfId.getText();

        Resultado msg;
        if(anterior == null){
            msg = repositorio.cadastrarAutor(nome);
        }else{
            msg = repositorio.alterarAutor(Integer.valueOf(id), nome);
            if(msg.foiSucesso()){
                anterior = null;
            }
        }
        
        Alert alert = new Alert(AlertType.INFORMATION,msg.getMsg());
        alert.showAndWait();

    }
     */
  
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
        /*
        if(anterior != null){
            tfId.setText(anterior.getId()+"");
            tfNome.setText(anterior.getNome());

            btAcao.setText("ATUALIZAR");
            lbTexto.setText("ATUALIZE SEU AUTOR");
        }
         */
    }

}

