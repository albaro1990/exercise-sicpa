import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Departments } from '../models/departments';
import { GenericService } from './generic.service';

@Injectable({
  providedIn: 'root',
})
export class DepartmentService extends GenericService<Departments, number> {
  constructor(protected http: HttpClient, protected router: Router) {
    super(http, router, `${environment.api.baseUrl}/employee`);
  }

  
}
