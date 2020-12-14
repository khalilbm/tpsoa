import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IManufactory, Manufactory } from 'app/shared/model/manufactory.model';
import { ManufactoryService } from './manufactory.service';

@Component({
  selector: 'jhi-manufactory-update',
  templateUrl: './manufactory-update.component.html',
})
export class ManufactoryUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
  });

  constructor(protected manufactoryService: ManufactoryService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ manufactory }) => {
      this.updateForm(manufactory);
    });
  }

  updateForm(manufactory: IManufactory): void {
    this.editForm.patchValue({
      id: manufactory.id,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const manufactory = this.createFromForm();
    if (manufactory.id !== undefined) {
      this.subscribeToSaveResponse(this.manufactoryService.update(manufactory));
    } else {
      this.subscribeToSaveResponse(this.manufactoryService.create(manufactory));
    }
  }

  private createFromForm(): IManufactory {
    return {
      ...new Manufactory(),
      id: this.editForm.get(['id'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IManufactory>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
