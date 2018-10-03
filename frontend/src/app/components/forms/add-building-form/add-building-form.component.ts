import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators,FormBuilder } from '@angular/forms';
import { BuildingService } from '../../../services/building.service';
import { DialogService } from '../../../services/dialog.service';
import { Building } from '../../../models/Building';
import { InfoDialogComponent } from '../../dialogs/info-dialog/info-dialog.component';

@Component({
  selector: 'app-add-building-form',
  templateUrl: './add-building-form.component.html',
  styleUrls: ['./add-building-form.component.css']
})
export class AddBuildingFormComponent implements OnInit {
  
  buildingName = new FormControl('', [
    Validators.required,
    Validators.minLength(3),
    Validators.maxLength(30)
  ]);

  buildingForm: FormGroup = this.builder.group({
    buildingName: this.buildingName,
  });

  constructor(
    private builder: FormBuilder,
    private buildingService: BuildingService,
    private dialogService: DialogService
  ) { }

  ngOnInit() {
  }

  formToSubject(): Building {
    return new Building(
      this.buildingForm.value.buildingName
    );
  }

  /*
    Feliratkozunk, majd:
    - hiba esetén jelzünk a hibát dialog segítségével
    - siker esetén jelezzük a sikert dialog segítségével
  */
  addBuilding() {
    this.buildingService.createBuilding(this.formToSubject()).subscribe(
      res => console.log(res),
      error => this.dialogService.openDialog("Épület hozzáadása:", this.dialogService.addBr(error.error), InfoDialogComponent),
      () => this.dialogService.openDialog("Épület hozzáadása:", "Épület sikeresen rögítve", InfoDialogComponent)
    );
    this.buildingForm.reset();
  }
}