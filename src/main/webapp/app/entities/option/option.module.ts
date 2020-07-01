import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GeniusquizSharedModule } from 'app/shared/shared.module';
import { OptionComponent } from './option.component';
import { OptionDetailComponent } from './option-detail.component';
import { OptionUpdateComponent } from './option-update.component';
import { OptionDeleteDialogComponent } from './option-delete-dialog.component';
import { optionRoute } from './option.route';

@NgModule({
  imports: [GeniusquizSharedModule, RouterModule.forChild(optionRoute)],
  declarations: [OptionComponent, OptionDetailComponent, OptionUpdateComponent, OptionDeleteDialogComponent],
  entryComponents: [OptionDeleteDialogComponent],
})
export class GeniusquizOptionModule {}
