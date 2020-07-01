export interface IChapitre {
  id?: number;
  libele?: string;
  description?: string;
  imageCouverture?: string;
  fichierCours?: string;
  matiereId?: number;
}

export class Chapitre implements IChapitre {
  constructor(
    public id?: number,
    public libele?: string,
    public description?: string,
    public imageCouverture?: string,
    public fichierCours?: string,
    public matiereId?: number
  ) {}
}
