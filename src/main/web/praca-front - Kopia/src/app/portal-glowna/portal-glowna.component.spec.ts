import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PortalGlownaComponent } from './portal-glowna.component';

describe('PortalGlownaComponent', () => {
  let component: PortalGlownaComponent;
  let fixture: ComponentFixture<PortalGlownaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PortalGlownaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PortalGlownaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
