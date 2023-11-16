package ifpr.pgua.eic.tarefas.model.daos;

import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.model.entities.Usuario;
import ifpr.pgua.eic.tarefas.utils.DBUtils;

public class JDBCUsuarioDAO implements UsuarioDAO {

    private static final String INSERTSQL = "INSERT INTO usuarios(nome, nomeUsuario, senha, telefone, idade) VALUES (?,?,?,?,?)";
    private static final String SEARCHSQL = "SELECT * FROM usuarios where id = ?";

    FabricaConexoes fabrica;

    public JDBCUsuarioDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado criar(Usuario usuario) {
        try(Connection con = fabrica.getConnection()){
            PreparedStatement pstm = con.prepareStatement(INSERTSQL, Statement.RETURN_GENERATED_KEYS);

            pstm.setString(1, usuario.getNome());
            pstm.setString(2, usuario.getNomeUsuario());
            pstm.setString(3, usuario.getSenha());
            pstm.setInt(4, usuario.getTelefone());
            pstm.setInt(5, usuario.getIdade());

            int ret = pstm.executeUpdate();

            if(ret == 1){
                int id = DBUtils.getLastId(pstm);
                usuario.setId(id);
                return Resultado.sucesso("Usuario cadastrado!", usuario);
            }
            return Resultado.erro("Erro desconhecido");
        }catch(SQLException e){
            return Resultado.erro(e.getMessage());
        }
    }



    @Override
    public Resultado listar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listar'");
    }

    @Override
    public Resultado editar(int id, Usuario novo) {
        try(Connection con = fabrica.getConnection();) {
            PreparedStatement pstm = con.prepareStatement("UPDATE usuarios SET nome=?, nomeUsuario=?, senha=?, telefone=?, idade=? WHERE id=?");

            pstm.setInt(1, id);
            pstm.setString(2, novo.getNome());
            pstm.setString(3, novo.getNomeUsuario());
            pstm.setString(4, novo.getSenha());
            pstm.setInt(5, novo.getTelefone());
            pstm.setInt(6, novo.getIdade());

            int ret = pstm.executeUpdate();

            if(ret == 1){
                return Resultado.sucesso("Usuário atualizado com sucesso!", novo);
            }
            return Resultado.erro("Usuário não atualizado!");

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
    public Resultado buscarPorId(int id){
        try(Connection con = fabrica.getConnection()){
            PreparedStatement pstm = con.prepareStatement(SEARCHSQL);

            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();

            rs.next();

            String nome = rs.getString("nome");
            String nomeUsuario = rs.getString("nomeUsuario");
            String senha = rs.getString("senha");
            int telefone = rs.getInt("telefone");
            int idade = rs.getInt("idade");

            Usuario usuario = new Usuario(nome, nomeUsuario, senha, telefone, idade);
            usuario.setId(id);

            return Resultado.sucesso("Buscado com sucesso", usuario);
        } catch(Exception e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado logar(String nomeUsuario, String senha) {
        try(Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM usuarios WHERE nomeUsuario=? AND senha=?");

            pstm.setString(1, nomeUsuario);
            pstm.setString(2, senha);

            ResultSet rs = pstm.executeQuery();

            if(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String nomeUsuarioLogin = rs.getString("nomeUsuario");
                String senhaLogin = rs.getString("senha");
                int telefone = rs.getInt("telefone");
                int idade = rs.getInt("idade");

                Usuario usuario = new Usuario(id, nome, nomeUsuarioLogin, senhaLogin, telefone, idade);

                return Resultado.sucesso("Bem vindo ao Livra!", usuario);
            }
            return Resultado.erro("Nome de usuário ou senha incorretos. Por favor, tente novamente.");

        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado buscarUsuarioLivro(int livroId) {
        try(Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT usuarioId FROM lv_livros WHERE id=?");

            pstm.setInt(1, livroId);

            ResultSet rs = pstm.executeQuery();
            rs.next();
            int usuarioId = rs.getInt("usuarioId");

            return buscarPorId(usuarioId);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }



   




    
}
