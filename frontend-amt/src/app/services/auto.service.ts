import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Auto } from '../models/auto';
import { environment } from 'src/environments/environment';
import { GenericService } from './generic.service';

@Injectable({
  providedIn: 'root',
})
export class AutoService extends GenericService<Auto, number> {
  constructor(protected http: HttpClient, protected router: Router) {
    super(http, router, `${environment.api.baseUrl}/autos`);
  }

  findAutoByPlaca(placa: string): Observable<Auto> {
    return this.http.get<Auto>(`${this.base}/${placa}`).pipe(
      catchError((e) => {
        if (e.status === 404) {
          return throwError(e);
        }
        return throwError(e);
      })
    );
  }

  findHoyNoCircula(placa: string, fecha: string): Observable<Auto> {
    return this.http
      .get<Auto>(`${this.base}/hoynocircula/${placa}/fecha/${fecha}`)
      .pipe(
        catchError((e) => {
          if (e.status === 404 || e.status === 500) {
            return throwError(e);
          }
          return throwError(e);
        })
      );
  }
}
