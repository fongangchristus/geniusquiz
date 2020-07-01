import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IChapitre } from 'app/shared/model/chapitre.model';
import { ChapitreService } from './chapitre.service';

@Component({
  templateUrl: './chapitre-delete-dialog.component.html',
})
export class ChapitreDeleteDialogComponent {
  chapitre?: IChapitre;

  constructor(protected chapitreService: ChapitreService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.chapitreService.delete(id).subscribe(() => {
      this.eventManager.broadcast('chapitreListModification');
      this.activeModal.close();
    });
  }
}
