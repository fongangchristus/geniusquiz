import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'quiz',
        loadChildren: () => import('./quiz/quiz.module').then(m => m.GeniusquizQuizModule),
      },
      {
        path: 'evaluation',
        loadChildren: () => import('./evaluation/evaluation.module').then(m => m.GeniusquizEvaluationModule),
      },
      {
        path: 'question',
        loadChildren: () => import('./question/question.module').then(m => m.GeniusquizQuestionModule),
      },
      {
        path: 'eventuel-reponse',
        loadChildren: () => import('./eventuel-reponse/eventuel-reponse.module').then(m => m.GeniusquizEventuelReponseModule),
      },
      {
        path: 'chapitre',
        loadChildren: () => import('./chapitre/chapitre.module').then(m => m.GeniusquizChapitreModule),
      },
      {
        path: 'reponse-user',
        loadChildren: () => import('./reponse-user/reponse-user.module').then(m => m.GeniusquizReponseUserModule),
      },
      {
        path: 'matiere',
        loadChildren: () => import('./matiere/matiere.module').then(m => m.GeniusquizMatiereModule),
      },
      {
        path: 'niveau',
        loadChildren: () => import('./niveau/niveau.module').then(m => m.GeniusquizNiveauModule),
      },
      {
        path: 'option',
        loadChildren: () => import('./option/option.module').then(m => m.GeniusquizOptionModule),
      },
      {
        path: 'cursus',
        loadChildren: () => import('./cursus/cursus.module').then(m => m.GeniusquizCursusModule),
      },
      {
        path: 'classe',
        loadChildren: () => import('./classe/classe.module').then(m => m.GeniusquizClasseModule),
      },
      {
        path: 'categorie-formation',
        loadChildren: () => import('./categorie-formation/categorie-formation.module').then(m => m.GeniusquizCategorieFormationModule),
      },
      {
        path: 'notification',
        loadChildren: () => import('./notification/notification.module').then(m => m.GeniusquizNotificationModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class GeniusquizEntityModule {}
