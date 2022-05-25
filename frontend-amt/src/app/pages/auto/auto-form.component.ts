import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Auto } from 'src/app/models/auto';
import { Marca } from 'src/app/models/marca';
import { Modelo } from 'src/app/models/modelo';
import { Tipo } from 'src/app/models/tipo';
import { AutoService } from 'src/app/services/auto.service';
import { MarcaService } from 'src/app/services/marca.service';
import { ModeloService } from 'src/app/services/modelo.service';
import { TipoService } from 'src/app/services/tipo.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-auto-form',
  templateUrl: './auto-form.component.html',
  styleUrls: ['./auto-form.component.css'],
})
export class AutoFormComponent implements OnInit {
  tipos: Tipo[] = [];
  tipo: Tipo;
  marcas: Marca[] = [];
  marca: Marca;
  modelos: Modelo[] = [];
  modelo: Modelo;
  auto: Auto = new Auto();
  errores: string[] = [];
  error: string;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private tipoService: TipoService,
    private marcaService: MarcaService,
    private modeloService: ModeloService,
    private autoService: AutoService
  ) {}

  ngOnInit(): void {
    this.cargarTipos();
  }

  public cargarTipos(): void {
    this.tipoService.findAll().subscribe((tipos) => {
      this.tipos = tipos;
    });
  }

  public cargarMarcas(): void {
    if (this.tipo != null) {
      this.marca = new Marca();
      this.marca.tipoDto = this.tipo;
      this.marcaService.findMarcaByTipo(this.tipo.tipoId).subscribe((marcas) => {
        this.marcas = marcas;
      });
    }
  }

  public cargarModelos(): void {
    if (this.marca != null) {
      this.modelo = new Modelo();
      this.modelo.marcaDto = this.marca;
      this.modeloService.findModeloByMarca(this.marca.marcaId).subscribe((modelos) => {
        this.modelos = modelos;
      });
    }
  }

  public create(): void {
    this.limpiar();
    if (this.modelo != null) {
      this.auto.modeloDto = this.modelo;
      console.log(this.auto);
      this.autoService.save(this.auto).subscribe(
        (auto) => {
          Swal.fire(
            'Nuevo:',
            `Auto ${this.auto.placa} creado con éxito`,
            'success'
          );

          this.router.navigate(['/dashboard/auto']);
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
