import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IReponseUser, ReponseUser } from 'app/shared/model/reponse-user.model';
import { ReponseUserService } from './reponse-user.service';
import { IQuestion } from 'app/shared/model/question.model';
import { QuestionService } from 'app/entities/question/question.service';
import { IEvaluation } from 'app/shared/model/evaluation.model';
import { EvaluationService } from 'app/entities/evaluation/evaluation.service';
import { IEventuelReponse } from 'app/shared/model/eventuel-reponse.model';
import { EventuelReponseService } from 'app/entities/eventuel-reponse/eventuel-reponse.service';

type SelectableEntity = IQuestion | IEvaluation | IEventuelReponse;

@Component({
  selector: 'jhi-reponse-user-update',
  templateUrl: './reponse-user-update.component.html',
})
export class ReponseUserUpdateComponent implements OnInit {
  isSaving = false;
  quizquestions: IQuestion[] = [];
  evaluations: IEvaluation[] = [];
  eventuelreponses: IEventuelReponse[] = [];

  editForm = this.fb.group({
    id: [],
    dateReponse: [],
    libele: [],
    quizQuestionId: [],
    evaluationId: [],
    eventuelReponses: [],
  });

  constructor(
    protected reponseUserService: ReponseUserService,
    protected questionService: QuestionService,
    protected evaluationService: EvaluationService,
    protected eventuelReponseService: EventuelReponseService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ reponseUser }) => {
      if (!reponseUser.id) {
        const today = moment().startOf('day');
        reponseUser.dateReponse = today;
      }

      this.updateForm(reponseUser);

      this.questionService
        .query({ filter: 'reponseuser-is-null' })
        .pipe(
          map((res: HttpResponse<IQuestion[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IQuestion[]) => {
          if (!reponseUser.quizQuestionId) {
            this.quizquestions = resBody;
          } else {
            this.questionService
              .find(reponseUser.quizQuestionId)
              .pipe(
                map((subRes: HttpResponse<IQuestion>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IQuestion[]) => (this.quizquestions = concatRes));
          }
        });

      this.evaluationService.query().subscribe((res: HttpResponse<IEvaluation[]>) => (this.evaluations = res.body || []));

      this.eventuelReponseService.query().subscribe((res: HttpResponse<IEventuelReponse[]>) => (this.eventuelreponses = res.body || []));
    });
  }

  updateForm(reponseUser: IReponseUser): void {
    this.editForm.patchValue({
      id: reponseUser.id,
      dateReponse: reponseUser.dateReponse ? reponseUser.dateReponse.format(DATE_TIME_FORMAT) : null,
      libele: reponseUser.libele,
      quizQuestionId: reponseUser.quizQuestionId,
      evaluationId: reponseUser.evaluationId,
      eventuelReponses: reponseUser.eventuelReponses,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const reponseUser = this.createFromForm();
    if (reponseUser.id !== undefined) {
      this.subscribeToSaveResponse(this.reponseUserService.update(reponseUser));
    } else {
      this.subscribeToSaveResponse(this.reponseUserService.create(reponseUser));
    }
  }

  private createFromForm(): IReponseUser {
    return {
      ...new ReponseUser(),
      id: this.editForm.get(['id'])!.value,
      dateReponse: this.editForm.get(['dateReponse'])!.value
        ? moment(this.editForm.get(['dateReponse'])!.value, DATE_TIME_FORMAT)
        : undefined,
      libele: this.editForm.get(['libele'])!.value,
      quizQuestionId: this.editForm.get(['quizQuestionId'])!.value,
      evaluationId: this.editForm.get(['evaluationId'])!.value,
      eventuelReponses: this.editForm.get(['eventuelReponses'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IReponseUser>>): void {
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

  getSelected(selectedVals: IEventuelReponse[], option: IEventuelReponse): IEventuelReponse {
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
