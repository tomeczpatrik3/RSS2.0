import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddSimpleReservationComponent } from './add-simple-reservation.component';

describe('AddSimpleReservationComponent', () => {
  let component: AddSimpleReservationComponent;
  let fixture: ComponentFixture<AddSimpleReservationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddSimpleReservationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddSimpleReservationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
