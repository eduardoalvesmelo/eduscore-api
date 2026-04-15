import { Routes } from '@angular/router';
import { LancamentoBoletimComponent } from './features/lancamento-boletim/lancamento-boletim.component';

export const routes: Routes = [
  {
    path: '',
    component: LancamentoBoletimComponent,
    title: 'EduScore - Gestão de Notas',
  },
  {
    path: '**',
    redirectTo: '',
  },
];
