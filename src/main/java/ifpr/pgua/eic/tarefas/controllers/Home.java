package ifpr.pgua.eic.tarefas.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.App;
import ifpr.pgua.eic.tarefas.model.entities.Livro;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioLivro;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Home implements Initializable{

    @FXML
    private Label lbBiblioteca;

    @FXML
    private ListView<Livro> lstLivros;

    @FXML
    private TextArea taDetalhes;

    @FXML
    private TextField tfProcureLivros;

    private ObservableList<Livro> livrosObservableList;

    private RepositorioLivro repositorio;
    private Livro selecionado;

    public Home(RepositorioLivro repositorio){
        this.repositorio = repositorio;
    }

    @FXML
    void exibirDetalhes(MouseEvent event) {
        Livro livro = lstLivros.getSelectionModel().getSelectedItem();
        if(livro != null){
            taDetalhes.clear();
            taDetalhes.appendText("Dono: "+livro.getUsuario());
            taDetalhes.appendText("\nTítulo: "+livro.getTitulo());
            taDetalhes.appendText("\nAutor: "+livro.getAutor());
            taDetalhes.appendText("\nGênero: "+livro.getGenero());
            taDetalhes.appendText("\nDescrição: "+livro.getDescricao());
            taDetalhes.appendText("\nContato para troca: "+livro.getContato());
        }
    }

    @FXML
    void abrirAutores(ActionEvent event) {
        App.pushScreen("LISTARAUTORES");
    }

    @FXML
    void abrirEditar(ActionEvent event) {
        App.pushScreen("EDITARUSUARIO");
    }

    @FXML
    void abrirHome(ActionEvent event) {

    }

    @FXML
    void abrirLivros(ActionEvent event) {
        App.pushScreen("LISTARLIVROS");
    }

    @FXML
    void procurarLivro(ActionEvent event) {
        String titulo = tfProcureLivros.getText();
        if(!titulo.isEmpty()) {
            
            lstLivros.getItems().clear();
            lstLivros.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            Resultado r = repositorio.buscarLivro(titulo);

            if(r.foiSucesso()){
                ArrayList<Livro> livros = (ArrayList)r.comoSucesso().getObj();
                lstLivros.getItems().addAll(livros);
            } else {
                Alert alert = new Alert(AlertType.ERROR,r.getMsg());
                alert.showAndWait();
            }
            
        } else {
            carregarTodosLivros();
        }
    }

    @FXML
    void voltar(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION, "Deseja mesmo sair da sua conta?");
        Optional<ButtonType> buttonType = alert.showAndWait();
        if(buttonType.get().equals(ButtonType.OK)){
            App.pushScreen("PRINCIPAL");
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        carregarTodosLivros();
        
    }

    public void carregarTodosLivros(){
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

