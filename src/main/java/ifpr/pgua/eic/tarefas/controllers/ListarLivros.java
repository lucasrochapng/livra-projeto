package ifpr.pgua.eic.tarefas.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.App;
import ifpr.pgua.eic.tarefas.model.entities.Livro;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioAutor;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioLivro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Alert.AlertType;

public class ListarLivros implements Initializable{

    @FXML
    private ListView<Livro> lstLivros;

    private RepositorioLivro repositorio;
    private RepositorioAutor repositorioAutor;

    private Livro selecionado;

    public ListarLivros(RepositorioLivro repositorio){
        this.repositorio = repositorio;
        
    }

    @FXML
    private void selecionar(){
        selecionado = lstLivros.getSelectionModel().getSelectedItem();
    }
    
    @FXML
    void adicionarLivro(ActionEvent event) {
        App.pushScreen("CADASTRARLIVRO");
    }

    @FXML
    private void editarLivro(){
        if(selecionado != null){
            App.pushScreen("CADASTRARLIVRO", o->new CadastrarLivro(repositorio, repositorioAutor, selecionado));
        }
    }

    @FXML
    void abrirAutores(ActionEvent event) {
        App.pushScreen("LISTARAUTORES");
    }

    @FXML
    void abrirHome(ActionEvent event) {
        App.pushScreen("HOME");
    }

    @FXML
    void editarUsuario(ActionEvent event){
        App.pushScreen("EDITARUSUARIO");
    }

    @FXML
    void abrirLivros(ActionEvent event) {

    }


    @FXML
    void deletarLivro(ActionEvent event) {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        lstLivros.getItems().clear();
        lstLivros.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        Resultado r = repositorio.listar();

        if(r.foiSucesso()){
            ArrayList<Livro> livros = (ArrayList)r.comoSucesso().getObj();
            lstLivros.getItems().addAll(livros);
        } else {
            Alert alert = new Alert(AlertType.ERROR,r.getMsg());
            alert.showAndWait();
        }
    }

}