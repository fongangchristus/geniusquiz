export interface INiveau {
  id?: number;
  libele?: string;
  description?: string;
  imageCouverture?: string;
  code?: string;
  curcusId?: number;
}

export class Niveau implements INiveau {
  constructor(
    public id?: number,
    public libele?: string,
    public description?: string,
    public imageCouverture?: string,
    public code?: string,
    public curcusId?: number
  ) {}
}
