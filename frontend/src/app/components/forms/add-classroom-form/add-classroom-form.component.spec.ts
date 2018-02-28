import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddClassroomFormComponent } from './add-classroom-form.component';

describe('AddClassroomFormComponent', () => {
  let component: AddClassroomFormComponent;
  let fixture: ComponentFixture<AddClassroomFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddClassroomFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddClassroomFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
