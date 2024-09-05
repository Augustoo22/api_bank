# Documentação da API Bancária

### Link para execução
[Execução do Projeto](Execução//README.md)


## 1. Introdução

### Visão geral do projeto
O projeto tem como objetivo desenvolver uma API bancária, onde será possível realizar operações financeiras básicas, tais como transações, depósitos e saques. A API visa oferecer uma plataforma segura e eficiente para a gestão de contas bancárias.

### Objetivos e propósito do sistema
O sistema tem como propósito fornecer uma interface robusta para que os usuários possam gerenciar suas contas bancárias de maneira eficaz. Entre os principais objetivos estão:
- Facilitar transações financeiras entre contas.
- Garantir a segurança e a privacidade dos dados dos usuários.
- Prover uma interface intuitiva para operações bancárias.

### Benefícios esperados do projeto
- **Facilidade de uso:** Uma API intuitiva que simplifica operações financeiras.
- **Segurança:** Implementação de autenticação robusta para proteger informações dos usuários.
- **Eficiência:** Processamento rápido de transações e consultas.
- **Transparência:** Disponibilização de extratos detalhados para os usuários.

## 2. Visão Geral do Sistema

### Descrição do sistema
A API bancária permitirá que os usuários realizem operações como depósitos, saques e transferências. Cada usuário terá uma conta associada com informações como nome, idade, saldo e histórico de transações. O sistema suportará autenticação para garantir que apenas usuários autorizados possam acessar e realizar operações em suas contas.

### Público-alvo do sistema
O sistema é destinado a:
- **Usuários finais:** Indivíduos que desejam gerenciar suas contas bancárias online.
- **Desenvolvedores:** Equipes técnicas que buscam integrar soluções bancárias em suas aplicações.

### Requisitos funcionais e não funcionais

**Requisitos Funcionais:**
- Autenticação de usuários.
- Cadastro e gerenciamento de contas.
- Realização de depósitos, saques e transferências.
- Consulta de saldo e extrato de conta.

**Requisitos Não Funcionais:**
- **Segurança:** Proteção dos dados dos usuários e transações.
- **Desempenho:** Respostas rápidas às operações e consultas.
- **Escalabilidade:** Capacidade de suportar um número crescente de usuários e transações.

## 3. Arquitetura do Sistema

### Explicação da arquitetura MVC (Model-View-Controller)
A API bancária será desenvolvida utilizando a arquitetura MVC (Model-View-Controller), que separa a aplicação em três componentes principais:

### Papel de cada componente

- **Model (Modelo):**
  - Representa a lógica de negócios e os dados da aplicação.
  - Gerencia a validação de dados e a interação com o banco de dados.
  - Exemplo: Classes que representam contas bancárias e transações.

- **View (Visão):**
  - Responsável pela apresentação dos dados ao usuário.
  - No contexto de uma API, a View é representada pelas respostas HTTP enviadas ao cliente.
  - Exemplo: JSON com informações de saldo ou confirmação de uma transação.

- **Controller (Controlador):**
  - Gerencia a lógica de controle e a comunicação entre o Model e a View.
  - Processa as requisições HTTP, interage com o Model e retorna a resposta apropriada através da View.
  - Exemplo: Endpoints que processam depósitos, saques e transferências.

### Uso do padrão Repository para acesso a dados
O padrão Repository será utilizado para a camada de acesso a dados, abstraindo as operações de persistência e oferecendo uma interface limpa para os demais componentes da aplicação. Isso facilita a manutenção e testes, além de promover a separação de responsabilidades.

- **Benefícios do padrão Repository:**
  - Encapsulamento da lógica de acesso a dados.
  - Facilita a troca da camada de persistência sem impactar outras partes do sistema.
  - Melhora a testabilidade ao permitir o uso de repositórios falsos (mock repositories) em testes unitários.

## 4. Requisitos Funcionais

### Lista detalhada de funcionalidades do sistema
- Cadastro de novos usuários.
- Deletar usuários.
- Login e autenticação de usuários.
- Consulta de saldo.
- Transferência entre contas.
- Geração e consulta de extratos de transações.
- Atualização de informações de perfil do usuário.

### Lista detalhada de funcionalidades do sistema com endpoints

1. **Cadastro de Usuário**
   - **Endpoint:** `POST /api/users`
   - **Descrição:** Criação de um novo usuário.

2. **Deletar Usuário**
   - **Endpoint:** `DELETE /api/users/{id}`
   - **Descrição:** Deletar um usuário existente.

3. **Login de Usuário**
   - **Endpoint:** `POST /api/auth/login`
   - **Descrição:** Autenticação de um usuário e geração de token de acesso.

4. **Consulta de Saldo**
   - **Endpoint:** `GET /api/accounts/{id}/balance`
   - **Descrição:** Consultar o saldo da conta do usuário.

5. **Transferência entre Contas**
   - **Endpoint:** `POST /api/accounts/transfer`
   - **Descrição:** Transferir valores entre contas de usuários.

6. **Consulta de Extrato**
   - **Endpoint:** `GET /api/accounts/{id}/transactions`
   - **Descrição:** Consultar o histórico de transações da conta do usuário.

7. **Atualização de Informações de Perfil do Usuário**
   - **Endpoint:** `PUT /api/users/{id}`
   - **Descrição:** Atualizar informações de perfil do usuário.

### Fluxos de trabalho do usuário
- **Cadastro e Login:** Usuário se cadastra, faz login e recebe token de acesso.
- **Operações Bancárias:** Usuário realiza depósitos, saques e transferências após autenticação.
- **Consultas:** Usuário consulta saldo e extrato após autenticação.

## 5. Requisitos Não Funcionais

### Desempenho esperado do sistema
- Respostas a operações comuns (como consulta de saldo, depósitos e transferências) devem ser rápidas e eficientes, com tempo de resposta inferior a 1 segundo na maioria dos casos.

### Segurança e autenticação
- Uso de Spring Security para autenticação e autorização.
- Proteção contra ataques comuns (SQL Injection, XSS, CSRF).
- Armazenamento seguro de senhas usando hashing (BCrypt).

### Escalabilidade e manutenibilidade
- Arquitetura modular para facilitar a expansão de funcionalidades.
- Uso de serviços de nuvem escaláveis (ex: AWS, Azure) para lidar com aumentos de carga.
- Código bem documentado e separado em módulos.

## 6. Tecnologias Utilizadas

### Linguagens de programação
- Java 17

### Frameworks
- Spring Boot (Java)
- Spring Security para autenticação e autorização.

### Bancos de dados
- PostgreSQL

### Ferramentas de desenvolvimento
- Git para controle de versão.
- Docker para contêinerização.

## 7. Modelo de Dados

### Estrutura do banco de dados
- **Usuário:** id, nome, idade, email, senha, saldo
- **Transação:** id, tipo, valor, data, conta_origem, conta_destino

### Relacionamentos entre entidades
- Usuário possui muitas Transações.

### Esquema de armazenamento
- Utilização de banco de dados relacional (PostgreSQL).

## 8. Interfaces do Usuário

### Layout e design das interfaces
- Interface web simples e intuitiva para gerenciar contas e realizar transações.

### Funcionalidades específicas de cada tela
- **Tela de login:** Entrada de credenciais.
- **Tela de cadastro:** Formulário de registro.
- **Dashboard:** Visão geral do saldo e opções de transações.
- **Tela de transações:** Formulário para depósitos, saques e transferências.

### Fluxos de interação do usuário
- Login -> Dashboard -> Realização de transações -> Consulta de extrato.

## 9. Arquitetura de Implementação

### Organização do código-fonte
- Divisão em diretórios: controllers, models, views, repositories, services.
- Uso de convenções de nomenclatura claras.

### Divisão em módulos e componentes
- Módulos separados para autenticação, transações, e usuários.

### Dependências entre os componentes
- Controllers dependem de Services.
- Services dependem de Repositories.
- Repositories dependem de Models.

## 10. Planejamento de Implantação

### Ambientes de implantação
- **Desenvolvimento:** Ambiente para desenvolvimento de novas funcionalidades.
- **Teste:** Ambiente para testes das funcionalidades desenvolvidas.
- **Produção:** Ambiente final utilizado pelos usuários.

### Procedimentos de implantação
- Uso de CI/CD para integração e entrega contínua.
- Deploy automatizado usando ferramentas como Jenkins ou GitHub Actions.

### Migração de dados, se necessário
- Scripts de migração para configuração inicial do banco de dados.

## 11. Gestão de Configuração e Controle de Versão

### Políticas de controle de versão
- Uso de GitFlow para gerenciamento de branches.

### Ramificação do código-fonte
- Branches: main, develop, feature.

### Uso de ferramentas de controle de versão
- Git para versionamento.
- GitHub para hospedagem de repositório.

## 12. Considerações de Segurança

### Mecanismos de autenticação e autorização
- Implementação de Spring Security.

### Proteção contra vulnerabilidades conhecidas
- Validação de entrada para prevenir SQL Injection.
- Proteção contra XSS e CSRF.
- Armazenamento seguro de senhas com hashing (BCrypt).

## 13. Considerações de Manutenção

### Planos de suporte pós-implantação
- Suporte via email.
- Base de conhecimento online.

### Processo de correção de bugs e implementação de melhorias
- Releases regulares para implementar melhorias e corrigir bugs.
- Monitoramento contínuo do sistema para

 identificar e resolver problemas rapidamente.

### Atualizações de segurança e de software
- Atualizações periódicas para manter a segurança e funcionalidade do sistema.
- Patches de segurança aplicados conforme necessário.

### Responsáveis pelo projeto
- Victor Augusto
- Misael Willian
- Guilherme Martins
