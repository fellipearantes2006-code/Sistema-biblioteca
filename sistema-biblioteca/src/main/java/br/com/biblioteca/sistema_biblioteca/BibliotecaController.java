package br.com.biblioteca.sistema_biblioteca;

import java.security.Principal;
import java.time.LocalDate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BibliotecaController {

    private final BibliotecaService bibliotecaService;
    private final UsuarioService usuarioService;

    public BibliotecaController(
            BibliotecaService bibliotecaService,
            UsuarioService usuarioService) {
        this.bibliotecaService = bibliotecaService;
        this.usuarioService = usuarioService;
    }

    // Direcionar o usuário conforme seu tipo de conta
    @GetMapping("/")
    public String inicio(Principal principal) {

        if (principal == null) {
            return "redirect:/login";
        }

        Usuario usuario = usuarioService.buscarPorEmail(principal.getName());

        if (usuario == null) {
            return "redirect:/login";
        }

        if (usuario.getTipo() == TipoUsuario.ESCRITOR) {
            return "redirect:/escritor/publicar";
        }

        return "redirect:/leitor/livros";
    }

    



    // Página de publicação do escritor
    @GetMapping("/escritor/publicar")
    public String paginaPublicar() {
        return "publicar-livro";
    }

    // Cadastrar um livro publicado pelo escritor
    @PostMapping("/escritor/publicar")
    public String cadastrarLivro(
            @RequestParam String titulo,
            @RequestParam String autor,
            @RequestParam LocalDate dataPublicacao,
            Principal principal,
            RedirectAttributes redirect) {

        if (titulo.isBlank() || autor.isBlank()) {
            redirect.addFlashAttribute(
                    "mensagem", "Preencha o título e o nome do autor.");
            return "redirect:/escritor/publicar";
        }

        bibliotecaService.cadastrarLivro(
                titulo.trim(),
                autor.trim(),
                dataPublicacao,
                principal.getName()
        );

        redirect.addFlashAttribute(
                "mensagem", "Livro publicado com sucesso!");

        return "redirect:/escritor/publicar";
    }

    // Página com os livros disponíveis para os leitores
        @GetMapping("/leitor/livros")
        public String listarLivros(Model model, Principal principal) {
        model.addAttribute("livros", bibliotecaService.listarLivros());
        model.addAttribute("emailUsuario", principal.getName());
        return "leitor-livros";
        }   

    // Emprestar um livro
    @PostMapping("/emprestimos")
    public String emprestarLivro(
            @RequestParam Long id,
            Principal principal,
            RedirectAttributes redirect) {

        if (bibliotecaService.emprestarLivro(id, principal.getName())) {
            redirect.addFlashAttribute(
                    "mensagem", "Livro emprestado com sucesso!");
        } else {
            redirect.addFlashAttribute(
                    "mensagem", "Livro não encontrado ou indisponível.");
        }

        return "redirect:/leitor/livros";
    }

    // Devolver um livro
    @PostMapping("/devolucoes")
    public String devolverLivro(
            @RequestParam Long id,
            Principal principal,
            RedirectAttributes redirect) {

        if (bibliotecaService.devolverLivro(id, principal.getName())) {
            redirect.addFlashAttribute(
                    "mensagem", "Livro devolvido com sucesso!");
        } else {
            redirect.addFlashAttribute(
                    "mensagem",
                    "Livro não encontrado ou não pertence a este leitor.");
        }

        return "redirect:/leitor/livros";
    }
}

