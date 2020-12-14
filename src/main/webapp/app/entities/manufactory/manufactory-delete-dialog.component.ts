import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IManufactory } from 'app/shared/model/manufactory.model';
import { ManufactoryService } from './manufactory.service';

@Component({
  templateUrl: './manufactory-delete-dialog.component.html',
})
export class ManufactoryDeleteDialogComponent {
  manufactory?: IManufactory;

  constructor(
    protected manufactoryService: ManufactoryService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.manufactoryService.delete(id).subscribe(() => {
      this.eventManager.broadcast('manufactoryListModification');
      this.activeModal.close();
    });
  }
}
