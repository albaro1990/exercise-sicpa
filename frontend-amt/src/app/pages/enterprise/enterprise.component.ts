import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { DatePipe } from '@angular/common';
import { Enterprise } from 'src/app/models/enterprise';
import { EnterpriseService } from 'src/app/services/enterprise.service';

@Component({
  selector: 'app-enterprise',
  templateUrl: './enterprise.component.html',
  styleUrls: ['./enterprise.component.css'],
})
export class EnterpriseComponent implements OnInit {
  enterprises: Enterprise[] = [];
  enterprise: Enterprise = new Enterprise();
  error: string;
  datePipe: DatePipe = new DatePipe('en-US');

  constructor(
    private enterpriseService: EnterpriseService,
    public authService: AuthService
  ) {}

  ngOnInit(): void {
    this.loadEnterprises();
  }


  public loadEnterprises(): void {
    this.enterpriseService.findAll().subscribe((enterprise) => {
      this.enterprises = enterprise;
    });
  } 
  
}
