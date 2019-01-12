import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PortalRezerwacjaComponent } from './portal-rezerwacja.component';

describe('PortalRezerwacjaComponent', () => {
  let component: PortalRezerwacjaComponent;
  let fixture: ComponentFixture<PortalRezerwacjaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PortalRezerwacjaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PortalRezerwacjaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
