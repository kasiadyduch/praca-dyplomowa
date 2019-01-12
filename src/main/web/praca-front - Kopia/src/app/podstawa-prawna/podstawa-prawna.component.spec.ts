import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PodstawaPrawnaComponent } from './podstawa-prawna.component';

describe('PodstawaPrawnaComponent', () => {
  let component: PodstawaPrawnaComponent;
  let fixture: ComponentFixture<PodstawaPrawnaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PodstawaPrawnaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PodstawaPrawnaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
