-- Limpeza prévia (Opcional, útil para evitar duplicidade no H2 mem)
DELETE FROM nota;
DELETE FROM aluno;
DELETE FROM avaliacao;
DELETE FROM turma;
DELETE FROM disciplina;

-- Inserção de Turmas e Disciplinas
INSERT INTO turma (nome) VALUES ('9º Ano A'), ('1º Ano Médio');
INSERT INTO disciplina (nome) VALUES ('Matemática'), ('Português');

-- Avaliações para Matemática (Supondo ID 1 da disciplina)
INSERT INTO avaliacao (nome, peso, disciplina_id) VALUES ('Prova Mensal', 5, 1);
INSERT INTO avaliacao (nome, peso, disciplina_id) VALUES ('Trabalho Prático', 2, 1);
INSERT INTO avaliacao (nome, peso, disciplina_id) VALUES ('Atividades', 1, 1);

-- Alunos para a Turma 1
INSERT INTO aluno (nome, turma_id) VALUES ('Anderson Silva', 1);
INSERT INTO aluno (nome, turma_id) VALUES ('Beatriz Souza', 1);
INSERT INTO aluno (nome, turma_id) VALUES ('Carlos Eduardo', 1);