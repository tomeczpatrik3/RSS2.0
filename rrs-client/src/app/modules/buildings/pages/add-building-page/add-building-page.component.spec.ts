import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddBuildingPageComponent } from './add-building-page.component';

describe('AddBuildingPageComponent', () => {
  let component: AddBuildingPageComponent;
  let fixture: ComponentFixture<AddBuildingPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddBuildingPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddBuildingPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
