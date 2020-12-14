import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TpsoaSharedModule } from 'app/shared/shared.module';
import { ManufactoryComponent } from './manufactory.component';
import { ManufactoryDetailComponent } from './manufactory-detail.component';
import { ManufactoryUpdateComponent } from './manufactory-update.component';
import { ManufactoryDeleteDialogComponent } from './manufactory-delete-dialog.component';
import { manufactoryRoute } from './manufactory.route';

@NgModule({
  imports: [TpsoaSharedModule, RouterModule.forChild(manufactoryRoute)],
  declarations: [ManufactoryComponent, ManufactoryDetailComponent, ManufactoryUpdateComponent, ManufactoryDeleteDialogComponent],
  entryComponents: [ManufactoryDeleteDialogComponent],
})
export class TpsoaManufactoryModule {}
