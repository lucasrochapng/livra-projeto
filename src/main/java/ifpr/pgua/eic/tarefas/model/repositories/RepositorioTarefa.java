package ifpr.pgua.eic.tarefas.model.repositories;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;
import com.google.protobuf.TextFormat.ParseException;

import ifpr.pgua.eic.tarefas.model.daos.CategoriaDAO;
import ifpr.pgua.eic.tarefas.model.daos.TarefaDAO;
import ifpr.pgua.eic.tarefas.model.entities.Categoria;
import ifpr.pgua.eic.tarefas.model.entities.Tarefa;

public class RepositorioTarefa {

    private ArrayList<Tarefa> tarefas;

    private TarefaDAO dao;
    private CategoriaDAO categoriaDAO;

    public RepositorioTarefa(TarefaDAO dao, CategoriaDAO categoriaDAO) {
        tarefas = new ArrayList<>();
        this.dao = dao;
        this.categoriaDAO = categoriaDAO;
    }

    public Resultado criarTarefa(String titulo, String descricao, LocalDate dataEntrega, Categoria categoria){

        if(titulo.isEmpty() || titulo.isBlank()){
            return Resultado.erro("Título inválido!");
        }

        if(descricao.isEmpty() || descricao.isBlank()){
            return Resultado.erro("Descrição inválida!");
        }

        if(dataEntrega.isBefore(LocalDate.now())){
            return Resultado.erro("Data de entrega inválida!");
        }

        if(categoria == null){
            return Resultado.erro("Categoria inválida!");
        }

        Tarefa tarefa = new Tarefa(titulo, descricao, dataEntrega, categoria);

        return dao.criar(tarefa);
    }

    public Resultado ListarTarefa(){
        Resultado resultado = dao.listar();
        if(resultado.foiSucesso()){
            ArrayList<Tarefa> tarefas = (ArrayList)resultado.comoSucesso().getObj();
            for(Tarefa tarefa: tarefas){
                resultado = categoriaDAO.buscarPorId(tarefa.getCategoria().getId());
                if(resultado.foiSucesso()){
                    tarefa.setCategoria((Categoria)resultado.comoSucesso().getObj());
                }else{
                    return Resultado.erro("Erro desconhecido!");
                }
            }
            return Resultado.sucesso("Tarefa listada com sucesso",tarefas);
        }
        return Resultado.erro("Erro ao listar!");
    }
}