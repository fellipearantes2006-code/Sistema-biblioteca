
package br.com.biblioteca.sistema_biblioteca;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BibliotecaController {

    private final BibliotecaService bibliotecaService;

    public BibliotecaController(BibliotecaService bibliotecaService) {
        this.bibliotecaService = bibliotecaService;
    }

    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("livros", bibliotecaService.listarLivros());
        return "index";
    }

    @PostMapping("/livros")
    public String cadastrarLivro(
            @RequestParam String titulo,
            @RequestParam String autor,
            RedirectAttributes redirect) {

        if (titulo.isBlank() || autor.isBlank()) {
            redirect.addFlashAttribute(
                    "mensagem", "Preencha o título e o autor.");
            return "redirect:/";
        }

        bibliotecaService.cadastrarLivro(titulo.trim(), autor.trim());
        redirect.addFlashAttribute(
                "mensagem", "Livro cadastrado com sucesso!");

        return "redirect:/";
    }

    @PostMapping("/emprestimos")
    public String emprestarLivro(
            @RequestParam int id,
            @RequestParam String nomeUsuario,
            RedirectAttributes redirect) {

        if (nomeUsuario.isBlank()) {
            redirect.addFlashAttribute(
                    "mensagem", "Informe o nome do leitor.");
        } else if (bibliotecaService.emprestarLivro(id, nomeUsuario.trim())) {
            redirect.addFlashAttribute(
                    "mensagem", "Empréstimo realizado com sucesso!");
        } else {
            redirect.addFlashAttribute(
                    "mensagem", "Livro não encontrado ou indisponível.");
        }

        return "redirect:/";
    }

    @PostMapping("/devolucoes")
    public String devolverLivro(
            @RequestParam int id,
            @RequestParam String nomeUsuario,
            RedirectAttributes redirect) {

        if (nomeUsuario.isBlank()) {
            redirect.addFlashAttribute(
                    "mensagem", "Informe o nome do leitor.");
        } else if (bibliotecaService.devolverLivro(id, nomeUsuario.trim())) {
            redirect.addFlashAttribute(
                    "mensagem", "Devolução realizada com sucesso!");
        } else {
            redirect.addFlashAttribute(
                    "mensagem", "Livro não encontrado ou não emprestado para esse leitor.");
        }

        return "redirect:/";
    }
}
