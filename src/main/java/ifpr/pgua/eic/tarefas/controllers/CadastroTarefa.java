package ifpr.pgua.eic.tarefas.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.naming.spi.DirStateFactory.Result;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.App;
import ifpr.pgua.eic.tarefas.model.entities.Categoria;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioCategoria;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioTarefa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CadastroTarefa implements Initializable{

    @FXML
    private TextArea txaDescricao;

    @FXML
    private ComboBox<Categoria> cbCategoria;

    @FXML
    private DatePicker dtpEntrega;

    @FXML
    private TextField tfNome;

    private RepositorioTarefa repositorio;
    private RepositorioCategoria repositorioCategoria;    

    public CadastroTarefa(RepositorioTarefa repositorio, RepositorioCategoria repositorioCategoria) {
        this.repositorio = repositorio;
        this.repositorioCategoria = repositorioCategoria;
    }

    @FXML
    void cadastrar(ActionEvent event) {

        LocalDate dataPrazo = dtpEntrega.getValue();
        String titulo = tfNome.getText();
        String descricao = txaDescricao.getText();
        Categoria categoria = cbCategoria.getSelectionModel().getSelectedItem();
        
        Resultado resultado;
        resultado = repositorio.criarTarefa(titulo, descricao, dataPrazo, categoria);

        Alert alert;
        if(resultado.foiErro()){
            alert = new Alert(AlertType.ERROR, resultado.getMsg());
        }else{
            alert = new Alert(AlertType.INFORMATION, resultado.getMsg());
            tfNome.clear();
            txaDescricao.clear();
            dtpEntrega.getEditor().clear();
            cbCategoria.getSelectionModel().clearSelection();
        }
        alert.showAndWait();
    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        cbCategoria.getItems().clear();

        Resultado r = repositorioCategoria.listarCategoria();

        if(r.foiSucesso()){
            ArrayList<Categoria> categorias = (ArrayList)r.comoSucesso().getObj();
            cbCategoria.getItems().addAll(categorias);
        }else{
            Alert alert = new Alert(AlertType.ERROR,r.getMsg());
        }
    }

}
