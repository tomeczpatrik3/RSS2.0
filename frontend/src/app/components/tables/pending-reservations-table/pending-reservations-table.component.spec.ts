import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { PendingReservationsTableComponent } from './pending-reservations-table.component';


describe('PendingReservationsTableComponent', () => {
  let component: PendingReservationsTableComponent;
  let fixture: ComponentFixture<PendingReservationsTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PendingReservationsTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PendingReservationsTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
