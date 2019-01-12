package app.service;

import app.model.Authority;
import app.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class AuthorityService {

    @Autowired
    AuthorityRepository authorityRepository;

    @Transactional
    @Modifying
    public List<Authority> getAllAuthorities() {

        return authorityRepository.findAll();
    }
}
