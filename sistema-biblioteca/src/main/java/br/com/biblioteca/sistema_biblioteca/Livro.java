
package br.com.biblioteca.sistema_biblioteca;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String autor;

    @Column(nullable = false)
    private LocalDate dataPublicacao;

    // E-mail do escritor que publicou o livro
    @Column(nullable = false)
    private String publicadoPorEmail;

    // E-mail do leitor que pegou o livro emprestado
    private String emprestadoPor;

    public Livro() {
    }

    public Livro(String titulo, String autor,
                 LocalDate dataPublicacao, String publicadoPorEmail) {
        this.titulo = titulo;
        this.autor = autor;
        this.dataPublicacao = dataPublicacao;
        this.publicadoPorEmail = publicadoPorEmail;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public String getPublicadoPorEmail() {
        return publicadoPorEmail;
    }

    public String getEmprestadoPor() {
        return emprestadoPor;
    }

    public void setEmprestadoPor(String emprestadoPor) {
        this.emprestadoPor = emprestadoPor;
    }
}
