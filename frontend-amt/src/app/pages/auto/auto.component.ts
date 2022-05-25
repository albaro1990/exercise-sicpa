import { Component, OnInit } from '@angular/core';
import { Auto } from 'src/app/models/auto';
import { AuthService } from 'src/app/services/auth.service';
import { AutoService } from 'src/app/services/auto.service';
import Swal from 'sweetalert2';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-auto',
  templateUrl: './auto.component.html',
  styleUrls: ['./auto.component.css'],
})
export class AutoComponent implements OnInit {
  autos: Auto[] = [];
  auto: Auto = new Auto();
  error: string;
  datePipe: DatePipe = new DatePipe('en-US');

  constructor(
    private autoService: AutoService,
    public authService: AuthService
  ) {}

  ngOnInit(): void {}

  public find(): void {
    this.limpiar();
    if (this.auto != null && this.auto.placa != null) {
      this.autoService.findAutoByPlaca(this.auto.placa).subscribe(
        (auto) => {
          this.autos.push(auto);
          //this.autos = this.autos.filter((aut) => aut === auto);
        },
        (err) => {
          if (err.status === 404 || err.status === 500) {
            this.error = err.error.error;
            console.log(this.error);
          }
        }
      );
    } else {
    }
  }

  public delete(auto: Auto): void {
    Swal.fire({
      title: 'Cuidado:',
      text: `¿Seguro que desea eliminar el auto ${auto.placa} ?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, eliminar!',
    }).then((result) => {
      if (result.value) {
        this.autoService.delete(auto.autoId).subscribe((response) => {
          this.autos = this.autos.filter((aut) => aut !== auto);
          Swal.fire(
            'Eliminado:',
            `Auto ${auto.placa} eliminado con éxito.`,
            'success'
          );
        });
      }
    });
  }

  public validar(): void {
    this.limpiar();
    if (this.auto != null && this.auto.placa != null) {
      this.autoService
        .findHoyNoCircula(
          this.auto.placa,
          this.datePipe.transform(this.auto.fecha, 'dd-MM-yyyy')
        )
        .subscribe(
          (auto) => {
            this.find();
            Swal.fire(
              'Info',
              `El auto con placa  ${
                this.auto.placa
              } puede circular en la fecha ${this.datePipe.transform(
                this.auto.fecha,
                'dd-MM-yyyy'
              )}`,
              'info'
            );
          },
          (err) => {
            if (err.status === 404 || err.status === 500) {
              this.error = err.error.error;
              console.log(this.error);
              Swal.fire('Error', `${this.error}`, 'error');
            }
          }
        );
    }
  }

  private limpiar(): void {
    this.autos = [];
    this.error = null;
  }
}
