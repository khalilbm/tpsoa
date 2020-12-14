import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IManufactory, Manufactory } from 'app/shared/model/manufactory.model';
import { ManufactoryService } from './manufactory.service';
import { ManufactoryComponent } from './manufactory.component';
import { ManufactoryDetailComponent } from './manufactory-detail.component';
import { ManufactoryUpdateComponent } from './manufactory-update.component';

@Injectable({ providedIn: 'root' })
export class ManufactoryResolve implements Resolve<IManufactory> {
  constructor(private service: ManufactoryService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IManufactory> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((manufactory: HttpResponse<Manufactory>) => {
          if (manufactory.body) {
            return of(manufactory.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Manufactory());
  }
}

export const manufactoryRoute: Routes = [
  {
    path: '',
    component: ManufactoryComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Manufactories',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ManufactoryDetailComponent,
    resolve: {
      manufactory: ManufactoryResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Manufactories',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ManufactoryUpdateComponent,
    resolve: {
      manufactory: ManufactoryResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Manufactories',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ManufactoryUpdateComponent,
    resolve: {
      manufactory: ManufactoryResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Manufactories',
    },
    canActivate: [UserRouteAccessService],
  },
];
