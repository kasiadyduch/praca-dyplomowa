package app.service;

import app.model.User;
import app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    @Modifying
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Modifying
    public User getUserByID(Integer id) {
        return userRepository.findOne(id);
    }
}
