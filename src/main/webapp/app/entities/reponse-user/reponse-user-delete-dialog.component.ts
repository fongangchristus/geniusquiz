import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IReponseUser } from 'app/shared/model/reponse-user.model';
import { ReponseUserService } from './reponse-user.service';

@Component({
  templateUrl: './reponse-user-delete-dialog.component.html',
})
export class ReponseUserDeleteDialogComponent {
  reponseUser?: IReponseUser;

  constructor(
    protected reponseUserService: ReponseUserService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.reponseUserService.delete(id).subscribe(() => {
      this.eventManager.broadcast('reponseUserListModification');
      this.activeModal.close();
    });
  }
}
