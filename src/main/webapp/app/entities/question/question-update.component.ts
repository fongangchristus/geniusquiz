import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IQuestion, Question } from 'app/shared/model/question.model';
import { QuestionService } from './question.service';
import { IChapitre } from 'app/shared/model/chapitre.model';
import { ChapitreService } from 'app/entities/chapitre/chapitre.service';

@Component({
  selector: 'jhi-question-update',
  templateUrl: './question-update.component.html',
})
export class QuestionUpdateComponent implements OnInit {
  isSaving = false;
  chapitres: IChapitre[] = [];

  editForm = this.fb.group({
    id: [],
    libele: [null, [Validators.required]],
    idChapitre: [],
    description: [],
    imageCouverture: [],
    point: [],
    isActif: [],
    chapitreId: [],
  });

  constructor(
    protected questionService: QuestionService,
    protected chapitreService: ChapitreService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ question }) => {
      this.updateForm(question);

      this.chapitreService.query().subscribe((res: HttpResponse<IChapitre[]>) => (this.chapitres = res.body || []));
    });
  }

  updateForm(question: IQuestion): void {
    this.editForm.patchValue({
      id: question.id,
      libele: question.libele,
      idChapitre: question.idChapitre,
      description: question.description,
      imageCouverture: question.imageCouverture,
      point: question.point,
      isActif: question.isActif,
      chapitreId: question.chapitreId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const question = this.createFromForm();
    if (question.id !== undefined) {
      this.subscribeToSaveResponse(this.questionService.update(question));
    } else {
      this.subscribeToSaveResponse(this.questionService.create(question));
    }
  }

  private createFromForm(): IQuestion {
    return {
      ...new Question(),
      id: this.editForm.get(['id'])!.value,
      libele: this.editForm.get(['libele'])!.value,
      idChapitre: this.editForm.get(['idChapitre'])!.value,
      description: this.editForm.get(['description'])!.value,
      imageCouverture: this.editForm.get(['imageCouverture'])!.value,
      point: this.editForm.get(['point'])!.value,
      isActif: this.editForm.get(['isActif'])!.value,
      chapitreId: this.editForm.get(['chapitreId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IQuestion>>): void {
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

  trackById(index: number, item: IChapitre): any {
    return item.id;
  }
}
