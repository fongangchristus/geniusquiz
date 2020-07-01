import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GeniusquizSharedModule } from 'app/shared/shared.module';
import { ChapitreComponent } from './chapitre.component';
import { ChapitreDetailComponent } from './chapitre-detail.component';
import { ChapitreUpdateComponent } from './chapitre-update.component';
import { ChapitreDeleteDialogComponent } from './chapitre-delete-dialog.component';
import { chapitreRoute } from './chapitre.route';

@NgModule({
  imports: [GeniusquizSharedModule, RouterModule.forChild(chapitreRoute)],
  declarations: [ChapitreComponent, ChapitreDetailComponent, ChapitreUpdateComponent, ChapitreDeleteDialogComponent],
  entryComponents: [ChapitreDeleteDialogComponent],
})
export class GeniusquizChapitreModule {}
