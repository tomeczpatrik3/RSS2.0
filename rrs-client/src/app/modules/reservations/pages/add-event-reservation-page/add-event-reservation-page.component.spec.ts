import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEventReservationPageComponent } from './add-event-reservation-page.component';

describe('AddEventReservationPageComponent', () => {
  let component: AddEventReservationPageComponent;
  let fixture: ComponentFixture<AddEventReservationPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddEventReservationPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddEventReservationPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
