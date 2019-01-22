import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PortalArchiwumComponent } from './portal-archiwum.component';

describe('PortalArchiwumComponent', () => {
  let component: PortalArchiwumComponent;
  let fixture: ComponentFixture<PortalArchiwumComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PortalArchiwumComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PortalArchiwumComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
