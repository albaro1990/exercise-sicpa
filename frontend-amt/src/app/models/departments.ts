
import { Enterprise } from './enterprise';
import { Modelo } from './modelo';

export class Departments {
  id: number;

  description: string;

  name: string;

  phone: string;
  enterprises: Enterprise;
  constructor() { }
}
