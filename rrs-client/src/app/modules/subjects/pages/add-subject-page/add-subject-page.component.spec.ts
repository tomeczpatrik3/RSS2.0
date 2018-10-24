import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddSubjectPageComponent } from './add-subject-page.component';

describe('AddSubjectPageComponent', () => {
  let component: AddSubjectPageComponent;
  let fixture: ComponentFixture<AddSubjectPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddSubjectPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddSubjectPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
