export interface ICategorieFormation {
  id?: number;
  libele?: string;
  description?: string;
  imageCouverture?: string;
  code?: string;
}

export class CategorieFormation implements ICategorieFormation {
  constructor(
    public id?: number,
    public libele?: string,
    public description?: string,
    public imageCouverture?: string,
    public code?: string
  ) {}
}
