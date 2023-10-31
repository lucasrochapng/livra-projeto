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

    FabricaConexoes fabrica;

    public JDBCAutorDAO(FabricaConexoes fabrica){
        this.fabrica = fabrica;
    }

    @Override
    public Resultado criar(Autor autor) {
        
        try(Connection con = fabrica.getConnection()){

            PreparedStatement pstm = con.prepareStatement(INSERTSQL, Statement.RETURN_GENERATED_KEYS);

            pstm.setString(1, autor.getNome());

            int ret = pstm.executeUpdate();
            
            if(ret == 1){
                int id = DBUtils.getLastId(pstm);

                autor.setId(id);

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
                
                Autor autor = new Autor(nome);
                autor.setId(id);
                autores.add(autor);
            }

            return Resultado.sucesso("Autores listados!", autores);

        } catch (Exception e) {
            return Resultado.erro(e.getMessage());
        }
        
    }

    @Override
    public Resultado buscarPorId(int id) {
        
        try(Connection con = fabrica.getConnection()) {
            
            PreparedStatement pstm = con.prepareStatement(SEARCHSQL);

            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();

            rs.next();

            String nome = rs.getString("nome");
            
            Autor autor = new Autor(nome);
            autor.setId(id);

            return Resultado.sucesso("Buscado com sucesso", autor);
        } catch (Exception e) {
            return Resultado.erro(e.getMessage());
        }
        
    }

    @Override
    public Resultado editar(int id, Autor novo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editar'");
    }

    @Override
    public Resultado excluir(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'excluir'");
    }
    
}
