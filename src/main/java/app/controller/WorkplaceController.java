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

import app.config.Validator;
import app.model.Workplace;
import app.repository.WorkplaceRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping(value = "/api/workplaces/")
public class WorkplaceController {
    @Autowired
    WorkplaceRepository workplaceRepository;

    @Autowired
    Validator validator;

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public Iterable<Workplace> getAllWorkplaces() {
        return workplaceRepository.findAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Workplace getSingleWorkplace(
            @PathVariable(value = "id") Integer workplaceid
    ) {
        if (workplaceRepository.findOne(workplaceid) == null) {
            throw new RuntimeException("Nie ma zakladu o podanym id!");
        }
        return workplaceRepository.findOne(workplaceid);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void createWorkplace(@RequestBody Workplace workplace) {
        if (!validator.isWorkplaceValid(workplace)) {
            throw new RuntimeException("Złe dane zakładu!");
        }
        workplaceRepository.save(new Workplace(
                0,
                workplace.getName(),
                workplace.getNip(),
                workplace.getStreet(),
                workplace.getSnumber(),
                workplace.getPostcode(),
                workplace.getCity()));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteWorkplace(
            @PathVariable(value = "id") Integer id
    ) {
        workplaceRepository.delete(id);
    }
}
