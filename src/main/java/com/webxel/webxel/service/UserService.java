package com.webxel.webxel.service;


import com.webxel.webxel.model.Role;
import com.webxel.webxel.dto.RegisterDTO;
import com.webxel.webxel.model.User;
import java.util.Set;

public interface UserService {

    User findByUsername(String username);


    User signUp(RegisterDTO registerDTO);

    Set<Role> findByName(String roleName);

    boolean usernameExists(String username);

    String findByRoleId(Set<Role> roles);

}


