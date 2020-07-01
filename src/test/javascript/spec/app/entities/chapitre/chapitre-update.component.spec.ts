import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GeniusquizTestModule } from '../../../test.module';
import { ChapitreUpdateComponent } from 'app/entities/chapitre/chapitre-update.component';
import { ChapitreService } from 'app/entities/chapitre/chapitre.service';
import { Chapitre } from 'app/shared/model/chapitre.model';

describe('Component Tests', () => {
  describe('Chapitre Management Update Component', () => {
    let comp: ChapitreUpdateComponent;
    let fixture: ComponentFixture<ChapitreUpdateComponent>;
    let service: ChapitreService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GeniusquizTestModule],
        declarations: [ChapitreUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ChapitreUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ChapitreUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ChapitreService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Chapitre(123);
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
        const entity = new Chapitre();
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
