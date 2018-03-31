import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailedReservationsComponent } from './detailed-reservations.component';

describe('DetailedReservationsComponent', () => {
  let component: DetailedReservationsComponent;
  let fixture: ComponentFixture<DetailedReservationsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetailedReservationsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailedReservationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
