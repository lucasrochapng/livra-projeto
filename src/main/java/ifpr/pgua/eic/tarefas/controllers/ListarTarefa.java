package ifpr.pgua.eic.tarefas.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.App;
import ifpr.pgua.eic.tarefas.model.entities.Tarefa;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioTarefa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

public class ListarTarefa implements Initializable{

    @FXML
    private ListView<Tarefa> lstTarefa;

    @FXML
    private TextArea txaTarefa;

    private RepositorioTarefa repositorio;

    public ListarTarefa(RepositorioTarefa repositorio) {
        this.repositorio = repositorio;
    }

    @FXML
    void selecionado(MouseEvent event) {
        if(lstTarefa.getSelectionModel() != null){
            txaTarefa.setText("Titulo: "+lstTarefa.getSelectionModel().getSelectedItem().getTitulo()
            +"\nDescrição: "+lstTarefa.getSelectionModel().getSelectedItem().getDescricao()
            +"\nData de entrega: "+lstTarefa.getSelectionModel().getSelectedItem().getDataPrazo()
            +"\nCategoria: "+lstTarefa.getSelectionModel().getSelectedItem().getCategoria().getNome());
        }else{
            txaTarefa.clear();
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
        lstTarefa.getItems().clear();

        Resultado r = repositorio.ListarTarefa();

        if(r.foiSucesso()){
            ArrayList<Tarefa> tarefas = (ArrayList)r.comoSucesso().getObj();
            lstTarefa.getItems().addAll(tarefas);
        }else{
            Alert alert = new Alert(AlertType.ERROR,r.getMsg());
            alert.showAndWait();
        }
    }

}
