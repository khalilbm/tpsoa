import { IOwner } from 'app/shared/model/owner.model';

export interface ICar {
  id?: number;
  matricule?: string;
  marque?: string;
  owner?: IOwner;
}

export class Car implements ICar {
  constructor(public id?: number, public matricule?: string, public marque?: string, public owner?: IOwner) {}
}
