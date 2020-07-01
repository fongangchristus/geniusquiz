import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEventuelReponse } from 'app/shared/model/eventuel-reponse.model';

@Component({
  selector: 'jhi-eventuel-reponse-detail',
  templateUrl: './eventuel-reponse-detail.component.html',
})
export class EventuelReponseDetailComponent implements OnInit {
  eventuelReponse: IEventuelReponse | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ eventuelReponse }) => (this.eventuelReponse = eventuelReponse));
  }

  previousState(): void {
    window.history.back();
  }
}
