import { Injectable } from '@angular/core';
import { CanDeactivate } from '@angular/router/src/interfaces';
import { AddClassroomFormComponent } from '../components/forms/add-classroom-form/add-classroom-form.component';


@Injectable()
export class FormGuardService implements CanDeactivate<AddClassroomFormComponent> {  
    constructor(
    ) {}
  
    canDeactivate(component: AddClassroomFormComponent) {
        return false;
    }

}