import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LancamentoBoletim } from './lancamento-boletim.component';

describe('LancamentoBoletim', () => {
  let component: LancamentoBoletim;
  let fixture: ComponentFixture<LancamentoBoletim>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LancamentoBoletim],
    }).compileComponents();

    fixture = TestBed.createComponent(LancamentoBoletim);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
