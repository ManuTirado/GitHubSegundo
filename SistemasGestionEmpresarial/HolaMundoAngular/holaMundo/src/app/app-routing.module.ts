import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormularioPersonaComponent } from './components/formulario-persona/formulario-persona.component';
import { FormularioPersonaReactivoComponent } from './components/formulario-persona-reactivo/formulario-persona-reactivo.component';
import { TablaPersonasComponent } from './components/tabla-personas/tabla-personas.component';
import { ListadoPersonasComponent } from './components/listado-personas/listado-personas.component';

const routes: Routes = [
    {path: 'tabla', component: TablaPersonasComponent},
    {path: 'formulario', component: FormularioPersonaComponent},
    {path: 'formularioReactivo', component: FormularioPersonaReactivoComponent},
    {path: 'listado', component: ListadoPersonasComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
