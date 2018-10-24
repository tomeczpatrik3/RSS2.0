import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EventReservationPageComponent } from './event-reservation-page.component';

describe('EventReservationPageComponent', () => {
  let component: EventReservationPageComponent;
  let fixture: ComponentFixture<EventReservationPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EventReservationPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EventReservationPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
