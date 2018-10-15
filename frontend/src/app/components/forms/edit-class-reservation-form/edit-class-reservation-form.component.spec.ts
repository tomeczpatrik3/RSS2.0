import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditClassReservationFormComponent } from './edit-class-reservation-form.component';

describe('EditClassReservationFormComponent', () => {
  let component: EditClassReservationFormComponent;
  let fixture: ComponentFixture<EditClassReservationFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditClassReservationFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditClassReservationFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
