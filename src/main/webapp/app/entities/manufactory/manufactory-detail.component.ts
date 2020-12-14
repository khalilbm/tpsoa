import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IManufactory } from 'app/shared/model/manufactory.model';

@Component({
  selector: 'jhi-manufactory-detail',
  templateUrl: './manufactory-detail.component.html',
})
export class ManufactoryDetailComponent implements OnInit {
  manufactory: IManufactory | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ manufactory }) => (this.manufactory = manufactory));
  }

  previousState(): void {
    window.history.back();
  }
}
