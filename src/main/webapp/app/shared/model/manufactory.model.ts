export interface IManufactory {
  id?: number;
}

export class Manufactory implements IManufactory {
  constructor(public id?: number) {}
}
