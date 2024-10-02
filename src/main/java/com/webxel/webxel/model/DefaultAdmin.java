package com.webxel.webxel.model;

import com.webxel.webxel.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DefaultAdmin {

    @Autowired
    private UserRepository userRepository;


    @PostConstruct
    @Transactional
    public void checkAdmin(){
        User admin = userRepository.findByUsername("ADMIN");
        Set<Role> roles = new HashSet<>();
        Role role = new Role("ADMIN");
        roles.add(role);

        if(admin == null){
            admin = new User();
            admin.setUsername("ADMIN");
            admin.setPassword("$2a$12$TTkdaO5.uIJwBfIigSgGee9kafWT26iDR7GnfaZEvjXMwx6HbcCHi");
            admin.setRoles(roles);
            admin.setEnabled(true);
            userRepository.save(admin);
        }
    }
}