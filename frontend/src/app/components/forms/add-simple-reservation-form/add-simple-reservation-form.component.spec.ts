import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddSimpleReservationFormComponent } from './add-simple-reservation-form.component';

describe('AddSimpleReservationComponent', () => {
  let component: AddSimpleReservationFormComponent;
  let fixture: ComponentFixture<AddSimpleReservationFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddSimpleReservationFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddSimpleReservationFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
