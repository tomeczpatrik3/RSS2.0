import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddSemesterPageComponent } from './add-semester-page.component';

describe('AddSemesterPageComponent', () => {
  let component: AddSemesterPageComponent;
  let fixture: ComponentFixture<AddSemesterPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddSemesterPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddSemesterPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
