import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddSimpleReservationPageComponent } from './add-simple-reservation-page.component';

describe('AddSimpleReservationPageComponent', () => {
  let component: AddSimpleReservationPageComponent;
  let fixture: ComponentFixture<AddSimpleReservationPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddSimpleReservationPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddSimpleReservationPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
