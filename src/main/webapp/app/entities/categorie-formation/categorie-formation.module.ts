import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GeniusquizSharedModule } from 'app/shared/shared.module';
import { CategorieFormationComponent } from './categorie-formation.component';
import { CategorieFormationDetailComponent } from './categorie-formation-detail.component';
import { CategorieFormationUpdateComponent } from './categorie-formation-update.component';
import { CategorieFormationDeleteDialogComponent } from './categorie-formation-delete-dialog.component';
import { categorieFormationRoute } from './categorie-formation.route';

@NgModule({
  imports: [GeniusquizSharedModule, RouterModule.forChild(categorieFormationRoute)],
  declarations: [
    CategorieFormationComponent,
    CategorieFormationDetailComponent,
    CategorieFormationUpdateComponent,
    CategorieFormationDeleteDialogComponent,
  ],
  entryComponents: [CategorieFormationDeleteDialogComponent],
})
export class GeniusquizCategorieFormationModule {}
