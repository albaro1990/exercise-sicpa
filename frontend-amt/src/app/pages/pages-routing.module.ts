import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from '../guard/auth.guard';
import { RoleGuard } from '../guard/role.guard';
import { DashboardComponent } from './dashboard/dashboard.component';
import { PagesComponent } from './pages.component';
import { ProfileComponent } from './profile/profile.component';
import { EnterpriseComponent } from './enterprise/enterprise.component';
import { EnterpriseFormComponent } from './enterprise/enterprise-form.component';
import { DepartmentComponent } from './departments/department.component';
import { DepartmentFormComponent } from './departments/department-form.component';
import { EmployeeComponent } from './employees/employee.component';
import { EmployeeFormComponent } from './employees/employee-form.component';

const routes: Routes = [
  {
    path: 'dashboard',
    component: PagesComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: '',
        component: DashboardComponent,
        data: { title: 'Dashboard' },
      },
      {
        path: 'profile',
        component: ProfileComponent,
        data: { title: 'Profile' },
      },
      {
        path: 'enterprise',
        component: EnterpriseComponent,
        canActivate: [AuthGuard, RoleGuard],
        data: {
          role: ['ROLE_ADMIN', 'ROLE_USER'], title: 'List Enterprises',
        },
      },
      {
        path: 'enterprise/form',
        component: EnterpriseFormComponent,
        canActivate: [AuthGuard, RoleGuard],
        data: { role: ['ROLE_ADMIN'], title: 'Create Enterprise' },
      },
      {
        path: 'enterprise/form/:id',
        component: EnterpriseFormComponent,
        canActivate: [AuthGuard, RoleGuard],
        data: { role: ['ROLE_ADMIN'], title: 'Update Enterprise' },
      },
      {
        path: 'department',
        component: DepartmentComponent,
        canActivate: [AuthGuard, RoleGuard],
        data: {
          role: ['ROLE_ADMIN', 'ROLE_USER'], title: 'List Department',
        },
      },
      {
        path: 'department/form',
        component: DepartmentFormComponent,
        canActivate: [AuthGuard, RoleGuard],
        data: { role: ['ROLE_ADMIN'], title: 'Create Department' },
      },
      {
        path: 'department/form/:id',
        component: DepartmentFormComponent,
        canActivate: [AuthGuard, RoleGuard],
        data: { role: ['ROLE_ADMIN'], title: 'Update Department' },
      },
      {
        path: 'employee',
        component: EmployeeComponent,
        canActivate: [AuthGuard, RoleGuard],
        data: {
          role: ['ROLE_ADMIN', 'ROLE_USER'], title: 'List Employee',
        },
      },
      {
        path: 'employee/form',
        component: EmployeeFormComponent,
        canActivate: [AuthGuard, RoleGuard],
        data: { role: ['ROLE_ADMIN'], title: 'Create Employee' },
      },
      {
        path: 'employee/form/:id',
        component: EmployeeFormComponent,
        canActivate: [AuthGuard, RoleGuard],
        data: { role: ['ROLE_ADMIN'], title: 'Update Employee' },
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PagesRoutingModule {}
