
package br.com.biblioteca.sistema_biblioteca;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario cadastrar(String email, String senha, TipoUsuario tipo) {
        String emailNormalizado = email.trim().toLowerCase();

        if (emailNormalizado.isBlank() || senha == null || senha.isBlank()) {
            throw new IllegalArgumentException(
                    "Preencha o e-mail e a senha.");
        }

        if (!emailNormalizado.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            throw new IllegalArgumentException("Informe um e-mail válido.");
        }

        if (senha.length() < 8) {
            throw new IllegalArgumentException(
                    "A senha deve ter pelo menos 8 caracteres.");
        }

        if (usuarioRepository.existsByEmail(emailNormalizado)) {
            throw new IllegalArgumentException(
                    "Este e-mail já está cadastrado.");
        }

        if (tipo == null) {
            throw new IllegalArgumentException(
                    "Selecione Leitor ou Escritor.");
        }

        Usuario usuario = new Usuario(
                emailNormalizado,
                passwordEncoder.encode(senha),
                tipo);

        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorEmail(String email) {
    return usuarioRepository.findByEmail(email.trim().toLowerCase())
            .orElse(null);
    }
}
