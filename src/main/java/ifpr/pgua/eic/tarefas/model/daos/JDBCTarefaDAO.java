package ifpr.pgua.eic.tarefas.model.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.model.entities.Categoria;
import ifpr.pgua.eic.tarefas.model.entities.Tarefa;
import ifpr.pgua.eic.tarefas.utils.DBUtils;

public class JDBCTarefaDAO implements TarefaDAO{
    private static final String INSERTSQL = "INSERT INTO tarefa(titulo,descricao,dataPrazo,categoriaId) values(?,?,?,?)";
    private static final String SELECTSQL = "SELECT * FROM tarefa";

    FabricaConexoes fabrica;

    public JDBCTarefaDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado criar(Tarefa tarefa) {
        try(Connection con = fabrica.getConnection()) {
            
            PreparedStatement pstm = con.prepareStatement(INSERTSQL, Statement.RETURN_GENERATED_KEYS);
        
            pstm.setString(1, tarefa.getTitulo());
            pstm.setString(2, tarefa.getDescricao());
            pstm.setDate(3, Date.valueOf(tarefa.getDataPrazo()));
            pstm.setInt(4, tarefa.getCategoria().getId());

            int ret = pstm.executeUpdate();
            if(ret == 1){
                int id = DBUtils.getLastId(pstm);

                tarefa.setId(id);
                
                return Resultado.sucesso("Tarefa cadastrada!", tarefa);
            }
            return Resultado.erro("Erro desconhecido");
        } catch (Exception e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado listar() {
        try(Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement(SELECTSQL);

            ResultSet rs = pstm.executeQuery();
            
            ArrayList<Tarefa> tarefas = new ArrayList<>();
            while(rs.next()){
                String titulo = rs.getString("titulo");
                String descricao = rs.getString("descricao");
                LocalDate dataPrazo = rs.getDate("dataPrazo").toLocalDate();
                Categoria categoria = new Categoria(rs.getInt("categoriaId"));

                Tarefa tarefa = new Tarefa(titulo, descricao, dataPrazo, categoria);
                tarefas.add(tarefa);
            }
            return Resultado.sucesso("Tarefas listadas!", tarefas);
        } catch (Exception e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado editar(int id, Tarefa nova) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editar'");
    }

    @Override
    public Resultado excluir(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'excluir'");
    }
    
}
