import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClassReservationTableComponent } from './class-reservation-table.component';

describe('ReservationTableComponent', () => {
  let component: ClassReservationTableComponent;
  let fixture: ComponentFixture<ClassReservationTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClassReservationTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClassReservationTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
