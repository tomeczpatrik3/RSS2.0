import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditEventReservationDialogComponent } from './edit-event-reservation-dialog.component';

describe('EditEventReservationDialogComponent', () => {
  let component: EditEventReservationDialogComponent;
  let fixture: ComponentFixture<EditEventReservationDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditEventReservationDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditEventReservationDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
