import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IQuiz, Quiz } from 'app/shared/model/quiz.model';
import { QuizService } from './quiz.service';
import { IMatiere } from 'app/shared/model/matiere.model';
import { MatiereService } from 'app/entities/matiere/matiere.service';
import { IQuestion } from 'app/shared/model/question.model';
import { QuestionService } from 'app/entities/question/question.service';

type SelectableEntity = IMatiere | IQuestion;

@Component({
  selector: 'jhi-quiz-update',
  templateUrl: './quiz-update.component.html',
})
export class QuizUpdateComponent implements OnInit {
  isSaving = false;
  matieres: IMatiere[] = [];
  questions: IQuestion[] = [];

  editForm = this.fb.group({
    id: [],
    type: [],
    idMatiere: [],
    entete: [],
    description: [],
    libele: [null, [Validators.required]],
    imageCouverture: [],
    duree: [],
    nbrQuestion: [],
    datePublication: [],
    dateExpiration: [],
    matiereId: [],
    questions: [null, Validators.required],
  });

  constructor(
    protected quizService: QuizService,
    protected matiereService: MatiereService,
    protected questionService: QuestionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ quiz }) => {
      if (!quiz.id) {
        const today = moment().startOf('day');
        quiz.duree = today;
        quiz.datePublication = today;
        quiz.dateExpiration = today;
      }

      this.updateForm(quiz);

      this.matiereService.query().subscribe((res: HttpResponse<IMatiere[]>) => (this.matieres = res.body || []));

      this.questionService.query().subscribe((res: HttpResponse<IQuestion[]>) => (this.questions = res.body || []));
    });
  }

  updateForm(quiz: IQuiz): void {
    this.editForm.patchValue({
      id: quiz.id,
      type: quiz.type,
      idMatiere: quiz.idMatiere,
      entete: quiz.entete,
      description: quiz.description,
      libele: quiz.libele,
      imageCouverture: quiz.imageCouverture,
      duree: quiz.duree ? quiz.duree.format(DATE_TIME_FORMAT) : null,
      nbrQuestion: quiz.nbrQuestion,
      datePublication: quiz.datePublication ? quiz.datePublication.format(DATE_TIME_FORMAT) : null,
      dateExpiration: quiz.dateExpiration ? quiz.dateExpiration.format(DATE_TIME_FORMAT) : null,
      matiereId: quiz.matiereId,
      questions: quiz.questions,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const quiz = this.createFromForm();
    if (quiz.id !== undefined) {
      this.subscribeToSaveResponse(this.quizService.update(quiz));
    } else {
      this.subscribeToSaveResponse(this.quizService.create(quiz));
    }
  }

  private createFromForm(): IQuiz {
    return {
      ...new Quiz(),
      id: this.editForm.get(['id'])!.value,
      type: this.editForm.get(['type'])!.value,
      idMatiere: this.editForm.get(['idMatiere'])!.value,
      entete: this.editForm.get(['entete'])!.value,
      description: this.editForm.get(['description'])!.value,
      libele: this.editForm.get(['libele'])!.value,
      imageCouverture: this.editForm.get(['imageCouverture'])!.value,
      duree: this.editForm.get(['duree'])!.value ? moment(this.editForm.get(['duree'])!.value, DATE_TIME_FORMAT) : undefined,
      nbrQuestion: this.editForm.get(['nbrQuestion'])!.value,
      datePublication: this.editForm.get(['datePublication'])!.value
        ? moment(this.editForm.get(['datePublication'])!.value, DATE_TIME_FORMAT)
        : undefined,
      dateExpiration: this.editForm.get(['dateExpiration'])!.value
        ? moment(this.editForm.get(['dateExpiration'])!.value, DATE_TIME_FORMAT)
        : undefined,
      matiereId: this.editForm.get(['matiereId'])!.value,
      questions: this.editForm.get(['questions'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IQuiz>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }

  getSelected(selectedVals: IQuestion[], option: IQuestion): IQuestion {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
