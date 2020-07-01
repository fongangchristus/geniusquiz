import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GeniusquizTestModule } from '../../../test.module';
import { ChapitreDetailComponent } from 'app/entities/chapitre/chapitre-detail.component';
import { Chapitre } from 'app/shared/model/chapitre.model';

describe('Component Tests', () => {
  describe('Chapitre Management Detail Component', () => {
    let comp: ChapitreDetailComponent;
    let fixture: ComponentFixture<ChapitreDetailComponent>;
    const route = ({ data: of({ chapitre: new Chapitre(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GeniusquizTestModule],
        declarations: [ChapitreDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ChapitreDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ChapitreDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load chapitre on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.chapitre).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
