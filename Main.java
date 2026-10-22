import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Usuario> usuarios = new ArrayList<>();
    private static final List<Livro> livros = new ArrayList<>();
    private static int proximoIdLivro = 1;

    public static void main(String[] args) {
        adicionarLivroInicial("Dom Casmurro", "Machado de Assis");
        adicionarLivroInicial("O Pequeno Príncipe", "Antoine de Saint-Exupéry");
        adicionarLivroInicial("A Revolução dos Bichos", "George Orwell");

        System.out.println("=== Sistema da Livraria ===");
        while (true) {
            System.out.println("\n1. Criar conta\n2. Entrar\n0. Sair");
            String opcao = lerLinha("Escolha uma opção: ");
            if (opcao == null || opcao.equals("0")) {
                System.out.println("Até logo!");
                return;
            }

            switch (opcao) {
                case "1":
                    criarConta();
                    break;
                case "2":
                    Usuario usuario = entrar();
                    if (usuario != null) {
                        menuUsuario(usuario);
                    }
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void criarConta() {
        System.out.println("\n--- Criar conta ---");
        String nomeUsuario = lerObrigatorio("Nome de usuário: ");
        if (nomeUsuario == null) return;

        if (buscarUsuario(nomeUsuario) != null) {
            System.out.println("Esse nome de usuário já está em uso.");
            return;
        }

        String senha = lerObrigatorio("Senha: ");
        if (senha == null) return;

        usuarios.add(new Usuario(nomeUsuario, senha));
        System.out.println("Conta criada com sucesso. Agora você pode entrar.");
    }

    private static Usuario entrar() {
        System.out.println("\n--- Entrar ---");
        String nomeUsuario = lerObrigatorio("Nome de usuário: ");
        if (nomeUsuario == null) return null;
        String senha = lerObrigatorio("Senha: ");
        if (senha == null) return null;

        Usuario usuario = buscarUsuario(nomeUsuario);
        if (usuario == null || !usuario.getSenha().equals(senha)) {
            System.out.println("Nome de usuário ou senha incorretos.");
            return null;
        }

        System.out.println("Bem-vindo(a), " + usuario.getNomeUsuario() + "!");
        return usuario;
    }

    private static void menuUsuario(Usuario usuario) {
        while (true) {
            System.out.println("\n--- Menu de " + usuario.getNomeUsuario() + " ---");
            System.out.println("1. Ver livros disponíveis\n2. Pedir emprestado\n3. Devolver livro"
                    + "\n4. Publicar livro\n0. Sair da conta");
            String opcao = lerLinha("Escolha uma opção: ");
            if (opcao == null || opcao.equals("0")) return;

            switch (opcao) {
                case "1":
                    listarLivrosDisponiveis();
                    break;
                case "2":
                    emprestarLivro(usuario);
                    break;
                case "3":
                    devolverLivro(usuario);
                    break;
                case "4":
                    publicarLivro();
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void listarLivrosDisponiveis() {
        System.out.println("\n--- Livros disponíveis para empréstimo ---");
        boolean encontrou = false;
        for (Livro livro : livros) {
            if (livro.getEmprestadoPor() == null) {
                mostrarLivro(livro);
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("Nenhum livro disponível no momento.");
    }

    private static void emprestarLivro(Usuario usuario) {
        listarLivrosDisponiveis();
        String entrada = lerLinha("Digite o ID do livro que deseja emprestar: ");
        if (entrada == null) return;

        Livro livro = buscarLivro(entrada);
        if (livro == null || livro.getEmprestadoPor() != null) {
            System.out.println("Livro não encontrado ou indisponível.");
            return;
        }

        livro.setEmprestadoPor(usuario.getNomeUsuario());
        System.out.println("Livro emprestado com sucesso: " + livro.getTitulo());
    }

    private static void devolverLivro(Usuario usuario) {
        System.out.println("\n--- Seus livros emprestados ---");
        boolean encontrou = false;
        for (Livro livro : livros) {
            if (usuario.getNomeUsuario().equals(livro.getEmprestadoPor())) {
                mostrarLivro(livro);
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Você não tem livros emprestados.");
            return;
        }

        String entrada = lerLinha("Digite o ID do livro que deseja devolver: ");
        if (entrada == null) return;
        Livro livro = buscarLivro(entrada);
        if (livro == null || !usuario.getNomeUsuario().equals(livro.getEmprestadoPor())) {
            System.out.println("Esse livro não está emprestado para sua conta.");
            return;
        }

        livro.setEmprestadoPor(null);
        System.out.println("Livro devolvido com sucesso: " + livro.getTitulo());
    }

    private static void publicarLivro() {
        System.out.println("\n--- Publicar livro ---");
        String titulo = lerObrigatorio("Título: ");
        if (titulo == null) return;
        String autor = lerObrigatorio("Nome do autor: ");
        if (autor == null) return;

        Livro livro = new Livro(proximoIdLivro++, titulo, autor);
        livros.add(livro);
        System.out.println("Livro publicado e disponível para empréstimo. ID: " + livro.getId());
    }

    private static void adicionarLivroInicial(String titulo, String autor) {
        livros.add(new Livro(proximoIdLivro++, titulo, autor));
    }

    private static Usuario buscarUsuario(String nomeUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNomeUsuario().equalsIgnoreCase(nomeUsuario)) return usuario;
        }
        return null;
    }

    private static Livro buscarLivro(String id) {
        try {
            int idLivro = Integer.parseInt(id);
            for (Livro livro : livros) {
                if (livro.getId() == idLivro) return livro;
            }
        } catch (NumberFormatException e) {
            return null;
        }
        return null;
    }

    private static void mostrarLivro(Livro livro) {
        System.out.println("ID: " + livro.getId() + " | " + livro.getTitulo() + " — " + livro.getAutor());
    }

    private static String lerObrigatorio(String mensagem) {
        while (true) {
            String valor = lerLinha(mensagem);
            if (valor == null) return null;
            valor = valor.trim();
            if (!valor.isEmpty()) return valor;
            System.out.println("Este campo não pode ficar vazio.");
        }
    }

    private static String lerLinha(String mensagem) {
        System.out.print(mensagem);
        return scanner.hasNextLine() ? scanner.nextLine() : null;
    }

}