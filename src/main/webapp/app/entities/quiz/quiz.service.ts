import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IQuiz } from 'app/shared/model/quiz.model';

type EntityResponseType = HttpResponse<IQuiz>;
type EntityArrayResponseType = HttpResponse<IQuiz[]>;

@Injectable({ providedIn: 'root' })
export class QuizService {
  public resourceUrl = SERVER_API_URL + 'api/quizzes';

  constructor(protected http: HttpClient) {}

  create(quiz: IQuiz): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(quiz);
    return this.http
      .post<IQuiz>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(quiz: IQuiz): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(quiz);
    return this.http
      .put<IQuiz>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IQuiz>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IQuiz[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(quiz: IQuiz): IQuiz {
    const copy: IQuiz = Object.assign({}, quiz, {
      duree: quiz.duree && quiz.duree.isValid() ? quiz.duree.toJSON() : undefined,
      datePublication: quiz.datePublication && quiz.datePublication.isValid() ? quiz.datePublication.toJSON() : undefined,
      dateExpiration: quiz.dateExpiration && quiz.dateExpiration.isValid() ? quiz.dateExpiration.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.duree = res.body.duree ? moment(res.body.duree) : undefined;
      res.body.datePublication = res.body.datePublication ? moment(res.body.datePublication) : undefined;
      res.body.dateExpiration = res.body.dateExpiration ? moment(res.body.dateExpiration) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((quiz: IQuiz) => {
        quiz.duree = quiz.duree ? moment(quiz.duree) : undefined;
        quiz.datePublication = quiz.datePublication ? moment(quiz.datePublication) : undefined;
        quiz.dateExpiration = quiz.dateExpiration ? moment(quiz.dateExpiration) : undefined;
      });
    }
    return res;
  }
}
