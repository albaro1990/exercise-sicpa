import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DepartmentsEmployees } from 'src/app/models/departmentEmployee';
import { Departments } from 'src/app/models/departments';
import { Employee } from 'src/app/models/employee';
import { Enterprise } from 'src/app/models/enterprise';
import { DepartmentService } from 'src/app/services/department.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-employee-form',
  templateUrl: './employee-form.component.html',
  styleUrls: ['./employee-form.component.css'],
})
export class EmployeeFormComponent implements OnInit {
  listEnterprises: Enterprise[] = [];
  enterprise: Enterprise;
  
  department: Departments = new Departments();
  listDepartments: Departments[] = [];
  departmentsEmployees: DepartmentsEmployees;

  employee: Employee;
  errores: string[] = [];
  error: string;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
  
    private departmentService: DepartmentService
  ) {}

  ngOnInit(): void {
    this.loadDepartments();
    this.activatedRoute.paramMap.subscribe((params) => {
      let id = +params.get('id');
      if(id!=null && id >0){
        this.departmentService.findById(id).subscribe((departments) => {
          this.department = departments;
        });
      }
    });
  }

  public loadDepartments(): void {
    this.departmentService.findAll().subscribe((departments) => {
      this.listDepartments = departments;
    });
  }

  public create(): void {
    this.limpiar();
    if (this.employee != null) {
      this.department.enterprises = this.enterprise;
      console.log(this.department);
      this.departmentService.save(this.department).subscribe(
        (department) => {
          Swal.fire(
            'New:',
            `Department ${this.department.name} save success`,
            'success'
          );

          this.router.navigate(['/dashboard/department']);
        },
        (err) => {
          if (err.status === 400) {
            this.errores = err.error.errors as string[];
            console.error(err.status);
            console.error(err.error.errors);
          } else if (err.status === 500) {
            this.error = err.error.error;
            console.log(this.error);
          }
        }
      );
    }
  }

  private limpiar(): void {
    this.error = null;
    this.errores = [];
  }
}
