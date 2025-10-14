# Economiks - Backend

![Java](https://img.shields.io/badge/Java-21-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)
![Maven](https://img.shields.io/badge/Maven-4.0.0-red.svg)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue.svg)

Backend da aplicação Economiks, um sistema de gestão (ERP) focado em simplicidade e desenhado para pequenos negócios. A aplicação é construída com uma filosofia **offline-first**, garantindo que a operação continue mesmo sem conexão com a internet.

## 1. Visão Geral do Projeto

O Economiks visa substituir o controle manual de finanças (em papel) por uma aplicação móvel moderna e confiável. O sistema permitirá o registro de compras, vendas, despesas e a visualização de balanços financeiros diários, semanais e mensais.

## 2. Arquitetura

Este projeto utiliza a **Arquitetura de Corte Vertical (Vertical Slice Architecture)**.

Em vez de organizar o código por camadas técnicas (ex: `controllers`, `services`, `repositories`), nós organizamos o código por **funcionalidade de negócio (feature)**. Cada "fatia" vertical representa um **Caso de Uso** completo e autocontido do sistema.

### Vantagens dessa abordagem:

* **Alta Coesão:** Todo o código relacionado a uma funcionalidade (ex: "Cadastrar Produto") reside em um único local.
* **Baixo Acoplamento:** As funcionalidades são independentes umas das outras. Alterar a lógica de registro de vendas não quebra a funcionalidade de autenticação.
* **Redução da Carga Cognitiva:** Para dar manutenção ou entender uma feature, o desenvolvedor precisa olhar para um número limitado e bem localizado de arquivos.
* **Manutenção e Testabilidade:** Testar um caso de uso de ponta a ponta se torna uma tarefa mais simples e focada.

## 3. Stack Tecnológica

| Categoria              | Tecnologia                  | Motivo da Escolha                                                              |
| ---------------------- | --------------------------- | ------------------------------------------------------------------------------ |
| **Linguagem** | Java 21 (LTS)               | Versão estável com suporte de longo prazo, performance e recursos modernos.      |
| **Framework Principal** | Spring Boot 3.x             | Agilidade, ecossistema robusto e configuração simplificada (convention over configuration). |
| **Build & Dependências** | Maven                       | Padrão de mercado para projetos Java, gerenciamento de dependências consolidado. |
| **Persistência de Dados** | Spring Data JPA / Hibernate | Abstrai e simplifica drasticamente o acesso ao banco de dados, reduzindo código boilerplate. |
| **Banco de Dados (Prod)**| PostgreSQL                  | Banco de dados relacional open-source, poderoso, confiável e pronto para a nuvem. |
| **Banco de Dados (Dev)** | H2 Database                 | Banco de dados em memória para desenvolvimento e testes ágeis, sem necessidade de instalação. |
| **Migrações de Schema** | Flyway                      | Versionamento e automação das alterações na estrutura do banco de dados.         |
| **Segurança** | Spring Security + JWT       | Padrão de mercado para proteger APIs RESTful de forma stateless.               |
| **Utilitários** | Lombok                      | Reduz a verbosidade do código Java (getters, setters, construtores, etc).      |

## 4. Estrutura do Projeto

A estrutura de pastas reflete a Arquitetura de Corte Vertical.

````
economiks.api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── economiks/
│   │   │           ├── EconomiksApplication.java
│   │   │           │
│   │   │           ├── config/
│   │   │           │   ├── SecurityConfig.java
│   │   │           │   └── GlobalExceptionHandler.java
│   │   │           │
│   │   │           ├── core/
│   │   │           │   ├── domain/
│   │   │           │   │   ├── entity/
│   │   │           │   │   │   ├── Usuario.java
│   │   │           │   │   │   ├── Produto.java
│   │   │           │   │   │   └── Venda.java
│   │   │           │   │   └── repository/
│   │   │           │   │       ├── UsuarioRepository.java
│   │   │           │   │       └── ProdutoRepository.java
│   │   │           │   └── security/
│   │   │           │       └── TokenService.java
│   │   │           │
│   │   │           └── features/
│   │   │               │
│   │   │               ├── autenticacao/
│   │   │               │   └── login/
│   │   │               │       ├── LoginController.java
│   │   │               │       ├── LoginService.java
│   │   │               │       ├── LoginRequest.java
│   │   │               │       └── TokenResponse.java
│   │   │               │
│   │   │               ├── produtos/
│   │   │               │   ├── ProdutoResponse.java
│   │   │               │   │
│   │   │               │   ├── cadastrar/
│   │   │               │   │   ├── CadastrarProdutoController.java
│   │   │               │   │   ├── CadastrarProdutoService.java
│   │   │               │   │   └── CadastrarProdutoRequest.java
│   │   │               │   │
│   │   │               │   ├── listarTodos/
│   │   │               │   │   ├── ListarTodosProdutosController.java
│   │   │               │   │   └── ListarTodosProdutosService.java
│   │   │               │   │
│   │   │               │   ├── buscarPorId/
│   │   │               │   │   ├── BuscarProdutoPorIdController.java
│   │   │               │   │   └── BuscarProdutoPorIdService.java
│   │   │               │   │
│   │   │               │   └── atualizar/
│   │   │               │       ├── AtualizarProdutoController.java
│   │   │               │       ├── AtualizarProdutoService.java
│   │   │               │       └── AtualizarProdutoRequest.java
│   │   │               │
│   │   │               └── vendas/
│   │   │                   └── registrar/
│   │   │                       ├── RegistrarVendaController.java
│   │   │                       ├── RegistrarVendaService.java
│   │   │                       ├── RegistrarVendaRequest.java
│   │   │                       └── VendaRegistradaResponse.java
│   │   │
│   │   └── resources/
│   │       ├── application.properties
│   │       └── db/
│   │           └── migration/
│   │               └── ...
│   │
│   └── test/
│       └── java/
│           └── com/
│               └── economiks/
│                   └── features/
│                       └── produtos/
│                           └── cadastrar/
│                               └── CadastrarProdutoServiceTest.java
│
└── pom.xml
````

* **`/core`**: Contém o núcleo do domínio de negócio (Entidades, Repositórios) que é estável e compartilhado por múltiplas features.
* **`/features`**: Cada subpasta aqui é uma "fatia vertical". Por exemplo, `features/produtos/cadastrar/` contém o Controller, Service e DTOs necessários **apenas** para a funcionalidade de cadastrar um novo produto.
