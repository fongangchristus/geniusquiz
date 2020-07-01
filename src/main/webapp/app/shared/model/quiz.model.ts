import { Moment } from 'moment';
import { IQuestion } from 'app/shared/model/question.model';

export interface IQuiz {
  id?: number;
  type?: string;
  idMatiere?: number;
  entete?: string;
  description?: string;
  libele?: string;
  imageCouverture?: string;
  duree?: Moment;
  nbrQuestion?: number;
  datePublication?: Moment;
  dateExpiration?: Moment;
  matiereId?: number;
  questions?: IQuestion[];
}

export class Quiz implements IQuiz {
  constructor(
    public id?: number,
    public type?: string,
    public idMatiere?: number,
    public entete?: string,
    public description?: string,
    public libele?: string,
    public imageCouverture?: string,
    public duree?: Moment,
    public nbrQuestion?: number,
    public datePublication?: Moment,
    public dateExpiration?: Moment,
    public matiereId?: number,
    public questions?: IQuestion[]
  ) {}
}
