package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import app.model.Timetable;
import app.repository.TimetableRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping(value = "/api/timetable/")
public class TimetableController {

    @Autowired
    TimetableRepository timetableRepository;

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public Iterable<Timetable> getAllTimetable() {
        return timetableRepository.findAll();
    }

    @RequestMapping(value = "{doctorid}", method = RequestMethod.GET)
    public Iterable<Timetable> getByDoctorid(
            @PathVariable(value = "doctorid") Integer doctorid
    ) {
        if (timetableRepository.findTimetablesByDoctorid(doctorid).isEmpty()) {
            throw new RuntimeException("Nie ma lekarza o podanym id!");
        }
        return timetableRepository.findTimetablesByDoctorid(doctorid);
    }

    @RequestMapping(value = "{dayofweek}", method = RequestMethod.GET)
    public Iterable<Timetable> getByDayofweek(
            @PathVariable(value = "dayofweek") Integer dayofweek
    ) {
        if (timetableRepository.findTimetablesByDayofweek(dayofweek).isEmpty()) {
            throw new RuntimeException("Nie ma przyjęć danego dnia!");
        }
        return timetableRepository.findTimetablesByDayofweek(dayofweek);
    }

}
