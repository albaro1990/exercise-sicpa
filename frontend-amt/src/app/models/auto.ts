import { Modelo } from './modelo';

export class Auto {
  autoId: number;
  chasis: string;
  color: string;
  placa: string;
  observacion: string;
  estado: boolean;
  modeloDto: Modelo;
  fecha: Date;
  constructor() {}
}
