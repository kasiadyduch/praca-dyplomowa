package app.repository;

import app.model.BookingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface BookingDetailsRepository extends JpaRepository<BookingDetails, Integer> {

    @Query("select bd from BookingDetails bd where bd.date = ?1")
    List<BookingDetails> findAllByDateEquals(Date date);

    @Query("select bd from BookingDetails bd where bd.user_id = ?1")
    List<BookingDetails> findAllByUser_id(Integer user_id);
}
