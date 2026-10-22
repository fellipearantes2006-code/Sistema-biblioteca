
package br.com.biblioteca.sistema_biblioteca;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BibliotecaService {

    private final List<Livro> livros = new ArrayList<>();
    private int proximoId = 1;

    public BibliotecaService() {
        cadastrarLivro("Dom Casmurro", "Machado de Assis");
        cadastrarLivro("O Pequeno Príncipe", "Antoine de Saint-Exupéry");
        cadastrarLivro("A Revolução dos Bichos", "George Orwell");
    }

    public List<Livro> listarLivros() {
        return livros;
    }

    public Livro cadastrarLivro(String titulo, String autor) {
        Livro livro = new Livro(proximoId++, titulo, autor);
        livros.add(livro);
        return livro;
    }

    public boolean emprestarLivro(int id, String nomeUsuario) {
        Livro livro = buscarPorId(id);

        if (livro == null || livro.getEmprestadoPor() != null) {
            return false;
        }

        livro.setEmprestadoPor(nomeUsuario);
        return true;
    }

    public boolean devolverLivro(int id, String nomeUsuario) {
        Livro livro = buscarPorId(id);

        if (livro == null ||
                !nomeUsuario.equals(livro.getEmprestadoPor())) {
            return false;
        }

        livro.setEmprestadoPor(null);
        return true;
    }

    private Livro buscarPorId(int id) {
        for (Livro livro : livros) {
            if (livro.getId() == id) {
                return livro;
            }
        }

        return null;
    }
}
