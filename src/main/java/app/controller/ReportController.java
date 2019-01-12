package app.controller;

import app.repository.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping(value = "/api/report/")
public class ReportController {

    @Autowired
    BookingRepository bookingRepository;

    @RequestMapping(value = "week/{from}/{to}", method = RequestMethod.GET)
    public ArrayList<Integer> getWeekReport(
            @PathVariable(value = "from") Date from,
            @PathVariable(value = "to") Date to
    ) {
        int countDaysBetween = Math.toIntExact((to.getTime() - from.getTime()) / 86400000);
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i <= countDaysBetween; i++) {
            Date date = new Date(from.getTime() + 86400000 * i);
            int countBookings = bookingRepository.countBookingsByDateEquals(date);
            result.add(countBookings);
        }
        return result;
    }

    @RequestMapping(value = "month/{year}/{month}", method = RequestMethod.GET)
    public ArrayList<Integer> getMonthReport(
            @PathVariable(value = "year") Integer year,
            @PathVariable(value = "month") Integer month
    ) {
        ArrayList<Integer> result = new ArrayList<>();
        YearMonth yearMonth = YearMonth.of(year, month);
        int daysOfmonth = yearMonth.lengthOfMonth();
        for (int i = 0; i < daysOfmonth ; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month - 1);
            calendar.set(Calendar.DAY_OF_MONTH, i + 1);
            Date date = new Date(calendar.getTimeInMillis());
            int countBookings = bookingRepository.countBookingsByDateEquals(date);
            result.add(countBookings);
        }
        return result;
    }

    @RequestMapping(value = "year/{year}", method = RequestMethod.GET)
    public ArrayList<Integer> getYearReport(
            @PathVariable(value = "year") Integer year
    ) {
       ArrayList<Integer> result = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            int countBookings = bookingRepository.xCountBookingsByYearAndMonth(year, i);
            result.add(countBookings);
        }
        return result;
    }
}
