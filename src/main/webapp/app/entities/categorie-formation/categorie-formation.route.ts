import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICategorieFormation, CategorieFormation } from 'app/shared/model/categorie-formation.model';
import { CategorieFormationService } from './categorie-formation.service';
import { CategorieFormationComponent } from './categorie-formation.component';
import { CategorieFormationDetailComponent } from './categorie-formation-detail.component';
import { CategorieFormationUpdateComponent } from './categorie-formation-update.component';

@Injectable({ providedIn: 'root' })
export class CategorieFormationResolve implements Resolve<ICategorieFormation> {
  constructor(private service: CategorieFormationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICategorieFormation> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((categorieFormation: HttpResponse<CategorieFormation>) => {
          if (categorieFormation.body) {
            return of(categorieFormation.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CategorieFormation());
  }
}

export const categorieFormationRoute: Routes = [
  {
    path: '',
    component: CategorieFormationComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'geniusquizApp.categorieFormation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CategorieFormationDetailComponent,
    resolve: {
      categorieFormation: CategorieFormationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'geniusquizApp.categorieFormation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CategorieFormationUpdateComponent,
    resolve: {
      categorieFormation: CategorieFormationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'geniusquizApp.categorieFormation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CategorieFormationUpdateComponent,
    resolve: {
      categorieFormation: CategorieFormationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'geniusquizApp.categorieFormation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
