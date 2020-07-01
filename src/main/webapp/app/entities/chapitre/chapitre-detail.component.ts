import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IChapitre } from 'app/shared/model/chapitre.model';

@Component({
  selector: 'jhi-chapitre-detail',
  templateUrl: './chapitre-detail.component.html',
})
export class ChapitreDetailComponent implements OnInit {
  chapitre: IChapitre | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ chapitre }) => (this.chapitre = chapitre));
  }

  previousState(): void {
    window.history.back();
  }
}
