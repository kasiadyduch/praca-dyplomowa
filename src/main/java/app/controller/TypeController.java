package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import app.model.Type;
import app.repository.TypeRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/api/types/")
public class TypeController {

    @Autowired
    TypeRepository typeRepository;

    @RequestMapping(value = "all")
    public Iterable<Type> getAllTypes() {
        return typeRepository.findAll();
    }

    @RequestMapping(value = "{typeid}", method = RequestMethod.GET)
    public Type GetSingleType(
            @PathVariable(value = "typeid") Integer typeid
    ) {
        if (typeRepository.findOne(typeid) == null) {
            throw new RuntimeException("Nie ma badania o podanym id!");
        }
        return typeRepository.findOne(typeid);
    }

}
