import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditEventReservationFormComponent } from './edit-event-reservation-form.component';

describe('EditEventReservationFormComponent', () => {
  let component: EditEventReservationFormComponent;
  let fixture: ComponentFixture<EditEventReservationFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditEventReservationFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditEventReservationFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
