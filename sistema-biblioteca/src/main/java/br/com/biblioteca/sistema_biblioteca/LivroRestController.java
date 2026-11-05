
package br.com.biblioteca.sistema_biblioteca;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LivroRestController {

    private final BibliotecaService bibliotecaService;

    public LivroRestController(BibliotecaService bibliotecaService) {
        this.bibliotecaService = bibliotecaService;
    }

    @GetMapping("/livros")
    public List<Livro> listarLivros() {
        return bibliotecaService.listarLivros();
    }
}

