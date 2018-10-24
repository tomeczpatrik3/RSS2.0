import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BuildingTablePageComponent } from './building-table-page.component';

describe('BuildingTablePageComponent', () => {
  let component: BuildingTablePageComponent;
  let fixture: ComponentFixture<BuildingTablePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BuildingTablePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BuildingTablePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
