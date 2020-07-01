import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IChapitre, Chapitre } from 'app/shared/model/chapitre.model';
import { ChapitreService } from './chapitre.service';
import { ChapitreComponent } from './chapitre.component';
import { ChapitreDetailComponent } from './chapitre-detail.component';
import { ChapitreUpdateComponent } from './chapitre-update.component';

@Injectable({ providedIn: 'root' })
export class ChapitreResolve implements Resolve<IChapitre> {
  constructor(private service: ChapitreService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IChapitre> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((chapitre: HttpResponse<Chapitre>) => {
          if (chapitre.body) {
            return of(chapitre.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Chapitre());
  }
}

export const chapitreRoute: Routes = [
  {
    path: '',
    component: ChapitreComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'geniusquizApp.chapitre.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ChapitreDetailComponent,
    resolve: {
      chapitre: ChapitreResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'geniusquizApp.chapitre.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ChapitreUpdateComponent,
    resolve: {
      chapitre: ChapitreResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'geniusquizApp.chapitre.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ChapitreUpdateComponent,
    resolve: {
      chapitre: ChapitreResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'geniusquizApp.chapitre.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
