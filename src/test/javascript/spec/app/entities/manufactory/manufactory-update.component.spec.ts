import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TpsoaTestModule } from '../../../test.module';
import { ManufactoryUpdateComponent } from 'app/entities/manufactory/manufactory-update.component';
import { ManufactoryService } from 'app/entities/manufactory/manufactory.service';
import { Manufactory } from 'app/shared/model/manufactory.model';

describe('Component Tests', () => {
  describe('Manufactory Management Update Component', () => {
    let comp: ManufactoryUpdateComponent;
    let fixture: ComponentFixture<ManufactoryUpdateComponent>;
    let service: ManufactoryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TpsoaTestModule],
        declarations: [ManufactoryUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ManufactoryUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ManufactoryUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ManufactoryService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Manufactory(123);
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
        const entity = new Manufactory();
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
