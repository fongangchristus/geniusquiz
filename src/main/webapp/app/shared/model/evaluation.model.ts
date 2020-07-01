import { Moment } from 'moment';

export interface IEvaluation {
  id?: number;
  lieu?: string;
  note?: number;
  idUser?: number;
  dateEvaluation?: Moment;
  quizId?: number;
}

export class Evaluation implements IEvaluation {
  constructor(
    public id?: number,
    public lieu?: string,
    public note?: number,
    public idUser?: number,
    public dateEvaluation?: Moment,
    public quizId?: number
  ) {}
}
