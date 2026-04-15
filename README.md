EduScore - Sistema de Lançamento de Notas
Sistema desenvolvido para facilitar o lançamento de notas escolares com cálculo automático de média ponderada. O projeto utiliza Spring Boot 3 no backend e Angular 16 no frontend.

Como Executar o Projeto
Pré-requisitos
Java 17 ou superior.

Node.js 18 ou superior.

Angular CLI instalado (npm install -g @angular/cli).

1. Backend (Spring Boot)
O backend utiliza um banco de dados H2 em memória, o que dispensa a instalação de um servidor de banco de dados externo.

Navegue até a pasta api/:

Bash
cd api
Execute a aplicação usando Maven:

Bash
./mvnw spring-boot:run
A API estará disponível em http://localhost:8080.

Console do Banco H2: Acesse http://localhost:8080/h2-console (JDBC URL: jdbc:h2:mem:eduscore_db).

Swagger UI (Documentação): Acesse http://localhost:8080/swagger-ui/index.html.

2. Frontend (Angular)
Navegue até a pasta web/:

Bash
cd ../eduscore-web
Instale as dependências:

Bash
npm install
Inicie o servidor de desenvolvimento:

Bash
ng serve
Acesse o sistema em http://localhost:4200.

🛠 Decisões Técnicas
Arquitetura do Backend
Organização em Camadas: Segui o padrão clássico Controller -> Service -> Repository -> Model. Isso garante que a regra de negócio (cálculo da média) fique isolada no Service, facilitando testes unitários e manutenção.

Database Seed: Utilizei o arquivo data.sql para garantir que a aplicação já inicie com dados de teste (turmas, alunos e disciplinas), conforme solicitado nos requisitos.

Validação de Dados: Implementei validações via Bean Validation (@Min, @Max) para garantir que notas estejam entre 0-10 e pesos entre 1-5.

Documentação: O uso do SpringDoc OpenAPI (Swagger) foi priorizado para permitir o teste independente dos endpoints.

Arquitetura do Frontend
Reactive Forms: Optei por formulários reativos para gerenciar a tabela de notas dinâmica. Isso permite validar os dados em tempo real e reagir a mudanças de input para atualizar a média ponderada sem chamadas ao servidor.

Componentização: A lógica da tabela foi desacoplada em componentes reutilizáveis, utilizando Angular Material para uma interface limpa e responsiva.

Services: Centralizei as chamadas HTTP em um ApiService utilizando o padrão Observable do RxJS para lidar com a asincronidade de forma eficiente.

Estratégia de Salvamento
Lançamento em Lote: O sistema permite editar múltiplas notas de uma vez e enviá-las em um único POST JSON, reduzindo o overhead de rede e melhorando a experiência do usuário (UX).

Idempotência: O salvamento no banco de dados utiliza uma lógica de "Upsert" (Update ou Insert) baseada na chave composta alunoId + avaliacaoId, garantindo que não haja duplicidade de registros.

Dados de Seed (Carga Inicial)
Ao iniciar o backend, os seguintes dados são carregados automaticamente:

Turmas: "9º Ano A" e "1º Ano Médio".

Disciplinas: "Matemática" e "Português".

Avaliações: Configuradas com pesos variáveis (ex: Prova peso 5, Trabalho peso 2).

Alunos: Vinculados às respectivas turmas para teste imediato.