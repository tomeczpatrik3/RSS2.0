import { Injectable } from '@angular/core';
import { Router, ActivatedRouteSnapshot } from '@angular/router';
import { AuthService } from '../authentication/auth.service';
import { CanDeactivate } from '@angular/router/src/interfaces';
import { AddClassroomFormComponent } from '../components/classroom/add-classroom-form/add-classroom-form.component';
import { DialogService } from '../services/dialog.service';
import { InfoDialogComponent } from '../components/dialogs/info-dialog/info-dialog.component';
import { QuestionDialogComponent } from '../components/dialogs/question-dialog/question-dialog.component';
import { MatDialog } from '@angular/material';


@Injectable()
export class FormGuardService implements CanDeactivate<AddClassroomFormComponent> {  
    constructor(
    ) {}
  
    canDeactivate(component: AddClassroomFormComponent) {
        return true;
    }

}