package app.controller;

import app.model.BookingDetails;
import app.repository.BookingDetailsRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping(value = "/api/booking-details/")

public class BookingDetailsController {

    @Autowired
    BookingDetailsRepository bookingDetailsRepository;

    @RequestMapping(value = "all", method = RequestMethod.GET)
//@PreAuthorize("hasRole('ADMIN')")
    public Iterable<BookingDetails> getAllBookingDetails() {
        return bookingDetailsRepository.findAll();
    }

    @RequestMapping(value = "{today}", method = RequestMethod.GET)
//@PreAuthorize("hasRole('ADMIN')")
    public Iterable<BookingDetails> getCurrentBookingDetailsByTodayDate(
            @PathVariable(value = "today") Date today) {
        return bookingDetailsRepository.findAllByDateEquals(today);
    }
}
