package ifpr.pgua.eic.tarefas.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.model.entities.Livro;
import ifpr.pgua.eic.tarefas.utils.DBUtils;

public class JDBCLivroDAO implements LivroDAO{

    private static final String INSERTSQL = "INSERT INTO livros(titulo, autorId, genero, descricao) values (?,?,?,?)";
    private static final String SELECTSQL = "SELECT * FROM livros";
    private static final String SEARCHSQL = "SELECT * FROM livros WHERE id = ?";

    FabricaConexoes fabrica;

    public JDBCLivroDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado criar(Livro livro) {
        try(Connection con = fabrica.getConnection()) {

            PreparedStatement pstm = con.prepareStatement(INSERTSQL, Statement.RETURN_GENERATED_KEYS);

            pstm.setString(1, livro.getTitulo());
            pstm.setInt(2, livro.getAutor().getId());
            pstm.setString(3, livro.getGenero());
            pstm.setString(4, livro.getDescricao());

            int ret = pstm.executeUpdate();
            if(ret == 1){
                int id = DBUtils.getLastId(pstm);
                livro.setId(id);
                return Resultado.sucesso("Livro cadastrado!", livro);
            }
            return Resultado.erro("Erro desconhecido");
        } catch (Exception e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado listar() {
        try(Connection con = fabrica.getConnection()){
            PreparedStatement pstm = con.prepareStatement(SELECTSQL);
            ResultSet rs = pstm.executeQuery();

            ArrayList<Livro> livros = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String genero = rs.getString("genero");
                String descricao = rs.getString("descricao");

                Livro livro = new Livro(id, titulo, null, genero, descricao);
                livros.add(livro);
            }
            return Resultado.sucesso("Livros listados", livros);
        } catch (Exception e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado atualizar(int id, Livro novo) {
        try(Connection con = fabrica.getConnection();){
            PreparedStatement pstm = con.prepareStatement("UPDATE livros SET titulo=?, autorId=?, genero=?, descricao=? WHERE id=?");

            pstm.setString(1, novo.getTitulo());
            pstm.setInt(2, id);
            pstm.setInt(3, novo.getAutor().getId());
            pstm.setString(4, novo.getGenero());
            pstm.setString(5, novo.getDescricao());

            int ret = pstm.executeUpdate();

            if(ret == 1){
                return Resultado.sucesso("Livro atualizado", novo);
            }
            return Resultado.erro("Erro desconhecido!");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado excluir(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'excluir'");
    }



    
    
}

/*

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

 */
