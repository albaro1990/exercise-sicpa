
import { Enterprise } from './enterprise';

export class Departments {
  id: number;

  description: string;

  name: string;

  phone: string;
  enterprises: Enterprise;
  constructor() { }
}
