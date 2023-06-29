# Documentação do Projeto: Sistema de Banco Escolar

## Visão Geral do Projeto

O projeto consiste em um sistema de banco escolar que permite a atualização de informações relacionadas a alunos, professores e disciplinas. O objetivo é fornecer uma interface amigável para que os usuários possam realizar essas atualizações de forma simples e intuitiva.

- **Sistema de banco escolar:** O projeto refere-se a um sistema de software que é desenvolvido para gerenciar informações relacionadas a uma escola, como dados de alunos, professores e disciplinas.

- **Atualização de informações:** O sistema permite que os usuários atualizem as informações referentes a alunos, professores e disciplinas. Isso significa que eles podem adicionar, modificar ou excluir dados específicos relacionados a essas entidades.

- **Alunos, professores e disciplinas:** Essas são as entidades principais que o sistema gerencia. Os usuários podem inserir informações sobre os alunos, como nome, data de nascimento, CPF, email. Da mesma forma, eles podem fornecer detalhes sobre os professores, nome, data de nascimento, CPF, email, ID da disciplina. Além disso, o sistema também permite a atualização de informações relacionadas a disciplinas, como nome da disciplina, carga horária, etc.

- **Interface amigável:** O objetivo do projeto é fornecer uma interface de usuário que seja fácil de usar e intuitiva. Isso significa que os usuários poderão interagir com o sistema de forma simples e compreensível, sem a necessidade de conhecimentos técnicos avançados.

- **Simples e intuitiva:** A interface do sistema foi projetada de tal forma que as ações de atualização de informações sejam fáceis de executar e compreensíveis para os usuários. Isso pode envolver o uso de elementos visuais, menus, botões e outras ferramentas que facilitem a interação com o sistema.

## Estrutura do Projeto

Essa parte do texto se refere à estrutura do projeto do sistema de banco escolar, especificando as principais classes envolvidas e o funcionamento geral do sistema. Vou explicar com mais detalhes:

1. **JanelaAluno, JanelaProfessor e JanelaDisciplina:** Essas são as classes responsáveis por criar as janelas de atualização de aluno, professor e disciplina, respectivamente. Cada uma dessas classes é responsável por exibir os campos necessários para inserção ou modificação das informações relacionadas a cada entidade específica. Por exemplo, a JanelaAluno terá campos para nome, idade, etc.

2. **TesteSwing2:** Essa é a classe principal do projeto, responsável por criar a janela principal do sistema. Ela também configura a barra de menus e adiciona os itens de menu para as atualizações de aluno, professor e disciplina. Essa classe controla a interação do usuário com o sistema e chama as respectivas janelas de atualização quando as opções do menu são selecionadas.

3. **Conexao:** Essa classe é responsável por estabelecer a conexão com o banco de dados utilizando a tecnologia JDBC (Java Database Connectivity). O JDBC permite a comunicação entre o programa Java e o banco de dados MySQL. A classe Conexao define os parâmetros necessários para a conexão, como o URL de conexão, usuário e senha.

### Funcionamento do Sistema

O funcionamento do sistema é o seguinte:

- Quando o sistema é executado, a classe TesteSwing2 é chamada e cria a janela principal do sistema, que contém uma barra de menus na parte superior.

- O usuário pode selecionar uma das opções do menu: "Aluno", "Professor", "Disciplina" ou "Sair".

- Ao selecionar "Aluno", por exemplo, um submenu é exibido com a opção "Atualizar Aluno". Ao selecionar essa opção, a JanelaAluno é exibida, permitindo ao usuário inserir ou modificar informações relacionadas a alunos.

- O mesmo ocorre para as opções "Professor" e "Disciplina", que exibem as respectivas janelas (JanelaProfessor e JanelaDisciplina) para atualização das informações relacionadas a essas entidades.

- Se o usuário selecionar "Sair", o programa é encerrado.

A classe Conexao é responsável por estabelecer a conexão com o banco de dados MySQL. Ela utiliza o driver JDBC para se comunicar com o banco de dados. No código fornecido, a conexão é estabelecida utilizando o usuário "root" e uma senha vazia. A URL de conexão é definida como "jdbc:mysql://localhost/escola", onde "localhost" representa o servidor local e "escola" é o nome do banco de dados.

### Resumo
Em resumo, a estrutura do projeto consiste em classes responsáveis por criar janelas de atualização para cada entidade (aluno, professor, disciplina), uma classe principal para controlar a interface do usuário e a lógica do menu, e uma classe para estabelecer a conexão com o banco de dados. Essas classes trabalham em conjunto para permitir a interação do usuário e a atualização das informações no banco de dados.
