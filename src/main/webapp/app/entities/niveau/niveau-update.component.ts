import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { INiveau, Niveau } from 'app/shared/model/niveau.model';
import { NiveauService } from './niveau.service';
import { ICursus } from 'app/shared/model/cursus.model';
import { CursusService } from 'app/entities/cursus/cursus.service';

@Component({
  selector: 'jhi-niveau-update',
  templateUrl: './niveau-update.component.html',
})
export class NiveauUpdateComponent implements OnInit {
  isSaving = false;
  cursuses: ICursus[] = [];

  editForm = this.fb.group({
    id: [],
    libele: [],
    description: [],
    imageCouverture: [],
    code: [],
    curcusId: [],
  });

  constructor(
    protected niveauService: NiveauService,
    protected cursusService: CursusService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ niveau }) => {
      this.updateForm(niveau);

      this.cursusService.query().subscribe((res: HttpResponse<ICursus[]>) => (this.cursuses = res.body || []));
    });
  }

  updateForm(niveau: INiveau): void {
    this.editForm.patchValue({
      id: niveau.id,
      libele: niveau.libele,
      description: niveau.description,
      imageCouverture: niveau.imageCouverture,
      code: niveau.code,
      curcusId: niveau.curcusId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const niveau = this.createFromForm();
    if (niveau.id !== undefined) {
      this.subscribeToSaveResponse(this.niveauService.update(niveau));
    } else {
      this.subscribeToSaveResponse(this.niveauService.create(niveau));
    }
  }

  private createFromForm(): INiveau {
    return {
      ...new Niveau(),
      id: this.editForm.get(['id'])!.value,
      libele: this.editForm.get(['libele'])!.value,
      description: this.editForm.get(['description'])!.value,
      imageCouverture: this.editForm.get(['imageCouverture'])!.value,
      code: this.editForm.get(['code'])!.value,
      curcusId: this.editForm.get(['curcusId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INiveau>>): void {
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
