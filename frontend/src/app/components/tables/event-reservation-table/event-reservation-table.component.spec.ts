import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EventReservationTableComponent } from './event-reservation-table.component';

describe('ReservationTableComponent', () => {
  let component: EventReservationTableComponent;
  let fixture: ComponentFixture<EventReservationTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EventReservationTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EventReservationTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
