import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Enterprise } from 'src/app/models/enterprise';
import { EnterpriseService } from 'src/app/services/enterprise.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-enterprise-form',
  templateUrl: './enterprise-form.component.html',
  styleUrls: ['./enterprise-form.component.css'],
})
export class EnterpriseFormComponent implements OnInit {
  
  enterprises: Array<Enterprise> = [];
  enterprise: Enterprise = new Enterprise();
  errores: string[] = [];
  error: string;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private enterpriseService: EnterpriseService
  ) {}

  ngOnInit(): void {

    this.activatedRoute.paramMap.subscribe((params) => {
      let id = +params.get('id');
      if(id!=null && id >0){
      this.enterpriseService.findById(id).subscribe((enterprise) => {
        this.enterprise = enterprise;
      });
     }
    });
    
  }


  public create(): void {
    this.clear();
   
      this.enterprise = this.enterprise;
      console.log(this.enterprise);
      if(this.enterprise!=null){
      this.enterpriseService.save(this.enterprise).subscribe(
        (enterprise) => {
          Swal.fire(
            'New:',
            `Enterprise ${this.enterprise.name} created success`,
            'success'
          );

          this.router.navigate(['/dashboard/enterprise']);
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

  private clear(): void {
    this.error = null;
    this.errores = [];
  }
}
