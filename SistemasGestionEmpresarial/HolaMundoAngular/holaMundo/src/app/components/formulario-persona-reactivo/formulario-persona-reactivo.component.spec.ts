import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormularioPersonaReactivoComponent } from './formulario-persona-reactivo.component';

describe('FormularioPersonaReactivoComponent', () => {
  let component: FormularioPersonaReactivoComponent;
  let fixture: ComponentFixture<FormularioPersonaReactivoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormularioPersonaReactivoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormularioPersonaReactivoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
