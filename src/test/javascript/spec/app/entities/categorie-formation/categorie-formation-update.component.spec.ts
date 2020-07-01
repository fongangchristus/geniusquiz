import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GeniusquizTestModule } from '../../../test.module';
import { CategorieFormationUpdateComponent } from 'app/entities/categorie-formation/categorie-formation-update.component';
import { CategorieFormationService } from 'app/entities/categorie-formation/categorie-formation.service';
import { CategorieFormation } from 'app/shared/model/categorie-formation.model';

describe('Component Tests', () => {
  describe('CategorieFormation Management Update Component', () => {
    let comp: CategorieFormationUpdateComponent;
    let fixture: ComponentFixture<CategorieFormationUpdateComponent>;
    let service: CategorieFormationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GeniusquizTestModule],
        declarations: [CategorieFormationUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CategorieFormationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CategorieFormationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CategorieFormationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CategorieFormation(123);
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
        const entity = new CategorieFormation();
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
