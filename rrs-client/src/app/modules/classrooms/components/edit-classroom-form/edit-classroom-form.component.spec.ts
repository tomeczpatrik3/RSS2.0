import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditClassroomFormComponent } from './edit-classroom-form.component';

describe('EditClassroomFormComponent', () => {
  let component: EditClassroomFormComponent;
  let fixture: ComponentFixture<EditClassroomFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditClassroomFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditClassroomFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
