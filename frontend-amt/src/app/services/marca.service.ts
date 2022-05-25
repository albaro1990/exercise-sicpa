import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Marca } from '../models/marca';
import { GenericService } from './generic.service';

@Injectable({
  providedIn: 'root',
})
export class MarcaService extends GenericService<Marca, number> {
  constructor(protected http: HttpClient, protected router: Router) {
    super(http, router, `${environment.api.baseUrl}/marcas`);
  }

  findMarcaByTipo(tipo: number): Observable<Marca[]> {
    return this.http.get<Marca[]>(`${this.base}/${tipo}`).pipe(
      catchError((e) => {
        if (e.status !== 401 && e.error.mensaje) {
          this.router.navigate(['/auto']);
          console.error(e.error.mensaje);
        }
        return throwError(e);
      })
    );
  }
}
