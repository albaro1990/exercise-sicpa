import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Modelo } from '../models/modelo';
import { GenericService } from './generic.service';

@Injectable({
  providedIn: 'root',
})
export class ModeloService extends GenericService<Modelo, number> {
  constructor(protected http: HttpClient, protected router: Router) {
    super(http, router, `${environment.api.baseUrl}/modelos`);
  }

  findModeloByMarca(marca: number): Observable<Modelo[]> {
    return this.http.get<Modelo[]>(`${this.base}/${marca}`).pipe(
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
