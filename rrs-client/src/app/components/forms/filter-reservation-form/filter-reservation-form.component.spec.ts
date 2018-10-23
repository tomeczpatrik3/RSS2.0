import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FilterReservationFormComponent } from './filter-reservation-form.component';

describe('FilterReservationFormComponent', () => {
  let component: FilterReservationFormComponent;
  let fixture: ComponentFixture<FilterReservationFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FilterReservationFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FilterReservationFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
