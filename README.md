# Sistema de Biblioteca

Sistema desenvolvido como projeto acadêmico utilizando Java, Spring Boot e Maven. um sistema que permite o usuario criar um conta como leitor e pedir um livro emprestado e devolver após terminar de ler, o usuario tambem pode criar um conta escritor para publicar um livro com titulo, nome do autor, e data de publicação

# Problema a ser solucionado

Muitas bibliotecas de pequeno porte e comunitárias enfrentam dificuldades na organização e no gerenciamento de seus acervos, principalmente devido à ausência de sistemas eficientes para o controle de empréstimos e devoluções. Essa falta de controle pode ocasionar atrasos na entrega dos livros, perda de exemplares em razão da inexistência de um histórico adequado de movimentações e dificuldades na identificação da disponibilidade das obras. Além disso, os processos realizados manualmente tornam o atendimento mais demorado, gerando desperdício de tempo tanto para os responsáveis pela biblioteca quanto para os usuários.

# Objetivo

Desenvolver um sistema de biblioteca que permita o cadastro de usuários, publicação de livros por escritores e empréstimo e devolução de livros por leitores.

# Requisitos funcionais
Requisito 1:  O sistema deve permitir o cadastro de usuários nas categorias de leitor e escritor, solicitando informações pessoais como nome, e-mail e senha

Requisito 2: O sistema deve permitir que os leitores consultem os livros disponíveis no catálogo por meio de uma barra de pesquisa, possibilitando a busca pelo título da obra ou pelo nome do autor

Requisito 3: O sistema deve permitir que os escritores cadastrados publiquem e cadastrem seus livros na plataforma, informando dados como título, autor e data de publicação


# Funcionalidades

## Cadastro e autenticação (FEITO)

Cadastro de usuários (FEITO)
Login de usuários (FEITO)

## Gerenciamento de livros

Cadastro de livros (FEITO)
Edição de livros ( FEITO)
Exclusão de livros (FEITO)

## Empréstimos

Realizar empréstimo de livros(FEITO)
Devolver livros (FEITO)

## Interface

Página inicial (FEITO) 
Exibição dos livros (FEITO) 
Melhorias visuais (FEITO)
Responsividade (FEITO)

## Tecnologias utilizadas

JavaScript, usado na interface para buscar e exibir livros e realizar empréstimos/devoluções.

Maven, para gerenciar dependências e compilar o projeto.

Thymeleaf, para renderizar páginas HTML com dados do servidor.

Spring Data JPA / Hibernate, para persistência e acesso aos dados.

Spring Security, para login e controle de acesso.

H2 Database, banco de dados usado pelo projeto, configurado para armazenar os dados em arquivo.

Spring Web MVC, para as páginas e rotas da aplicação.


## endpoints

| Método | Caminho | O que faz |
|---|---|---|
| `GET` | `/` | Direciona a pessoa para a área de escritor ou leitor, conforme o tipo de conta. Sem login, encaminha para `/login`. |
| `GET` | `/login` | Exibe a página de login. |
| `POST` | `/login` | Processa o login, usando os campos `email` e `senha`. É tratado pelo Spring Security. |
| `GET` | `/cadastro` | Exibe o formulário de cadastro. |
| `POST` | `/cadastro` | Cria uma conta com `email`, `senha` e `tipo`; depois redireciona para o login ou de volta ao cadastro em caso de erro. |
| `GET` | `/escritor/publicar` | Exibe a página para publicar livros. Disponível para escritores. |
| `POST` | `/escritor/publicar` | Cadastra um livro com `titulo`, `autor` e `dataPublicacao`. Disponível para escritores. |
| `GET` | `/leitor/livros` | Exibe a página de livros para leitores. |
| `GET` | `/api/livros` | Retorna a lista de livros em formato JSON. Requer login de leitor ou escritor. |
| `POST` | `/emprestimos` | Empresta um livro, recebendo o `id`; depois retorna à página de livros. Disponível para leitores. |
| `POST` | `/devolucoes` | Devolve um livro, recebendo o `id`; depois retorna à página de livros. Disponível para leitores. |
| `GET` | `/csrf` | Retorna o token CSRF necessário para enviar operações protegidas, como empréstimos e devoluções. |
| `POST` | `/logout` | Encerra a sessão e redireciona para `/login?logout`. É tratado pelo Spring Security. |


## Como baixar e executar o projeto

1. Instale o **Java 21** e o **Git** no computador.
2. Abra o terminal ou PowerShell e clone o repositório:

```powershell
git clone https://github.com/fellipearantes2006-code/Sistema-biblioteca.git
```

3. Entre na pasta do projeto Java:

```powershell
cd Sistema-biblioteca\sistema-biblioteca
```

4. Inicie a aplicação com o Maven Wrapper incluído no projeto:

```powershell
.\mvnw.cmd spring-boot:run
```

5. Quando a inicialização terminar, acesse **http://localhost:8080** no navegador.

## Nome dos integrandes
Fellipe Vieira Arantes

Karita Aparecida Moura




