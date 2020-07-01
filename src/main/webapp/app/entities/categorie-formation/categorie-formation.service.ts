import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICategorieFormation } from 'app/shared/model/categorie-formation.model';

type EntityResponseType = HttpResponse<ICategorieFormation>;
type EntityArrayResponseType = HttpResponse<ICategorieFormation[]>;

@Injectable({ providedIn: 'root' })
export class CategorieFormationService {
  public resourceUrl = SERVER_API_URL + 'api/categorie-formations';

  constructor(protected http: HttpClient) {}

  create(categorieFormation: ICategorieFormation): Observable<EntityResponseType> {
    return this.http.post<ICategorieFormation>(this.resourceUrl, categorieFormation, { observe: 'response' });
  }

  update(categorieFormation: ICategorieFormation): Observable<EntityResponseType> {
    return this.http.put<ICategorieFormation>(this.resourceUrl, categorieFormation, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICategorieFormation>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICategorieFormation[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
