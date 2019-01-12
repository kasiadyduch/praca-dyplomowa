package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.model.Booking;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query("select count(b) from Booking b where b.date = ?1")
    Integer countBookingsByDateEquals(Date date);

    @Query("select count(b) from Booking b where extract(year from b.date) = ?1 and extract(month from b.date) = ?2")
    Integer xCountBookingsByYearAndMonth(Integer year, Integer month);
}
