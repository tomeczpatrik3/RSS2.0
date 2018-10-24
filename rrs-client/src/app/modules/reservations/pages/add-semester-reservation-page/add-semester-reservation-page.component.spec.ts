import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddSemesterReservationPageComponent } from './add-semester-reservation-page.component';

describe('AddSemesterReservationPageComponent', () => {
  let component: AddSemesterReservationPageComponent;
  let fixture: ComponentFixture<AddSemesterReservationPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddSemesterReservationPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddSemesterReservationPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
