import { IReponseUser } from 'app/shared/model/reponse-user.model';

export interface IEventuelReponse {
  id?: number;
  libele?: string;
  code?: string;
  imageCouverture?: string;
  description?: string;
  correctAnswer?: boolean;
  point?: number;
  quizId?: number;
  reponseUsers?: IReponseUser[];
}

export class EventuelReponse implements IEventuelReponse {
  constructor(
    public id?: number,
    public libele?: string,
    public code?: string,
    public imageCouverture?: string,
    public description?: string,
    public correctAnswer?: boolean,
    public point?: number,
    public quizId?: number,
    public reponseUsers?: IReponseUser[]
  ) {
    this.correctAnswer = this.correctAnswer || false;
  }
}
