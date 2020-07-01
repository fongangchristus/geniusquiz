import { IClasse } from 'app/shared/model/classe.model';

export interface IMatiere {
  id?: number;
  libele?: string;
  description?: string;
  validite?: string;
  imageCouverture?: string;
  idOrganisation?: number;
  classes?: IClasse[];
}

export class Matiere implements IMatiere {
  constructor(
    public id?: number,
    public libele?: string,
    public description?: string,
    public validite?: string,
    public imageCouverture?: string,
    public idOrganisation?: number,
    public classes?: IClasse[]
  ) {}
}
