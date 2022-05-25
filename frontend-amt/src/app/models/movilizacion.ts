import { Auto } from './auto';
import { Restriccion } from './restriccion';

export class Movilizacion {
  movilizacionId: number;
  estado: boolean;
  fecha: Date;
  auto: Auto;
  descripcion: string;
  restricion: Restriccion;
  constructor() {}
}
