import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddReservationFormComponent } from './add-reservation-form.component';

describe('AddReservationFormComponent', () => {
  let component: AddReservationFormComponent;
  let fixture: ComponentFixture<AddReservationFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddReservationFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddReservationFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
