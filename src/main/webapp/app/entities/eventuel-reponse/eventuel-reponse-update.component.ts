import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEventuelReponse, EventuelReponse } from 'app/shared/model/eventuel-reponse.model';
import { EventuelReponseService } from './eventuel-reponse.service';
import { IQuiz } from 'app/shared/model/quiz.model';
import { QuizService } from 'app/entities/quiz/quiz.service';

@Component({
  selector: 'jhi-eventuel-reponse-update',
  templateUrl: './eventuel-reponse-update.component.html',
})
export class EventuelReponseUpdateComponent implements OnInit {
  isSaving = false;
  quizzes: IQuiz[] = [];

  editForm = this.fb.group({
    id: [],
    libele: [null, [Validators.required]],
    code: [],
    imageCouverture: [],
    description: [],
    correctAnswer: [],
    point: [],
    quizId: [],
  });

  constructor(
    protected eventuelReponseService: EventuelReponseService,
    protected quizService: QuizService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ eventuelReponse }) => {
      this.updateForm(eventuelReponse);

      this.quizService.query().subscribe((res: HttpResponse<IQuiz[]>) => (this.quizzes = res.body || []));
    });
  }

  updateForm(eventuelReponse: IEventuelReponse): void {
    this.editForm.patchValue({
      id: eventuelReponse.id,
      libele: eventuelReponse.libele,
      code: eventuelReponse.code,
      imageCouverture: eventuelReponse.imageCouverture,
      description: eventuelReponse.description,
      correctAnswer: eventuelReponse.correctAnswer,
      point: eventuelReponse.point,
      quizId: eventuelReponse.quizId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const eventuelReponse = this.createFromForm();
    if (eventuelReponse.id !== undefined) {
      this.subscribeToSaveResponse(this.eventuelReponseService.update(eventuelReponse));
    } else {
      this.subscribeToSaveResponse(this.eventuelReponseService.create(eventuelReponse));
    }
  }

  private createFromForm(): IEventuelReponse {
    return {
      ...new EventuelReponse(),
      id: this.editForm.get(['id'])!.value,
      libele: this.editForm.get(['libele'])!.value,
      code: this.editForm.get(['code'])!.value,
      imageCouverture: this.editForm.get(['imageCouverture'])!.value,
      description: this.editForm.get(['description'])!.value,
      correctAnswer: this.editForm.get(['correctAnswer'])!.value,
      point: this.editForm.get(['point'])!.value,
      quizId: this.editForm.get(['quizId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEventuelReponse>>): void {
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
