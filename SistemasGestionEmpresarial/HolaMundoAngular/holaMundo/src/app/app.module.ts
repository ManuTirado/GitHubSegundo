import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TablaPersonasComponent } from './components/tabla-personas/tabla-personas.component';
import { FormularioPersonaComponent } from './components/formulario-persona/formulario-persona.component';
import { ListadoPersonasComponent } from './components/listado-personas/listado-personas.component';
import { ReactiveFormsModule } from '@angular/forms';
import { FormularioPersonaReactivoComponent } from './components/formulario-persona-reactivo/formulario-persona-reactivo.component';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [
    AppComponent,
    TablaPersonasComponent,
    FormularioPersonaComponent,
    ListadoPersonasComponent,
    FormularioPersonaReactivoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    MatProgressSpinnerModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
