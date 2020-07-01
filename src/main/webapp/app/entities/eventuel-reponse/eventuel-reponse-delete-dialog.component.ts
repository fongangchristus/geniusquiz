import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEventuelReponse } from 'app/shared/model/eventuel-reponse.model';
import { EventuelReponseService } from './eventuel-reponse.service';

@Component({
  templateUrl: './eventuel-reponse-delete-dialog.component.html',
})
export class EventuelReponseDeleteDialogComponent {
  eventuelReponse?: IEventuelReponse;

  constructor(
    protected eventuelReponseService: EventuelReponseService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.eventuelReponseService.delete(id).subscribe(() => {
      this.eventManager.broadcast('eventuelReponseListModification');
      this.activeModal.close();
    });
  }
}
