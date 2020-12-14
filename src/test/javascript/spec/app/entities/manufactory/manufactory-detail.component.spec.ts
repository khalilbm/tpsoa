import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TpsoaTestModule } from '../../../test.module';
import { ManufactoryDetailComponent } from 'app/entities/manufactory/manufactory-detail.component';
import { Manufactory } from 'app/shared/model/manufactory.model';

describe('Component Tests', () => {
  describe('Manufactory Management Detail Component', () => {
    let comp: ManufactoryDetailComponent;
    let fixture: ComponentFixture<ManufactoryDetailComponent>;
    const route = ({ data: of({ manufactory: new Manufactory(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TpsoaTestModule],
        declarations: [ManufactoryDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ManufactoryDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ManufactoryDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load manufactory on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.manufactory).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
