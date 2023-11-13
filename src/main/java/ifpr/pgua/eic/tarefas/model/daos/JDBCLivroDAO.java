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

    private static final String INSERTSQL = "INSERT INTO livros2(titulo, autorId, genero, descricao, contato) values (?,?,?,?,?)";
    private static final String SELECTSQL = "SELECT * FROM livros2";
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
            pstm.setString(5, livro.getContato());

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
                String contato = rs.getString("contato");

                Livro livro = new Livro(id, titulo, null, genero, descricao, contato);
                livros.add(livro);
            }
            return Resultado.sucesso("Livros listados", livros);
        } catch (Exception e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado listarPorContato(String contato) {
        try(Connection con = fabrica.getConnection()){

            PreparedStatement pstm = con.prepareStatement("SELECT * FROM livros2 WHERE contato=?");

            pstm.setString(1, contato);

            ResultSet rs = pstm.executeQuery();

            ArrayList<Livro> livros = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String genero = rs.getString("genero");
                String descricao = rs.getString("descricao");
                String contatoLivro = rs.getString("contato");

                Livro livro = new Livro(id, titulo, null, genero, descricao, contatoLivro);
                livros.add(livro);
            }
            return Resultado.sucesso("Livros listados", livros);
        } catch (Exception e){
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado atualizar(int id, Livro novo) {
        try(Connection con = fabrica.getConnection();){
            PreparedStatement pstm = con.prepareStatement("UPDATE livros SET titulo=?, autorId=?, genero=?, descricao=? WHERE id=?");
            
            pstm.setString(1, novo.getTitulo());
            pstm.setInt(2, novo.getAutor().getId());
            pstm.setString(3, novo.getGenero());
            pstm.setString(4, novo.getDescricao());
            pstm.setInt(5, id);

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

    @Override
    public Resultado buscarPorId(int id) {
        try(Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM livros2 WHERE id=?");
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            if(rs.next()){
                String titulo = rs.getString("titulo");
                String genero = rs.getString("genero");
                String descricao = rs.getString("descricao");
                String contato = rs.getString("contato");

                Livro livro = new Livro(id, titulo, null, genero, descricao, contato);

                return Resultado.sucesso("Livros listados", livro);
            } else {
                return Resultado.erro("Livro não encontrado!");
            }
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    
    
}