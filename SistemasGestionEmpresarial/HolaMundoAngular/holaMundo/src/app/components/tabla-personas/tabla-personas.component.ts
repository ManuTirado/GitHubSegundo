import { Component, OnInit } from '@angular/core';
import { persona } from 'src/app/Interfaces/persona';
import { PersonasserviceService } from 'src/app/services/personasservice/personasservice.service';

@Component({
  selector: 'app-tabla-personas',
  templateUrl: './tabla-personas.component.html',
  styleUrls: ['./tabla-personas.component.css']
})

export class TablaPersonasComponent implements OnInit {
  listadoPersonas: persona[];
  cargando: boolean = true;

  constructor(private personaService: PersonasserviceService) { }

  ngOnInit(): void {
    this.personaService.listadoPersonas().subscribe(data => {
      setTimeout(() => {
        this.cargando = false;
        this.listadoPersonas = data;
      }, 500);
    }, error => {
      //TODO: Controlar el error
      alert("Ha ocurrido un error al ontener las personas")
      this.cargando = false;
    })
  }

  btnBorrarPersona(id) {
    this.personaService.eliminarPersona(id).subscribe()
  }
}
