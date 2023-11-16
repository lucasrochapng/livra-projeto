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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;

public class EditarDados implements Initializable{

    private RepositorioUsuario repositorioUsuario;

    public EditarDados(RepositorioUsuario repositorioUsuario){
        this.repositorioUsuario = repositorioUsuario;
    }

    @FXML
    private Button btAtualizar;

    @FXML
    private Button btVoltar;

    @FXML
    private Label label;

    @FXML
    private VBox telaEditAutor;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfIdade;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfNomeUsuario;

    @FXML
    private TextField tfSenha;

    @FXML
    private TextField tfTelefone;

    @FXML
    void atualizarPerfil(ActionEvent event) {

        int id = Integer.parseInt(tfId.getText());
        String nome = tfNome.getText();
        String nomeUsuario = tfNomeUsuario.getText();
        String senha = tfSenha.getText();
        int telefone = Integer.parseInt(tfTelefone.getText());
        int idade = Integer.parseInt(tfIdade.getText());

        Resultado resultado = repositorioUsuario.alterarUsuario(id, nome, nomeUsuario, senha, telefone, idade);

        Alert alert;
        if(resultado.foiErro()){
            alert = new Alert(AlertType.ERROR, resultado.getMsg());
        } else {
            alert = new Alert(AlertType.INFORMATION, resultado.getMsg());
            tfNome.clear();
            tfNomeUsuario.clear();
            tfSenha.clear();
            tfTelefone.clear();
            tfIdade.clear();
        }
        alert.showAndWait();

    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        tfId.setText(repositorioUsuario.contaLogada().getId()+"");
        tfNome.setText(repositorioUsuario.contaLogada().getNome());
        tfNomeUsuario.setText(repositorioUsuario.contaLogada().getNomeUsuario());
        tfSenha.setText(repositorioUsuario.contaLogada().getSenha());
        tfTelefone.setText(repositorioUsuario.contaLogada().getTelefone()+"");
        tfIdade.setText(repositorioUsuario.contaLogada().getIdade()+"");
        
    }

}
