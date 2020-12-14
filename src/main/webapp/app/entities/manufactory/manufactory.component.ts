import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IManufactory } from 'app/shared/model/manufactory.model';
import { ManufactoryService } from './manufactory.service';
import { ManufactoryDeleteDialogComponent } from './manufactory-delete-dialog.component';

@Component({
  selector: 'jhi-manufactory',
  templateUrl: './manufactory.component.html',
})
export class ManufactoryComponent implements OnInit, OnDestroy {
  manufactories?: IManufactory[];
  eventSubscriber?: Subscription;

  constructor(
    protected manufactoryService: ManufactoryService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.manufactoryService.query().subscribe((res: HttpResponse<IManufactory[]>) => (this.manufactories = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInManufactories();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IManufactory): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInManufactories(): void {
    this.eventSubscriber = this.eventManager.subscribe('manufactoryListModification', () => this.loadAll());
  }

  delete(manufactory: IManufactory): void {
    const modalRef = this.modalService.open(ManufactoryDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.manufactory = manufactory;
  }
}
