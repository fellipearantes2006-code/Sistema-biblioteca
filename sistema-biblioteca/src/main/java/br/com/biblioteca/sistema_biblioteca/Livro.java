package br.com.biblioteca.sistema_biblioteca;

public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private String emprestadoPor;

    public Livro(int id, String titulo, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEmprestadoPor() {
        return emprestadoPor;
    }

    public void setEmprestadoPor(String emprestadoPor) {
        this.emprestadoPor = emprestadoPor;
    }
}
