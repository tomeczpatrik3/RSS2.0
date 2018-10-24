import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClassReservationPageComponent } from './class-reservation-page.component';

describe('ClassReservationPageComponent', () => {
  let component: ClassReservationPageComponent;
  let fixture: ComponentFixture<ClassReservationPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClassReservationPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClassReservationPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
