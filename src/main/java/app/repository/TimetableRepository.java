package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Time;
import java.util.List;

import app.model.Timetable;

public interface TimetableRepository extends JpaRepository<Timetable, Integer> {

    List<Timetable> findTimetablesByDayofweek(Integer dayofweek);

    List<Timetable> findTimetablesByDayofweekAndDoctorid(Integer dayofweek, Integer doctorid);

    List<Timetable> findTimetablesByDoctorid(Integer doctorid);
}
