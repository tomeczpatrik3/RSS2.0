import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ObserveEventReservationFormComponent } from './observe-event-reservation-form.component';

describe('ObserveEventReservationFormComponent', () => {
  let component: ObserveEventReservationFormComponent;
  let fixture: ComponentFixture<ObserveEventReservationFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ObserveEventReservationFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ObserveEventReservationFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
