package ifpr.pgua.eic.tarefas.model.entities;

import java.time.LocalDate;

public class Tarefa {
    
    private int id;
    private String titulo;
    private String descricao;
    private LocalDate dataPrazo;
    private Categoria categoria;

    public Tarefa(String titulo, String descricao, LocalDate dataPrazo, Categoria categoria) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataPrazo = dataPrazo;
        this.categoria = categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataPrazo() {
        return dataPrazo;
    }

    public void setDataPrazo(LocalDate dataPrazo) {
        this.dataPrazo = dataPrazo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    } 

    @Override
    public String toString() {
        return titulo;
    }
}
