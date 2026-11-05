package br.com.biblioteca.sistema_biblioteca;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BibliotecaService {

    private final LivroRepository livroRepository;

    public BibliotecaService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    // Listar todos os livros publicados
    public List<Livro> listarLivros() {
        return livroRepository.findAll();
    }

    // Publicar um novo livro
    public Livro cadastrarLivro(
            String titulo,
            String autor,
            LocalDate dataPublicacao,
            String emailEscritor) {

        Livro livro = new Livro(
                titulo,
                autor,
                dataPublicacao,
                emailEscritor
        );

        return livroRepository.save(livro);
    }

    // Emprestar um livro
    public boolean emprestarLivro(Long id, String emailUsuario) {

        Livro livro = livroRepository.findById(id).orElse(null);

        if (livro == null || livro.getEmprestadoPor() != null) {
            return false;
        }

        livro.setEmprestadoPor(emailUsuario);
        livroRepository.save(livro);

        return true;
    }

    // Devolver um livro
    public boolean devolverLivro(Long id, String emailUsuario) {

        Livro livro = livroRepository.findById(id).orElse(null);

        if (livro == null ||
                !emailUsuario.equals(livro.getEmprestadoPor())) {
            return false;
        }

        livro.setEmprestadoPor(null);
        livroRepository.save(livro);

        return true;
    }
}

