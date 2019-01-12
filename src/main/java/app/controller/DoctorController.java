package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.config.Validator;
import app.model.Doctor;
import app.repository.DoctorRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping(value = "/api/doctors/")
public class DoctorController {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    Validator validator;

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public Iterable<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Doctor getSingleDoctor(
            @PathVariable(value = "id") Integer doctorid
    ) {
        if (doctorRepository.findOne(doctorid) == null) {
            throw new RuntimeException("Nie ma lekarza o podanym id!");
        }
        return doctorRepository.findOne(doctorid);
    }

    @RequestMapping(value = "{lastname}", method = RequestMethod.GET)
    public Iterable<Doctor> getByLastname(
            @RequestParam(value = "lastname") String nazwisko
    ) {
        if (doctorRepository.findDoctorsByLastnameContaining(nazwisko).isEmpty()) {
            throw new RuntimeException("Nie ma lekarza o podanym nazwisku!");
        }
        return doctorRepository.findDoctorsByLastnameContaining(nazwisko);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void createDoctor(@RequestBody Doctor doctor) {
        if (!validator.isDoctorValid(doctor)) {
            throw new RuntimeException("Złe dane lekarza!");
        }
        doctorRepository.save(new Doctor(
                0,
                doctor.getFirstname(),
                doctor.getLastname()));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateDoctor(
            @PathVariable(value = "id") Integer doctorid,
            @RequestBody Doctor doctor) {
        if (doctorRepository.findOne(doctorid) == null) {
            throw new RuntimeException("Nie ma lekarza o podanym id!");
        }
        Doctor l = doctorRepository.findOne(doctorid);
        l.setFirstname(doctor.getFirstname());
        l.setLastname(doctor.getLastname());
        if (!validator.isDoctorValid(l)) {
            throw new RuntimeException("Złe dane lekarza!");
        }
        doctorRepository.save(l);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteDoctor(
            @PathVariable(value = "id") Integer doctorid
    ) {
        doctorRepository.delete(doctorid);
    }
}
