import { IMatiere } from 'app/shared/model/matiere.model';

export interface IClasse {
  id?: number;
  libele?: string;
  description?: string;
  imageCouverture?: string;
  code?: string;
  niveauId?: number;
  optionId?: number;
  categorieFormationId?: number;
  matieres?: IMatiere[];
}

export class Classe implements IClasse {
  constructor(
    public id?: number,
    public libele?: string,
    public description?: string,
    public imageCouverture?: string,
    public code?: string,
    public niveauId?: number,
    public optionId?: number,
    public categorieFormationId?: number,
    public matieres?: IMatiere[]
  ) {}
}
