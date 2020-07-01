export interface ICursus {
  id?: number;
  libele?: string;
  description?: string;
  imageCouverture?: string;
  code?: string;
}

export class Cursus implements ICursus {
  constructor(
    public id?: number,
    public libele?: string,
    public description?: string,
    public imageCouverture?: string,
    public code?: string
  ) {}
}
