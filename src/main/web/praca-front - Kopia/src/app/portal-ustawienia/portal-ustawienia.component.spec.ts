import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PortalUstawieniaComponent } from './portal-ustawienia.component';

describe('PortalUstawieniaComponent', () => {
  let component: PortalUstawieniaComponent;
  let fixture: ComponentFixture<PortalUstawieniaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PortalUstawieniaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PortalUstawieniaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
