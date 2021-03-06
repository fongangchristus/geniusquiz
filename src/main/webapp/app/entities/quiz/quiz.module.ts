import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GeniusquizSharedModule } from 'app/shared/shared.module';
import { QuizComponent } from './quiz.component';
import { QuizDetailComponent } from './quiz-detail.component';
import { QuizUpdateComponent } from './quiz-update.component';
import { QuizDeleteDialogComponent } from './quiz-delete-dialog.component';
import { quizRoute } from './quiz.route';

@NgModule({
  imports: [GeniusquizSharedModule, RouterModule.forChild(quizRoute)],
  declarations: [QuizComponent, QuizDetailComponent, QuizUpdateComponent, QuizDeleteDialogComponent],
  entryComponents: [QuizDeleteDialogComponent],
})
export class GeniusquizQuizModule {}
