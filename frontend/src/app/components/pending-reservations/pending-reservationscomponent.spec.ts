import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PendingReservationsComponent } from './pending-reservations.component';

describe('PendingReservationComponent', () => {
  let component: PendingReservationsComponent;
  let fixture: ComponentFixture<PendingReservationsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PendingReservationsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PendingReservationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
