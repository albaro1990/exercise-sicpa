import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import Swal from 'sweetalert2';
import { DatePipe } from '@angular/common';
import { Departments } from 'src/app/models/departments';
import { DepartmentService } from 'src/app/services/department.service';
import { DepartmentsEmployees } from 'src/app/models/departmentEmployee';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css'],
})
export class EmployeeComponent implements OnInit {
  departments: Departments[] = [];
  department: Departments = new Departments();
  departmentsEmployees: DepartmentsEmployees[] = [];
  error: string;
  datePipe: DatePipe = new DatePipe('en-US');

  constructor(
    private departmentService: DepartmentService,
    public authService: AuthService
  ) {}

  ngOnInit(): void {
    this.loadDepartments();
  }


  public loadDepartments(): void {
    this.departmentService.findAll().subscribe((department) => {
      this.departments = department;
    });
  } 


}
