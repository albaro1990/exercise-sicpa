import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { Employee } from '../models/employee';
import { GenericService } from './generic.service';

@Injectable({
  providedIn: 'root',
})
export class EmployeeService extends GenericService<Employee, number> {
  constructor(protected http: HttpClient, protected router: Router) {
    super(http, router, `${environment.api.baseUrl}/employee`);
  }

  
}
