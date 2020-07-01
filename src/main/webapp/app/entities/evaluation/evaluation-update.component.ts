import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IEvaluation, Evaluation } from 'app/shared/model/evaluation.model';
import { EvaluationService } from './evaluation.service';
import { IQuiz } from 'app/shared/model/quiz.model';
import { QuizService } from 'app/entities/quiz/quiz.service';

@Component({
  selector: 'jhi-evaluation-update',
  templateUrl: './evaluation-update.component.html',
})
export class EvaluationUpdateComponent implements OnInit {
  isSaving = false;
  quizzes: IQuiz[] = [];

  editForm = this.fb.group({
    id: [],
    lieu: [],
    note: [],
    idUser: [],
    dateEvaluation: [null, [Validators.required]],
    quizId: [],
  });

  constructor(
    protected evaluationService: EvaluationService,
    protected quizService: QuizService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ evaluation }) => {
      if (!evaluation.id) {
        const today = moment().startOf('day');
        evaluation.dateEvaluation = today;
      }

      this.updateForm(evaluation);

      this.quizService.query().subscribe((res: HttpResponse<IQuiz[]>) => (this.quizzes = res.body || []));
    });
  }

  updateForm(evaluation: IEvaluation): void {
    this.editForm.patchValue({
      id: evaluation.id,
      lieu: evaluation.lieu,
      note: evaluation.note,
      idUser: evaluation.idUser,
      dateEvaluation: evaluation.dateEvaluation ? evaluation.dateEvaluation.format(DATE_TIME_FORMAT) : null,
      quizId: evaluation.quizId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const evaluation = this.createFromForm();
    if (evaluation.id !== undefined) {
      this.subscribeToSaveResponse(this.evaluationService.update(evaluation));
    } else {
      this.subscribeToSaveResponse(this.evaluationService.create(evaluation));
    }
  }

  private createFromForm(): IEvaluation {
    return {
      ...new Evaluation(),
      id: this.editForm.get(['id'])!.value,
      lieu: this.editForm.get(['lieu'])!.value,
      note: this.editForm.get(['note'])!.value,
      idUser: this.editForm.get(['idUser'])!.value,
      dateEvaluation: this.editForm.get(['dateEvaluation'])!.value
        ? moment(this.editForm.get(['dateEvaluation'])!.value, DATE_TIME_FORMAT)
        : undefined,
      quizId: this.editForm.get(['quizId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEvaluation>>): void {
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

  trackById(index: number, item: IQuiz): any {
    return item.id;
  }
}
