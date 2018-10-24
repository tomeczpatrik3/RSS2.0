import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FilterEventsFormComponent } from './filter-events-form.component';

describe('FilterEventsFormComponent', () => {
  let component: FilterEventsFormComponent;
  let fixture: ComponentFixture<FilterEventsFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FilterEventsFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FilterEventsFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
