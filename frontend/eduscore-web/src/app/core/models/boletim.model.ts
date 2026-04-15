export interface Turma { id: number; nome: string; }
export interface Disciplina { id: number; nome: string; }
export interface Aluno { id: number; nome: string; }
export interface Avaliacao { id: number; nome: string; peso: number; }

export interface LancamentoNota {
  alunoId: number;
  avaliacaoId: number;
  valor: number;
}

export interface BoletimLoteDTO {
  lancamentos: LancamentoNota[];
}
