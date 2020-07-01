import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IOption, Option } from 'app/shared/model/option.model';
import { OptionService } from './option.service';
import { ICursus } from 'app/shared/model/cursus.model';
import { CursusService } from 'app/entities/cursus/cursus.service';

@Component({
  selector: 'jhi-option-update',
  templateUrl: './option-update.component.html',
})
export class OptionUpdateComponent implements OnInit {
  isSaving = false;
  cursuses: ICursus[] = [];

  editForm = this.fb.group({
    id: [],
    libele: [],
    description: [],
    imageCouverture: [],
    code: [],
    quizId: [],
  });

  constructor(
    protected optionService: OptionService,
    protected cursusService: CursusService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ option }) => {
      this.updateForm(option);

      this.cursusService.query().subscribe((res: HttpResponse<ICursus[]>) => (this.cursuses = res.body || []));
    });
  }

  updateForm(option: IOption): void {
    this.editForm.patchValue({
      id: option.id,
      libele: option.libele,
      description: option.description,
      imageCouverture: option.imageCouverture,
      code: option.code,
      quizId: option.quizId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const option = this.createFromForm();
    if (option.id !== undefined) {
      this.subscribeToSaveResponse(this.optionService.update(option));
    } else {
      this.subscribeToSaveResponse(this.optionService.create(option));
    }
  }

  private createFromForm(): IOption {
    return {
      ...new Option(),
      id: this.editForm.get(['id'])!.value,
      libele: this.editForm.get(['libele'])!.value,
      description: this.editForm.get(['description'])!.value,
      imageCouverture: this.editForm.get(['imageCouverture'])!.value,
      code: this.editForm.get(['code'])!.value,
      quizId: this.editForm.get(['quizId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOption>>): void {
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

  trackById(index: number, item: ICursus): any {
    return item.id;
  }
}
