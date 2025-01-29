# Cinema Management System

---

<div align="center">
  <img src="https://github.com/user-attachments/assets/4fc8b9f0-de42-40a2-be48-ab1b05a497b7" alt="Descrição da imagem">
</div>

---

<div align="center">
  <img src="https://github.com/user-attachments/assets/a145e4c2-bfb3-4764-879a-bad9fb7ee8d9" alt="Descrição da imagem">
</div>

---

## Sobre o Projeto

O **Cinema Management System** é um projeto acadêmico desenvolvido para a disciplina de **Estrutura de Dados**. Ele visa implementar conceitos fundamentais da disciplina, como organização e manipulação de dados, utilizando a linguagem **Kotlin** com a biblioteca **JavaFX** para a criação de interfaces gráficas.

O sistema simula a gestão de um cinema, permitindo aos usuários realizarem operações como cadastro de filmes, gerenciamento de sessões, reservas de assentos e outras funcionalidades relacionadas ao setor.

---

## Funcionalidades

- **Cadastro de filmes**: Permite adicionar, editar e remover informações de filmes.
- **Gestão de sessões**: Criação e gerenciamento de horários para exibição dos filmes.
- **Reserva de assentos**: Sistema de seleção e reserva de assentos.
- **Visualização de informações**: Listagem de filmes e sessões disponíveis.
- **Persistência de dados**: Armazenamento das informações utilizando estruturas de dados apropriadas.

---

## Tecnologias Utilizadas

- **Linguagem de programação**: [Kotlin](https://kotlinlang.org/)
- **Framework para interface gráfica**: [JavaFX](https://openjfx.io/)
- **Ferramenta de build**: [Gradle](https://gradle.org/)

---

## Estrutura do Projeto

O projeto está organizado da seguinte forma:

```
CinemaManagementSystem/
├── src/
│   ├── main/
│   │   ├── kotlin/
│   │   │   ├── controllers/      # Controladores da interface gráfica
│   │   │   ├── models/           # Modelos de dados
│   │   │   ├── views/            # Interfaces em JavaFX
│   │   │   └── utils/            # Utilitários e funções auxiliares
│   │   └── resources/
│   │       └── fxml/             # Arquivos de layout em FXML
├── build.gradle                  # Configurações do Gradle
└── README.md                     # Documentação do projeto
```

---

## Como Executar

### Requisitos

- **JDK**: Versão 11 ou superior.
- **Gradle**: Configurado no ambiente.

### Passos

1. Clone o repositório:
   ```bash
   git clone https://github.com/JasonSX1/Estruturas-de-Dados.git
   ```

2. Navegue até a pasta do projeto:
   ```bash
   cd Estruturas-de-Dados/CinemaManagementSystem
   ```

3. Execute o projeto com o Gradle:
   ```bash
   ./gradlew run
   ```

4. O sistema estará disponível para uso através da interface gráfica.

---

## Contribuição

Contribuições são bem-vindas! Para contribuir:

1. Fork este repositório.
2. Crie uma branch para sua feature ou correção:
   ```bash
   git checkout -b minha-feature
   ```
3. Realize suas alterações e commit:
   ```bash
   git commit -m "Adiciona nova funcionalidade"
   ```
4. Envie para o seu fork:
   ```bash
   git push origin minha-feature
   ```
5. Abra um Pull Request no repositório original.

---

## Autores

- **JasonSX1** ([GitHub](https://github.com/JasonSX1))

---

## Licença

Este projeto é licenciado sob a [MIT License](https://opensource.org/licenses/MIT).

