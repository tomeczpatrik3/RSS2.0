import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddSemesterReservationComponent } from './add-semester-reservation.component';

describe('AddSemesterReservationComponent', () => {
  let component: AddSemesterReservationComponent;
  let fixture: ComponentFixture<AddSemesterReservationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddSemesterReservationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddSemesterReservationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
