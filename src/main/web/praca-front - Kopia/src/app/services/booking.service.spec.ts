import { TestBed, inject } from '@angular/core/testing';
import { BookingService } from './booking.service';

describe('BookingsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [BookingService]
    });
  });

  it('should be created', inject([BookingService], (service: BookingsService) => {
    expect(service).toBeTruthy();
  }));
});
