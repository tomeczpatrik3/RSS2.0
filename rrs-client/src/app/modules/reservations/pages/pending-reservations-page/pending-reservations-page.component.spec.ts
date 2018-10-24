import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PendingReservationsPageComponent } from './pending-reservations-page.component';

describe('PendingReservationsPageComponent', () => {
  let component: PendingReservationsPageComponent;
  let fixture: ComponentFixture<PendingReservationsPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PendingReservationsPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PendingReservationsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
