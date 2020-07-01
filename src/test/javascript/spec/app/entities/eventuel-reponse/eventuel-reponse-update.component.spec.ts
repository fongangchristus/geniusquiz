import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GeniusquizTestModule } from '../../../test.module';
import { EventuelReponseUpdateComponent } from 'app/entities/eventuel-reponse/eventuel-reponse-update.component';
import { EventuelReponseService } from 'app/entities/eventuel-reponse/eventuel-reponse.service';
import { EventuelReponse } from 'app/shared/model/eventuel-reponse.model';

describe('Component Tests', () => {
  describe('EventuelReponse Management Update Component', () => {
    let comp: EventuelReponseUpdateComponent;
    let fixture: ComponentFixture<EventuelReponseUpdateComponent>;
    let service: EventuelReponseService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GeniusquizTestModule],
        declarations: [EventuelReponseUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(EventuelReponseUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EventuelReponseUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EventuelReponseService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EventuelReponse(123);
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
        const entity = new EventuelReponse();
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
