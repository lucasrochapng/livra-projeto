package ifpr.pgua.eic.tarefas.model.daos;

import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.model.entities.Categoria;
import ifpr.pgua.eic.tarefas.utils.DBUtils;

public class JDBCCategoriaDAO implements CategoriaDAO{

    private static final String INSERTSQL = "INSERT INTO categoria(nome,descricao) VALUES (?,?)";
    private static final String SELECTSQL = "SELECT * FROM categoria";
    private static final String SEARCHSQL = "SELECT * FROM categoria where id = ?";

    FabricaConexoes fabrica;

    public JDBCCategoriaDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado criar(Categoria categoria) {
        try(Connection con = fabrica.getConnection()){

            PreparedStatement pstm = con.prepareStatement(INSERTSQL, Statement.RETURN_GENERATED_KEYS);

            pstm.setString(1, categoria.getNome());
            pstm.setString(2, categoria.getDescricao());

            int ret = pstm.executeUpdate();
            
            if(ret == 1){
                int id = DBUtils.getLastId(pstm);

                categoria.setId(id);

                return Resultado.sucesso("Categoria cadastrada!", categoria);
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

            ArrayList<Categoria> categorias = new ArrayList<>();
            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");

                Categoria categoria = new Categoria(nome, descricao);
                categoria.setId(id);
                categorias.add(categoria);
            }

            return Resultado.sucesso("Categorias listadas!", categorias);

        } catch (Exception e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado editar(int id, Categoria nova) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editar'");
    }

    @Override
    public Resultado excluir(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'excluir'");
    }

    @Override
    public Resultado buscarPorId(int id) {
        try(Connection con = fabrica.getConnection()) {
            
            PreparedStatement pstm = con.prepareStatement(SEARCHSQL);

            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();

            rs.next();

            String nome = rs.getString("nome");
            String descricao = rs.getString("descricao");

            Categoria categoria = new Categoria(nome, descricao);
            categoria.setId(id);

            return Resultado.sucesso("Buscado com sucesso", categoria);
        } catch (Exception e) {
            return Resultado.erro(e.getMessage());
        }
    }
    
}
