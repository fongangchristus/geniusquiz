import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IChapitre, Chapitre } from 'app/shared/model/chapitre.model';
import { ChapitreService } from './chapitre.service';
import { IMatiere } from 'app/shared/model/matiere.model';
import { MatiereService } from 'app/entities/matiere/matiere.service';

@Component({
  selector: 'jhi-chapitre-update',
  templateUrl: './chapitre-update.component.html',
})
export class ChapitreUpdateComponent implements OnInit {
  isSaving = false;
  matieres: IMatiere[] = [];

  editForm = this.fb.group({
    id: [],
    libele: [null, [Validators.required]],
    description: [],
    imageCouverture: [],
    fichierCours: [],
    matiereId: [],
  });

  constructor(
    protected chapitreService: ChapitreService,
    protected matiereService: MatiereService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ chapitre }) => {
      this.updateForm(chapitre);

      this.matiereService.query().subscribe((res: HttpResponse<IMatiere[]>) => (this.matieres = res.body || []));
    });
  }

  updateForm(chapitre: IChapitre): void {
    this.editForm.patchValue({
      id: chapitre.id,
      libele: chapitre.libele,
      description: chapitre.description,
      imageCouverture: chapitre.imageCouverture,
      fichierCours: chapitre.fichierCours,
      matiereId: chapitre.matiereId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const chapitre = this.createFromForm();
    if (chapitre.id !== undefined) {
      this.subscribeToSaveResponse(this.chapitreService.update(chapitre));
    } else {
      this.subscribeToSaveResponse(this.chapitreService.create(chapitre));
    }
  }

  private createFromForm(): IChapitre {
    return {
      ...new Chapitre(),
      id: this.editForm.get(['id'])!.value,
      libele: this.editForm.get(['libele'])!.value,
      description: this.editForm.get(['description'])!.value,
      imageCouverture: this.editForm.get(['imageCouverture'])!.value,
      fichierCours: this.editForm.get(['fichierCours'])!.value,
      matiereId: this.editForm.get(['matiereId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IChapitre>>): void {
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

  trackById(index: number, item: IMatiere): any {
    return item.id;
  }
}
