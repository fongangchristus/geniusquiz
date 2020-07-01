import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GeniusquizTestModule } from '../../../test.module';
import { EventuelReponseDetailComponent } from 'app/entities/eventuel-reponse/eventuel-reponse-detail.component';
import { EventuelReponse } from 'app/shared/model/eventuel-reponse.model';

describe('Component Tests', () => {
  describe('EventuelReponse Management Detail Component', () => {
    let comp: EventuelReponseDetailComponent;
    let fixture: ComponentFixture<EventuelReponseDetailComponent>;
    const route = ({ data: of({ eventuelReponse: new EventuelReponse(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GeniusquizTestModule],
        declarations: [EventuelReponseDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(EventuelReponseDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EventuelReponseDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load eventuelReponse on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.eventuelReponse).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
