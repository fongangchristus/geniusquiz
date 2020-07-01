export interface IOption {
  id?: number;
  libele?: string;
  description?: string;
  imageCouverture?: string;
  code?: string;
  quizId?: number;
}

export class Option implements IOption {
  constructor(
    public id?: number,
    public libele?: string,
    public description?: string,
    public imageCouverture?: string,
    public code?: string,
    public quizId?: number
  ) {}
}
