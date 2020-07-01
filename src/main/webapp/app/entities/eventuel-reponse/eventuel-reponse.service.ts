import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEventuelReponse } from 'app/shared/model/eventuel-reponse.model';

type EntityResponseType = HttpResponse<IEventuelReponse>;
type EntityArrayResponseType = HttpResponse<IEventuelReponse[]>;

@Injectable({ providedIn: 'root' })
export class EventuelReponseService {
  public resourceUrl = SERVER_API_URL + 'api/eventuel-reponses';

  constructor(protected http: HttpClient) {}

  create(eventuelReponse: IEventuelReponse): Observable<EntityResponseType> {
    return this.http.post<IEventuelReponse>(this.resourceUrl, eventuelReponse, { observe: 'response' });
  }

  update(eventuelReponse: IEventuelReponse): Observable<EntityResponseType> {
    return this.http.put<IEventuelReponse>(this.resourceUrl, eventuelReponse, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEventuelReponse>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEventuelReponse[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
