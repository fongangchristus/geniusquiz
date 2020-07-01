import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IClasse, Classe } from 'app/shared/model/classe.model';
import { ClasseService } from './classe.service';
import { INiveau } from 'app/shared/model/niveau.model';
import { NiveauService } from 'app/entities/niveau/niveau.service';
import { IOption } from 'app/shared/model/option.model';
import { OptionService } from 'app/entities/option/option.service';
import { ICategorieFormation } from 'app/shared/model/categorie-formation.model';
import { CategorieFormationService } from 'app/entities/categorie-formation/categorie-formation.service';
import { IMatiere } from 'app/shared/model/matiere.model';
import { MatiereService } from 'app/entities/matiere/matiere.service';

type SelectableEntity = INiveau | IOption | ICategorieFormation | IMatiere;

@Component({
  selector: 'jhi-classe-update',
  templateUrl: './classe-update.component.html',
})
export class ClasseUpdateComponent implements OnInit {
  isSaving = false;
  niveaus: INiveau[] = [];
  options: IOption[] = [];
  categorieformations: ICategorieFormation[] = [];
  matieres: IMatiere[] = [];

  editForm = this.fb.group({
    id: [],
    libele: [],
    description: [],
    imageCouverture: [],
    code: [],
    niveauId: [],
    optionId: [],
    categorieFormationId: [],
    matieres: [],
  });

  constructor(
    protected classeService: ClasseService,
    protected niveauService: NiveauService,
    protected optionService: OptionService,
    protected categorieFormationService: CategorieFormationService,
    protected matiereService: MatiereService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ classe }) => {
      this.updateForm(classe);

      this.niveauService.query().subscribe((res: HttpResponse<INiveau[]>) => (this.niveaus = res.body || []));

      this.optionService.query().subscribe((res: HttpResponse<IOption[]>) => (this.options = res.body || []));

      this.categorieFormationService
        .query()
        .subscribe((res: HttpResponse<ICategorieFormation[]>) => (this.categorieformations = res.body || []));

      this.matiereService.query().subscribe((res: HttpResponse<IMatiere[]>) => (this.matieres = res.body || []));
    });
  }

  updateForm(classe: IClasse): void {
    this.editForm.patchValue({
      id: classe.id,
      libele: classe.libele,
      description: classe.description,
      imageCouverture: classe.imageCouverture,
      code: classe.code,
      niveauId: classe.niveauId,
      optionId: classe.optionId,
      categorieFormationId: classe.categorieFormationId,
      matieres: classe.matieres,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const classe = this.createFromForm();
    if (classe.id !== undefined) {
      this.subscribeToSaveResponse(this.classeService.update(classe));
    } else {
      this.subscribeToSaveResponse(this.classeService.create(classe));
    }
  }

  private createFromForm(): IClasse {
    return {
      ...new Classe(),
      id: this.editForm.get(['id'])!.value,
      libele: this.editForm.get(['libele'])!.value,
      description: this.editForm.get(['description'])!.value,
      imageCouverture: this.editForm.get(['imageCouverture'])!.value,
      code: this.editForm.get(['code'])!.value,
      niveauId: this.editForm.get(['niveauId'])!.value,
      optionId: this.editForm.get(['optionId'])!.value,
      categorieFormationId: this.editForm.get(['categorieFormationId'])!.value,
      matieres: this.editForm.get(['matieres'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IClasse>>): void {
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

  getSelected(selectedVals: IMatiere[], option: IMatiere): IMatiere {
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
