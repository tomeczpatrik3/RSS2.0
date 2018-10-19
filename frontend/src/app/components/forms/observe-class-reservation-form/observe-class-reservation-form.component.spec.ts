import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ObserveClassReservationFormComponent } from './observe-class-reservation-form.component';

describe('ObserveClassReservationFormComponent', () => {
  let component: ObserveClassReservationFormComponent;
  let fixture: ComponentFixture<ObserveClassReservationFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ObserveClassReservationFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ObserveClassReservationFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
