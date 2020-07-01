import { Moment } from 'moment';
import { IEventuelReponse } from 'app/shared/model/eventuel-reponse.model';

export interface IReponseUser {
  id?: number;
  dateReponse?: Moment;
  libele?: string;
  quizQuestionId?: number;
  evaluationId?: number;
  eventuelReponses?: IEventuelReponse[];
}

export class ReponseUser implements IReponseUser {
  constructor(
    public id?: number,
    public dateReponse?: Moment,
    public libele?: string,
    public quizQuestionId?: number,
    public evaluationId?: number,
    public eventuelReponses?: IEventuelReponse[]
  ) {}
}
