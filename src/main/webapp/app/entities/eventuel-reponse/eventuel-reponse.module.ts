import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GeniusquizSharedModule } from 'app/shared/shared.module';
import { EventuelReponseComponent } from './eventuel-reponse.component';
import { EventuelReponseDetailComponent } from './eventuel-reponse-detail.component';
import { EventuelReponseUpdateComponent } from './eventuel-reponse-update.component';
import { EventuelReponseDeleteDialogComponent } from './eventuel-reponse-delete-dialog.component';
import { eventuelReponseRoute } from './eventuel-reponse.route';

@NgModule({
  imports: [GeniusquizSharedModule, RouterModule.forChild(eventuelReponseRoute)],
  declarations: [
    EventuelReponseComponent,
    EventuelReponseDetailComponent,
    EventuelReponseUpdateComponent,
    EventuelReponseDeleteDialogComponent,
  ],
  entryComponents: [EventuelReponseDeleteDialogComponent],
})
export class GeniusquizEventuelReponseModule {}
