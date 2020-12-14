import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TpsoaTestModule } from '../../../test.module';
import { ManufactoryComponent } from 'app/entities/manufactory/manufactory.component';
import { ManufactoryService } from 'app/entities/manufactory/manufactory.service';
import { Manufactory } from 'app/shared/model/manufactory.model';

describe('Component Tests', () => {
  describe('Manufactory Management Component', () => {
    let comp: ManufactoryComponent;
    let fixture: ComponentFixture<ManufactoryComponent>;
    let service: ManufactoryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TpsoaTestModule],
        declarations: [ManufactoryComponent],
      })
        .overrideTemplate(ManufactoryComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ManufactoryComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ManufactoryService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Manufactory(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.manufactories && comp.manufactories[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
