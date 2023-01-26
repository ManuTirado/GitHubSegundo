import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TablaPersonasComponent } from './components/tabla-personas/tabla-personas.component';
import { FormularioPersonaComponent } from './components/formulario-persona/formulario-persona.component';
import { ListadoPersonasComponent } from './components/listado-personas/listado-personas.component';

@NgModule({
  declarations: [
    AppComponent,
    TablaPersonasComponent,
    FormularioPersonaComponent,
    ListadoPersonasComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
