import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { Tipo } from '../models/tipo';
import { GenericService } from './generic.service';

@Injectable({
  providedIn: 'root',
})
export class TipoService extends GenericService<Tipo, number> {
  constructor(protected http: HttpClient, protected router: Router) {
    super(http, router, `${environment.api.baseUrl}/tipos`);
  }
}
