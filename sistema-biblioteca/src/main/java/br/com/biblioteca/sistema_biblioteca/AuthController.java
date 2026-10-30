
package br.com.biblioteca.sistema_biblioteca;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/cadastro")
    public String cadastro() {
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String criarConta(
            @RequestParam String email,
            @RequestParam String senha,
            @RequestParam String tipo,
            RedirectAttributes redirect) {

        try {
            TipoUsuario tipoUsuario = TipoUsuario.valueOf(tipo);
            usuarioService.cadastrar(email, senha, tipoUsuario);

            redirect.addFlashAttribute(
                    "mensagem", "Conta criada! Agora você pode entrar.");
            return "redirect:/login";

        } catch (IllegalArgumentException e) {
            redirect.addFlashAttribute("erro", e.getMessage());
            return "redirect:/cadastro";
        }
    }
}
