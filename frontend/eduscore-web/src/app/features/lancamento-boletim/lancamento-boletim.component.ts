import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { ApiService } from '../../core/services/api.service';
import * as Models from '../../core/models/boletim.model';

@Component({
  selector: 'app-lancamento-boletim',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatTableModule,
    MatFormFieldModule,
    MatSelectModule,
    MatButtonModule,
    MatSnackBarModule,
  ],
  templateUrl: './lancamento-boletim.component.html',
  styleUrl: './lancamento-boletim.component.css',
})
export class LancamentoBoletimComponent implements OnInit {
  turmas: Models.Turma[] = [];
  disciplinas: Models.Disciplina[] = [];
  alunos: Models.Aluno[] = [];
  avaliacoes: Models.Avaliacao[] = [];

  displayedColumns: string[] = [];
  notasMap: { [key: string]: number } = {};

  turmaSelecionada?: number;
  disciplinaSelecionada?: number;

  constructor(
    private apiService: ApiService,
    private snack: MatSnackBar,
  ) {}

  ngOnInit() {
    this.apiService.getTurmas().subscribe((data) => (this.turmas = data));
    this.apiService.getDisciplinas().subscribe((data) => (this.disciplinas = data));
  }

  onFiltroChange(turmaId: number, discId: number) {
    this.turmaSelecionada = turmaId;
    this.disciplinaSelecionada = discId;

    if (turmaId && discId) {
      // Força o reset da tabela para evitar erros de índice
      this.alunos = [];
      this.displayedColumns = [];

      // Sequenciamento de requisições: Primeiro colunas, depois dados
      this.apiService.getAvaliacoes(discId).subscribe((avs) => {
        this.avaliacoes = avs;

        this.apiService.getAlunos(turmaId).subscribe((alunosData) => {
          this.alunos = [...alunosData];

          // CRITICAL: A ordem das strings aqui define a ordem da tabela
          // 'av' + id garante um identificador seguro para o matColumnDef
          this.displayedColumns = ['nome', ...this.avaliacoes.map((a) => 'av' + a.id), 'media'];
        });
      });
    }
  }

  getNotaKey(alunoId: number, avId: number): string {
    return `${alunoId}-${avId}`;
  }

  calcularMedia(alunoId: number): string {
    const notas = this.avaliacoes
      .map((av) => this.notasMap[this.getNotaKey(alunoId, av.id)])
      .filter((n) => n !== undefined && n !== null);

    if (notas.length === 0) return '-';
    const media = notas.reduce((a, b) => a + b, 0) / this.avaliacoes.length;
    return media.toLocaleString('pt-BR', { minimumFractionDigits: 1, maximumFractionDigits: 1 });
  }

  parseMedia(valor: string): number {
    return parseFloat(valor.replace(',', '.')) || 0;
  }

  salvarTudo() {
    const lancamentos = Object.keys(this.notasMap).map((key) => {
      const [alunoId, avaliacaoId] = key.split('-').map(Number);
      return { alunoId, avaliacaoId, valor: this.notasMap[key] };
    });

    if (lancamentos.length === 0) {
      this.snack.open('Preencha ao menos uma nota antes de salvar.', 'Aviso', { duration: 3000 });
      return;
    }

    this.apiService.salvarNotas({ lancamentos }).subscribe({
      next: () => this.snack.open('Notas persistidas com sucesso!', 'OK', { duration: 3000 }),
      error: () => this.snack.open('Erro na comunicação com o servidor.', 'Fechar'),
    });
  }
}
