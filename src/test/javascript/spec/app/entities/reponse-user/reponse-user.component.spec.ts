import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { GeniusquizTestModule } from '../../../test.module';
import { ReponseUserComponent } from 'app/entities/reponse-user/reponse-user.component';
import { ReponseUserService } from 'app/entities/reponse-user/reponse-user.service';
import { ReponseUser } from 'app/shared/model/reponse-user.model';

describe('Component Tests', () => {
  describe('ReponseUser Management Component', () => {
    let comp: ReponseUserComponent;
    let fixture: ComponentFixture<ReponseUserComponent>;
    let service: ReponseUserService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GeniusquizTestModule],
        declarations: [ReponseUserComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: {
              data: of({
                defaultSort: 'id,asc',
              }),
              queryParamMap: of(
                convertToParamMap({
                  page: '1',
                  size: '1',
                  sort: 'id,desc',
                })
              ),
            },
          },
        ],
      })
        .overrideTemplate(ReponseUserComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ReponseUserComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ReponseUserService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ReponseUser(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.reponseUsers && comp.reponseUsers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ReponseUser(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.reponseUsers && comp.reponseUsers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      comp.ngOnInit();
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // INIT
      comp.ngOnInit();

      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
  });
});
