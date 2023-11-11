package ifpr.pgua.eic.tarefas.model.entities;

public class Troca {
    
    private int id;
    private int id_livro;
    private int id_usuario;
    private int estado;

    public Troca(int id_livro, int id_usuario, int estado){
        this.id_livro = id_livro;
        this.id_usuario = id_usuario;
        this.estado = estado;
    }

    public Troca(int id, int id_livro, int id_usuario, int estado){
        this.id = id;
        this.id_livro = id_livro;
        this.id_usuario = id_usuario;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId_livro() {
        return id_livro;
    }
    public void setId_livro(int id_livro) {
        this.id_livro = id_livro;
    }

    public int getId_usuario() {
        return id_usuario;
    }
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getEstado() {
        return estado;
    }
    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "id: " + id_livro;
    }



}
