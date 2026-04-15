-- 1. Limpeza
DELETE FROM nota;
DELETE FROM aluno;
DELETE FROM avaliacao;
DELETE FROM turma;
DELETE FROM disciplina;

-- 2. Turmas
INSERT INTO turma (id, nome) VALUES (1, '9º Ano A');
INSERT INTO turma (id, nome) VALUES (2, '1º Ano Médio');

-- 3. Disciplinas
INSERT INTO disciplina (id, nome) VALUES (1, 'Matemática');
INSERT INTO disciplina (id, nome) VALUES (2, 'Português');

-- 4. Avaliações para Matemática (Soma 10, nenhum valor acima de 5)
INSERT INTO avaliacao (nome, peso, disciplina_id) VALUES ('Prova Mensal MAT', 5, 1);
INSERT INTO avaliacao (nome, peso, disciplina_id) VALUES ('Trabalho Prático MAT', 3, 1);
INSERT INTO avaliacao (nome, peso, disciplina_id) VALUES ('Atividades MAT', 2, 1);

-- 5. Avaliações para Português (Ajustado para 5 e 5 para evitar violação de check)
INSERT INTO avaliacao (nome, peso, disciplina_id) VALUES ('Prova Mensal POR', 5, 2);
INSERT INTO avaliacao (nome, peso, disciplina_id) VALUES ('Redação', 5, 2);

-- 6. Alunos
INSERT INTO aluno (nome, turma_id) VALUES ('Anderson Silva', 1);
INSERT INTO aluno (nome, turma_id) VALUES ('Beatriz Souza', 1);
INSERT INTO aluno (nome, turma_id) VALUES ('Carlos Eduardo', 1);
INSERT INTO aluno (nome, turma_id) VALUES ('Anderson Silva', 2);
INSERT INTO aluno (nome, turma_id) VALUES ('Beatriz Souza', 2);
INSERT INTO aluno (nome, turma_id) VALUES ('Carlos Eduardo', 2);