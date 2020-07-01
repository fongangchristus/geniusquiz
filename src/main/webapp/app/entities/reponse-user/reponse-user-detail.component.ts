import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IReponseUser } from 'app/shared/model/reponse-user.model';

@Component({
  selector: 'jhi-reponse-user-detail',
  templateUrl: './reponse-user-detail.component.html',
})
export class ReponseUserDetailComponent implements OnInit {
  reponseUser: IReponseUser | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ reponseUser }) => (this.reponseUser = reponseUser));
  }

  previousState(): void {
    window.history.back();
  }
}
