import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICategorieFormation, CategorieFormation } from 'app/shared/model/categorie-formation.model';
import { CategorieFormationService } from './categorie-formation.service';

@Component({
  selector: 'jhi-categorie-formation-update',
  templateUrl: './categorie-formation-update.component.html',
})
export class CategorieFormationUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libele: [],
    description: [],
    imageCouverture: [],
    code: [],
  });

  constructor(
    protected categorieFormationService: CategorieFormationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ categorieFormation }) => {
      this.updateForm(categorieFormation);
    });
  }

  updateForm(categorieFormation: ICategorieFormation): void {
    this.editForm.patchValue({
      id: categorieFormation.id,
      libele: categorieFormation.libele,
      description: categorieFormation.description,
      imageCouverture: categorieFormation.imageCouverture,
      code: categorieFormation.code,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const categorieFormation = this.createFromForm();
    if (categorieFormation.id !== undefined) {
      this.subscribeToSaveResponse(this.categorieFormationService.update(categorieFormation));
    } else {
      this.subscribeToSaveResponse(this.categorieFormationService.create(categorieFormation));
    }
  }

  private createFromForm(): ICategorieFormation {
    return {
      ...new CategorieFormation(),
      id: this.editForm.get(['id'])!.value,
      libele: this.editForm.get(['libele'])!.value,
      description: this.editForm.get(['description'])!.value,
      imageCouverture: this.editForm.get(['imageCouverture'])!.value,
      code: this.editForm.get(['code'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICategorieFormation>>): void {
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
