import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditBuildingFormComponent } from './edit-building-form.component';

describe('EditBuildingFormComponent', () => {
  let component: EditBuildingFormComponent;
  let fixture: ComponentFixture<EditBuildingFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditBuildingFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditBuildingFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
