import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddClassroomPageComponent } from './add-classroom-page.component';

describe('AddClassroomPageComponent', () => {
  let component: AddClassroomPageComponent;
  let fixture: ComponentFixture<AddClassroomPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddClassroomPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddClassroomPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
