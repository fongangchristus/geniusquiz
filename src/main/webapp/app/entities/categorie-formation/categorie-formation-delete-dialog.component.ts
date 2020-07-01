import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICategorieFormation } from 'app/shared/model/categorie-formation.model';
import { CategorieFormationService } from './categorie-formation.service';

@Component({
  templateUrl: './categorie-formation-delete-dialog.component.html',
})
export class CategorieFormationDeleteDialogComponent {
  categorieFormation?: ICategorieFormation;

  constructor(
    protected categorieFormationService: CategorieFormationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.categorieFormationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('categorieFormationListModification');
      this.activeModal.close();
    });
  }
}
