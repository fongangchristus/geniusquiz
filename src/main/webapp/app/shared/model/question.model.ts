import { IQuiz } from 'app/shared/model/quiz.model';

export interface IQuestion {
  id?: number;
  libele?: string;
  idChapitre?: number;
  description?: string;
  imageCouverture?: string;
  point?: number;
  isActif?: boolean;
  chapitreId?: number;
  quizzes?: IQuiz[];
}

export class Question implements IQuestion {
  constructor(
    public id?: number,
    public libele?: string,
    public idChapitre?: number,
    public description?: string,
    public imageCouverture?: string,
    public point?: number,
    public isActif?: boolean,
    public chapitreId?: number,
    public quizzes?: IQuiz[]
  ) {
    this.isActif = this.isActif || false;
  }
}
