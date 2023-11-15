package ifpr.pgua.eic.tarefas.model.entities;

public class Livro {
    
    private int id;
    private String titulo;
    private Autor autor;
    private String genero;
    private String descricao;
    private String contato;
    private Usuario usuario;

    public Livro(int id, String titulo, Autor autor, String genero, String descricao, String contato, Usuario usuario){
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.descricao = descricao;
        this.contato = contato;
        this.usuario = usuario;
    }

    public Livro(String titulo, Autor autor, String genero, String descricao, String contato, Usuario usuario){
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.descricao = descricao;
        this.contato = contato;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }
    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getContato() {
        return contato;
    }
    public void setContato(String contato) {
        this.contato = contato;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    

    @Override
    public String toString() {
        return titulo;
    }

}
