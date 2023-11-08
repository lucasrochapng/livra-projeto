package ifpr.pgua.eic.tarefas.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.model.entities.Autor;
import ifpr.pgua.eic.tarefas.utils.DBUtils;

public class JDBCAutorDAO implements AutorDAO {

    private static final String INSERTSQL = "INSERT INTO autores(nome) VALUES (?)";
    private static final String SELECTSQL = "SELECT * FROM autores";
    private static final String SEARCHSQL = "SELECT * FROM autores WHERE id = ?";

    private FabricaConexoes fabrica;

    public JDBCAutorDAO(FabricaConexoes fabrica){
        this.fabrica = fabrica;
    }

    @Override
    public Resultado criar(Autor autor) {
        
        try(Connection con = fabrica.getConnection();){

            PreparedStatement pstm = con.prepareStatement(INSERTSQL, Statement.RETURN_GENERATED_KEYS);

            pstm.setString(1, autor.getNome());

            int ret = pstm.executeUpdate();
            
            if(ret == 1){

                System.out.println(con.getMetaData().getDatabaseProductName());

                int id = DBUtils.getLastId(pstm);
                autor.setId(id);

                System.out.println(autor);
                return Resultado.sucesso("Autor cadastrado!", autor);
            }
            return Resultado.erro("Erro desconhecido");
        }catch(SQLException e){
            return Resultado.erro(e.getMessage());
        }
        
    }

    @Override
    public Resultado listar() {
        
        try(Connection con = fabrica.getConnection()) {

            PreparedStatement pstm = con.prepareStatement(SELECTSQL);

            ResultSet rs = pstm.executeQuery();

            ArrayList<Autor> autores = new ArrayList<>();
            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                
                Autor autor = new Autor(id, nome);
                autores.add(autor);
            }

            return Resultado.sucesso("Autores listados!", autores);

        } catch (Exception e) {
            return Resultado.erro(e.getMessage());
        }
        
    }

    @Override
    public Resultado buscarPorId(int id) {
        try (Connection con = fabrica.getConnection()) {

            PreparedStatement pstm = con.prepareStatement("SELECT * FROM autores WHERE id=?");

            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();

            if(rs.next()){
                String nome = rs.getString("nome");

                Autor autor = new Autor(id, nome);
                return Resultado.sucesso("Autor encontrado!", autor);
            }
            return Resultado.erro("Autor não encontrado!");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado atualizar(int id, Autor novo) {
        try(Connection con = fabrica.getConnection();) {
            PreparedStatement pstm = con.prepareStatement("UPDATE autores SET nome=? WHERE id=?");

            pstm.setString(1, novo.getNome());
            pstm.setInt(2, id);

            int ret = pstm.executeUpdate();

            if(ret == 1){
                return Resultado.sucesso("Autor atualizado", novo);
            }
            return Resultado.erro("Erro não identificado!");
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
    public Resultado buscarAutorLivro(int livroId) {
        try(Connection con = fabrica.getConnection()) {

            PreparedStatement pstm = con.prepareStatement("SELECT autorId FROM livros WHERE id=?");

            pstm.setInt(1, livroId);

            ResultSet rs = pstm.executeQuery();
            rs.next();
            int autorId = rs.getInt("autorId");

            return buscarPorId(autorId);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    
}
