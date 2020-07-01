import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICursus, Cursus } from 'app/shared/model/cursus.model';
import { CursusService } from './cursus.service';

@Component({
  selector: 'jhi-cursus-update',
  templateUrl: './cursus-update.component.html',
})
export class CursusUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libele: [],
    description: [],
    imageCouverture: [],
    code: [],
  });

  constructor(protected cursusService: CursusService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ cursus }) => {
      this.updateForm(cursus);
    });
  }

  updateForm(cursus: ICursus): void {
    this.editForm.patchValue({
      id: cursus.id,
      libele: cursus.libele,
      description: cursus.description,
      imageCouverture: cursus.imageCouverture,
      code: cursus.code,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const cursus = this.createFromForm();
    if (cursus.id !== undefined) {
      this.subscribeToSaveResponse(this.cursusService.update(cursus));
    } else {
      this.subscribeToSaveResponse(this.cursusService.create(cursus));
    }
  }

  private createFromForm(): ICursus {
    return {
      ...new Cursus(),
      id: this.editForm.get(['id'])!.value,
      libele: this.editForm.get(['libele'])!.value,
      description: this.editForm.get(['description'])!.value,
      imageCouverture: this.editForm.get(['imageCouverture'])!.value,
      code: this.editForm.get(['code'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICursus>>): void {
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
}
