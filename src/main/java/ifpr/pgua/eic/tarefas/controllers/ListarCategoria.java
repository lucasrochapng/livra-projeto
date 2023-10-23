package ifpr.pgua.eic.tarefas.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.App;
import ifpr.pgua.eic.tarefas.model.entities.Categoria;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioCategoria;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

public class ListarCategoria implements Initializable{

    @FXML
    private ListView<Categoria> lstCategoria;

    @FXML
    private TextArea txaCategoria;

    private RepositorioCategoria repositorio;

    public ListarCategoria(RepositorioCategoria repositorio) {
        this.repositorio = repositorio;
    }

    @FXML
    void selecionado(MouseEvent event) {
        if(lstCategoria.getSelectionModel() != null){
            txaCategoria.setText("Nome: "+lstCategoria.getSelectionModel().getSelectedItem().getNome()
            +"\nDescrição: "+lstCategoria.getSelectionModel().getSelectedItem().getDescricao());
        }else{
            txaCategoria.clear();
        }
    }

    @FXML
    void deletar(ActionEvent event) {

    }

    @FXML
    void editar(ActionEvent event) {

    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        lstCategoria.getItems().clear();

        lstCategoria.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        Resultado r = repositorio.listarCategoria();

        if(r.foiSucesso()){
            ArrayList<Categoria> categorias = (ArrayList)r.comoSucesso().getObj();
            lstCategoria.getItems().addAll(categorias);
        }else{
            Alert alert = new Alert(AlertType.ERROR,r.getMsg());
            alert.showAndWait();
        }
    }

}
