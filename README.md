---

# EduScore - Sistema de Gestão de Notas

O **EduScore** é uma plataforma robusta desenvolvida para facilitar o lançamento de notas escolares em lote. O sistema permite que professores selecionem turmas e disciplinas para gerenciar avaliações de forma dinâmica e intuitiva.

## Tecnologias

### Frontend
- **Angular 18+** (Standalone Components)
- **Angular Material** (UI/UX)
- **Reactive/Template-driven Forms**
- **RxJS** para gerenciamento de chamadas assíncronas
- **Tailwind CSS** ou Custom CSS para estilização avançada

### Backend
- **Java 17 / Spring Boot 3**
- **Spring Data JPA**
- **H2 Database** (Banco de dados em memória para desenvolvimento)
- **Maven** para gerenciamento de dependências

---

## Arquitetura do Banco de Dados

O sistema utiliza um banco de dados relacional com as seguintes entidades principais:

- **Turma**: Agrupamento de alunos.
- **Disciplina**: Matérias lecionadas (Matemática, Português, etc).
- **Aluno**: Vinculado a uma turma.
- **Avaliacao**: Provas ou trabalhos vinculados a uma disciplina específica, contendo pesos para o cálculo da média.
- **Nota**: A relação final entre Aluno, Avaliação e o valor atingido.

### Modelo de Dados (Seed Inicial)
O arquivo `src/main/resources/import.sql` popula o ambiente com:
- Turmas: `9º Ano A` e `1º Ano Médio`.
- Disciplinas com avaliações configuradas por peso.
- Alunos distribuídos entre as turmas para testes de carga em lote.

---

## Funcionalidades do Frontend

### Grade de Lançamento Dinâmica
A principal inovação do frontend é a **Grade Dinâmica**. Diferente de tabelas estáticas, as colunas de avaliações são geradas em tempo real:
1. O usuário seleciona a **Turma** e a **Disciplina**.
2. O sistema busca as avaliações cadastradas para aquela disciplina no banco.
3. A tabela se reconstrói automaticamente injetando os inputs de notas entre a coluna "Nome" e "Média Final".

### Cálculo de Média Automático
O sistema realiza o cálculo de média em tempo real no cliente (Client-side) conforme as notas são digitadas, fornecendo feedback visual imediato (notas abaixo de 6.0 ficam em destaque vermelho).

### Persistência em Lote
Ao clicar em "Salvar Lançamentos em Lote", o frontend mapeia o dicionário de notas (`notasMap`) para um DTO de transporte, enviando todas as atualizações em uma única requisição POST, otimizando o tráfego de rede e a performance do banco.

---

## Garantia de Qualidade (Testes)

O projeto implementa uma pirâmide de testes para garantir a confiabilidade das operações críticas.

### Backend (JUnit 5 & Mockito)
Focado na integridade dos dados e segurança das regras de negócio.
- **Testes de Serviço (`BoletimServiceTest`)**: Valida o processamento do lote de notas e a existência de dependências (Aluno/Avaliação) antes da persistência.
- **Testes de Controller (`NotaControllerTest`)**: Garante que o endpoint de lote responda corretamente ao contrato JSON estabelecido.
- **Execução**: No diretório do backend, execute `mvn test`.

### Frontend (Jasmine & Karma)
Focado na precisão dos cálculos e integração com a API.
- **Testes de Componente (`lancamento-boletim.component.spec.ts`)**: Valida matematicamente a regra da **Média Ponderada** e o comportamento da interface.
- **Testes de Serviço (`api.spec.ts`)**: Verifica se as chamadas assíncronas estão apontando para os endpoints corretos do servidor.
- **Execução**: No diretório do frontend, execute `ng test`.

---

## Configuração do Ambiente

### Requisitos
- Node.js 18+
- JDK 17+
- Angular CLI

### Executando o Backend
1. Navegue até a pasta `backend`.
2. Execute o comando: `./mvnw spring-boot:run`
3. O H2 Console estará disponível em `http://localhost:8080/h2-console` (User: `sa`, Pass: ` `).

### Executando o Frontend
1. Navegue até a pasta `frontend`.
2. Instale as dependências: `npm install`
3. Inicie o servidor: `ng serve`
4. Acesse: `http://localhost:4200`

---

## Endpoints Principais (API)

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| GET | `/api/turmas` | Lista todas as turmas |
| GET | `/api/disciplinas` | Lista todas as disciplinas |
| GET | `/api/alunos?turmaId={id}` | Busca alunos por turma |
| GET | `/api/avaliacoes?disciplinaId={id}` | Busca provas por disciplina |
| POST | `/api/notas/lote` | Salva uma lista de notas processadas |

---