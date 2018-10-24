import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEventReservationFormComponent } from './add-event-reservation-form.component';

describe('AddEventReservationComponent', () => {
  let component: AddEventReservationFormComponent;
  let fixture: ComponentFixture<AddEventReservationFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddEventReservationFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddEventReservationFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
