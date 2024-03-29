import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-formulario-persona-reactivo',
  templateUrl: './formulario-persona-reactivo.component.html',
  styleUrls: ['./formulario-persona-reactivo.component.css']
})
export class FormularioPersonaReactivoComponent {
  formularioPersReact: FormGroup;

  constructor() { }

  ngOnInit(): void {
    this.formularioPersReact = new FormGroup(
      {
        nombre: new FormControl('', [Validators.required]),
        apellidos: new FormControl('', [Validators.required])
      });
  }

  saluda() {
    alert('Hola ' + this.formularioPersReact.controls.nombre.value + ' ' + this.formularioPersReact.controls.apellidos.value);
  }
}
