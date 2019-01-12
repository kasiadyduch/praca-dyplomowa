package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.model.Booking;
import app.repository.BookingRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping(value = "/api/bookings/")
public class BookingController {

    @Autowired
    BookingRepository bookingRepository;

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public Iterable<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @RequestMapping(value = "{bookingid}", method = RequestMethod.GET)
    public Booking getSingleBooking(
            @PathVariable(value = "bookingid") Integer bookingid
    ) {
        if (bookingRepository.findOne(bookingid) == null) {
            throw new RuntimeException("Nie ma rezerwacji o podanym id!");
        }
        return bookingRepository.findOne(bookingid);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void createBooking(@RequestBody Booking booking) {
        bookingRepository.save(new Booking(
                0,
                booking.getUserid(),
                booking.getTypeid(),
                booking.getDate(),
                booking.getAttachmentpath()));
    }

    @RequestMapping(value = "{bookingid}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateBooking(
            @PathVariable(value = "bookingid") Integer bookingid,
            @RequestBody Booking booking
    ) {
        if (bookingRepository.findOne(bookingid) == null) {
            throw new RuntimeException("Nie ma rezerwacji o podanym id!");
        }
        Booking r = bookingRepository.findOne(bookingid);
        r.setUserid(booking.getUserid());
        r.setTypeid(booking.getTypeid());
        r.setDate(booking.getDate());
        r.setAttachmentpath(booking.getAttachmentpath());
        bookingRepository.save(r);

    }

    @RequestMapping(value = "{bookingid}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteBooking(
            @PathVariable(value = "bookingid") Integer bookingid
    ) {
        bookingRepository.delete(bookingid);
    }
}
