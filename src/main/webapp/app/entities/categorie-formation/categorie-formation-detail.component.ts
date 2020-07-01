import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICategorieFormation } from 'app/shared/model/categorie-formation.model';

@Component({
  selector: 'jhi-categorie-formation-detail',
  templateUrl: './categorie-formation-detail.component.html',
})
export class CategorieFormationDetailComponent implements OnInit {
  categorieFormation: ICategorieFormation | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ categorieFormation }) => (this.categorieFormation = categorieFormation));
  }

  previousState(): void {
    window.history.back();
  }
}
