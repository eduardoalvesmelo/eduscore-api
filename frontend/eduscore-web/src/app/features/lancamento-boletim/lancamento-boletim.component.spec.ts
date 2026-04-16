import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormsModule } from '@angular/forms';
import { LancamentoBoletimComponent } from './lancamento-boletim.component';

describe('LancamentoBoletimComponent', () => {
  let component: LancamentoBoletimComponent;
  let fixture: ComponentFixture<LancamentoBoletimComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ LancamentoBoletimComponent, HttpClientTestingModule, FormsModule ]
    }).compileComponents();

    fixture = TestBed.createComponent(LancamentoBoletimComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
