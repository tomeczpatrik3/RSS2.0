import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditSemesterFormComponent } from './edit-semester-form.component';

describe('EditSemesterFormComponent', () => {
  let component: EditSemesterFormComponent;
  let fixture: ComponentFixture<EditSemesterFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditSemesterFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditSemesterFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
