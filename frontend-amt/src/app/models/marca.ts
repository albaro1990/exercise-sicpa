import { Tipo } from './tipo';

export class Marca {
  marcaId: number;
  nombre: string;
  estado: boolean;
  tipoDto: Tipo;
  modeloDtos: [] = [];
  constructor() {}
}
