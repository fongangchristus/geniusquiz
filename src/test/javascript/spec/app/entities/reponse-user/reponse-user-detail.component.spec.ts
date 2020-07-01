import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GeniusquizTestModule } from '../../../test.module';
import { ReponseUserDetailComponent } from 'app/entities/reponse-user/reponse-user-detail.component';
import { ReponseUser } from 'app/shared/model/reponse-user.model';

describe('Component Tests', () => {
  describe('ReponseUser Management Detail Component', () => {
    let comp: ReponseUserDetailComponent;
    let fixture: ComponentFixture<ReponseUserDetailComponent>;
    const route = ({ data: of({ reponseUser: new ReponseUser(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GeniusquizTestModule],
        declarations: [ReponseUserDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ReponseUserDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ReponseUserDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load reponseUser on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.reponseUser).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
