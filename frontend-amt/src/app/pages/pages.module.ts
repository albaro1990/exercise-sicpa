import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { SharedModule } from '../shared/shared.module';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DepartmentFormComponent } from './departments/department-form.component';
import { DepartmentComponent } from './departments/department.component';
import { EmployeeFormComponent } from './employees/employee-form.component';
import { EmployeeComponent } from './employees/employee.component';
import { EnterpriseFormComponent } from './enterprise/enterprise-form.component';
import { EnterpriseComponent } from './enterprise/enterprise.component';
import { PagesComponent } from './pages.component';
import { ProfileComponent } from './profile/profile.component';


@NgModule({
  declarations: [
    DashboardComponent,
    PagesComponent,
    ProfileComponent,
    EnterpriseComponent,
    EnterpriseFormComponent,
    DepartmentComponent,
    DepartmentFormComponent,
    EmployeeComponent,
    EmployeeFormComponent
  ],
  exports: [DashboardComponent, PagesComponent],
  imports: [
    CommonModule,
    SharedModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
  ],
})
export class PagesModule {}
