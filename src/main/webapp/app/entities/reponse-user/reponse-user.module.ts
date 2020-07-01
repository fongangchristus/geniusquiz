import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GeniusquizSharedModule } from 'app/shared/shared.module';
import { ReponseUserComponent } from './reponse-user.component';
import { ReponseUserDetailComponent } from './reponse-user-detail.component';
import { ReponseUserUpdateComponent } from './reponse-user-update.component';
import { ReponseUserDeleteDialogComponent } from './reponse-user-delete-dialog.component';
import { reponseUserRoute } from './reponse-user.route';

@NgModule({
  imports: [GeniusquizSharedModule, RouterModule.forChild(reponseUserRoute)],
  declarations: [ReponseUserComponent, ReponseUserDetailComponent, ReponseUserUpdateComponent, ReponseUserDeleteDialogComponent],
  entryComponents: [ReponseUserDeleteDialogComponent],
})
export class GeniusquizReponseUserModule {}
