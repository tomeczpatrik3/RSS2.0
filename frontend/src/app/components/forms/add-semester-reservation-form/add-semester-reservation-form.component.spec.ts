import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddSemesterReservationFormComponent } from './add-semester-reservation-form.component';

describe('AddSemesterReservationComponent', () => {
  let component: AddSemesterReservationFormComponent;
  let fixture: ComponentFixture<AddSemesterReservationFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddSemesterReservationFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddSemesterReservationFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
