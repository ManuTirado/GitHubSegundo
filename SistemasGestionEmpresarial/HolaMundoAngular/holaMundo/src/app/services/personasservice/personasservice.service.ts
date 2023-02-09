import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { persona } from 'src/app/Interfaces/persona';

@Injectable({
  providedIn: 'root'
})

export class PersonasserviceService {
  /*URL de mi aPI para usar en todo el Servicio*/
  urlWebAPI = 'https://apipersonaspaco.azurewebsites.net/api/personas';

  constructor(private http: HttpClient) { }

  listadoPersonas(): Observable<persona[]> {
    return this.http.get<persona[]>(this.urlWebAPI);
  }

  eliminarPersona(id:number): void {
     this.http.delete<number>(this.urlWebAPI+"/"+id);
  }
}
