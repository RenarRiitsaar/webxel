package com.webxel.webxel.service.impl;

import com.webxel.webxel.model.Role;
import com.webxel.webxel.dto.RegisterDTO;
import com.webxel.webxel.model.User;
import com.webxel.webxel.repository.RoleRepository;
import com.webxel.webxel.repository.UserRepository;
import com.webxel.webxel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    @Override
    public User signUp(RegisterDTO registerDTO) {
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setEmail(registerDTO.getEmail());
        user.setFirstName(registerDTO.getFirstName());
        user.setLastName(registerDTO.getLastName());
        user.setRoles(findByName("USER"));
        userRepository.save(user);

        return user;
    }

    @Override
    public Set<Role> findByName(String roleName) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(roleName));
        return roles;
    }

    @Override
    public boolean usernameExists(String username) {
        User user = userRepository.findByUsername(username);

        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String findByRoleId(Set<Role> roles) {

        String userRoleName = "";
        for (Role role : roles) {
            userRoleName = role.getName();
        }
        return userRoleName;
    }
}

