import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'owner',
        loadChildren: () => import('./owner/owner.module').then(m => m.TpsoaOwnerModule),
      },
      {
        path: 'car',
        loadChildren: () => import('./car/car.module').then(m => m.TpsoaCarModule),
      },
      {
        path: 'manufactory',
        loadChildren: () => import('./manufactory/manufactory.module').then(m => m.TpsoaManufactoryModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class TpsoaEntityModule {}
