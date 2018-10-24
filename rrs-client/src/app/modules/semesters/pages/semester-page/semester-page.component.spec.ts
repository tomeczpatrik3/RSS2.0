import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SemesterPageComponent } from './semester-page.component';

describe('SemesterPageComponent', () => {
  let component: SemesterPageComponent;
  let fixture: ComponentFixture<SemesterPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SemesterPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SemesterPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
