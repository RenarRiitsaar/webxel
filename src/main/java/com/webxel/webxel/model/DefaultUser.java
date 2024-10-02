package com.webxel.webxel.model;

import com.webxel.webxel.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DefaultUser {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    @Transactional
    public void checkUser(){
        User user = userRepository.findByUsername("USER");
        Set<Role> roles = new HashSet<>();
        Role role = new Role("USER");
        roles.add(role);

        if(user == null){
            user = new User();
            user.setUsername("USER");
            user.setPassword("$2a$12$TTkdaO5.uIJwBfIigSgGee9kafWT26iDR7GnfaZEvjXMwx6HbcCHi");
            user.setRoles(roles);
            user.setEnabled(true);
            userRepository.save(user);
        }
    }
    }
