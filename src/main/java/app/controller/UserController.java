package app.controller;

import app.model.Authority;
import org.omg.SendingContext.RunTime;
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
import app.hash.PasswordEncoder;
import app.model.User;
import app.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static app.model.UserRoleName.ROLE_PATIENT;
import static app.model.UserRoleName.ROLE_USER;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping(value = "/api/users/")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Validator validator;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public Iterable<User> getAllUsers() {

        List<User> result = userRepository.findAll();
        for (User u : result) {
            u.setPassword("");
        }
        return result;
    }

    @RequestMapping(value = "userid/{userid}", method = RequestMethod.GET)
    public User getSingleUser(
            @PathVariable(value = "userid") Integer userid
    ) {
        if (userRepository.findOne(userid) == null) {
            throw new RuntimeException("Nie ma usera o podanym id!");
        }
        User result = userRepository.findOne(userid);
        result.setPassword("");
        return result;
    }

    @RequestMapping(value = "lastname/{lastname}", method = RequestMethod.GET)
    public Iterable<User> getUserByLastname(
            @PathVariable(value = "lastname") String lastname
    ) {
        if (userRepository.findUsersByLastnameContaining(lastname).isEmpty()) {
            throw new RuntimeException("Nie ma usera o podanym nazwisku!");
        }
        List<User> result = userRepository.findUsersByLastnameContaining(lastname);
        for(User u : result) {
            u.setPassword("");
        }
        return result;
    }

    @RequestMapping(value = "pesel/{pesel}", method = RequestMethod.GET)
    public User getUserByPesel(
            @PathVariable(value = "pesel") String pesel
    ) {
        if (userRepository.findUserByPeselContaining(pesel) == null) {
            throw new RuntimeException("Nie ma usera o podanym peselu!");
        }
        User result = userRepository.findUserByPeselContaining(pesel);
        result.setPassword("");
        return result;
    }

    @RequestMapping(value = "email/{email}", method = RequestMethod.GET)
    public Integer getUserIdByEmail(
            @RequestParam(value = "email") String email
    ) {
        if (userRepository.findUserByEmail(email) == null) {
            throw new RuntimeException("Nie ma usera o podanym email!");
        }
        User user = userRepository.findUserByEmail(email);
        return user.getId();
    }

    @RequestMapping(value = "mpAdmin", method = RequestMethod.GET)
    public List<User> getMpAdminUsers() {
        Iterable<User> allUsers = userRepository.findAll();
        List<User> result = new ArrayList<>();
        for (User u : allUsers) {
            for (Authority a : u.getAuthorities()) {
                if (a.getName() == ROLE_USER) {
                    result.add(u);
                }
            }
        }
        return result;
    }

    @RequestMapping(value = "patients", method = RequestMethod.GET)
    public List<User> getPatients() {
        Iterable<User> allUsers = userRepository.findAll();
        List<User> result = new ArrayList<>();
        for (User u : allUsers) {
            for (Authority a : u.getAuthorities()) {
                if (a.getName() == ROLE_PATIENT) {
                    result.add(u);
                }
            }
        }
        return result;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void createUser(@RequestBody User user) {
        if (!validator.isUserValid(user)) {
            throw new RuntimeException("Złe dane usera!");
        }
        userRepository.save(new User(
                0,
                user.getFirstname(),
                user.getLastname(),
                user.getPesel(),
                user.getPhone(),
                user.getStreet(),
                user.getSnumber(),
                user.getBnumber(),
                user.getPostcode(),
                user.getCity(),
                user.getEmail(),
                passwordEncoder.hashPassword(user.getPassword()),
                user.getWorkplaceid(),
                user.getEnabled(),
                user.getLastpasswordresetdate(),
                user.getAuthorities()));
    }

    @RequestMapping(value = "{userid}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(
            @PathVariable(value = "userid") Integer userid,
            @RequestBody User user
    ) {
        if (userRepository.findOne(userid) == null) {
            throw new RuntimeException("Nie ma usera o podanym id!");
        }
        User u = userRepository.findOne(userid);
        u.setFirstname(user.getFirstname());
        u.setLastname(user.getLastname());
        u.setPesel(user.getPesel());
        u.setPhone(user.getPhone());
        u.setStreet(user.getStreet());
        u.setSnumber(user.getSnumber());
        u.setBnumber(user.getBnumber());
        u.setPostcode(user.getPostcode());
        u.setCity(user.getCity());
        u.setEmail(user.getEmail());
        if (!passwordEncoder.checkPassword(user.getPassword(), u.getPassword())) {
            u.setPassword(passwordEncoder.hashPassword(user.getPassword()));
            long milis = System.currentTimeMillis();
            u.setLastpasswordresetdate(new java.sql.Date(milis));
        }
        u.setWorkplaceid(user.getWorkplaceid());
        if (!validator.isUserValid(u)) {
            throw new RuntimeException("Złe dane usera");
        }
        userRepository.save(u);
    }

    @RequestMapping(value = "{userid}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(
            @PathVariable(value = "userid") Integer userid
    ) {
        userRepository.delete(userid);
    }

    @RequestMapping(value = "check", method = RequestMethod.POST)
    public boolean checkPassword(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "pass") String pass
    ) {
        User user = userRepository.findUserByEmail(email);
        String hashedPass = user.getPassword();
        return passwordEncoder.checkPassword(pass, hashedPass);
    }
}
