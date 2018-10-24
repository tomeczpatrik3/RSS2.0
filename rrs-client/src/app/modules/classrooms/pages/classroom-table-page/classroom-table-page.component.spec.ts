import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClassroomTablePageComponent } from './classroom-table-page.component';

describe('ClassroomTablePageComponent', () => {
  let component: ClassroomTablePageComponent;
  let fixture: ComponentFixture<ClassroomTablePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClassroomTablePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClassroomTablePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
