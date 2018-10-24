import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddSemesterFormComponent } from './add-semester-form.component';

describe('AddSemesterFormComponent', () => {
  let component: AddSemesterFormComponent;
  let fixture: ComponentFixture<AddSemesterFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddSemesterFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddSemesterFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
