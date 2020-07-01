import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GeniusquizTestModule } from '../../../test.module';
import { ReponseUserUpdateComponent } from 'app/entities/reponse-user/reponse-user-update.component';
import { ReponseUserService } from 'app/entities/reponse-user/reponse-user.service';
import { ReponseUser } from 'app/shared/model/reponse-user.model';

describe('Component Tests', () => {
  describe('ReponseUser Management Update Component', () => {
    let comp: ReponseUserUpdateComponent;
    let fixture: ComponentFixture<ReponseUserUpdateComponent>;
    let service: ReponseUserService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GeniusquizTestModule],
        declarations: [ReponseUserUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ReponseUserUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ReponseUserUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ReponseUserService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ReponseUser(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new ReponseUser();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
