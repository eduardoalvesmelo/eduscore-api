import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as Models from '../models/boletim.model';

@Injectable({ providedIn: 'root' })
export class ApiService {
  // O prefixo /api será interceptado pelo proxy.conf.json
  private readonly URL_BASE = '/api';

  constructor(private http: HttpClient) {}

  getTurmas(): Observable<Models.Turma[]> {
    return this.http.get<Models.Turma[]>(`${this.URL_BASE}/turmas`);
  }

  getDisciplinas(): Observable<Models.Disciplina[]> {
    return this.http.get<Models.Disciplina[]>(`${this.URL_BASE}/disciplinas`);
  }

  getAlunos(turmaId: number): Observable<Models.Aluno[]> {
    return this.http.get<Models.Aluno[]>(`${this.URL_BASE}/alunos?turmaId=${turmaId}`);
  }

  getAvaliacoes(disciplinaId: number): Observable<Models.Avaliacao[]> {
    return this.http.get<Models.Avaliacao[]>(`${this.URL_BASE}/avaliacoes?disciplinaId=${disciplinaId}`);
  }

  salvarNotas(dto: Models.BoletimLoteDTO): Observable<void> {
    return this.http.post<void>(`${this.URL_BASE}/notas/lote`, dto);
  }
}
