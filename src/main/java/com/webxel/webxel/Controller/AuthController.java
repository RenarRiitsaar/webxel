package com.webxel.webxel.Controller;

import com.webxel.webxel.dto.AuthResponseDTO;
import com.webxel.webxel.dto.LoginDTO;
import com.webxel.webxel.dto.RegisterDTO;
import com.webxel.webxel.model.User;
import com.webxel.webxel.security.JwtGenerator;
import com.webxel.webxel.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private JwtGenerator jwtGenerator;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody RegisterDTO registerDTO) {


        if(userService.usernameExists(registerDTO.getUsername())){
            return new ResponseEntity<>( HttpStatus.NOT_ACCEPTABLE);
        }

        User user =  userService.signUp(registerDTO);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        User user = userService.findByUsername(loginDTO.getUsername());
        String roleName = userService.findByRoleId(user.getRoles());
        boolean isEnabled = user.isEnabled();


        return new ResponseEntity<>(new AuthResponseDTO(token, roleName, user.getId(),
                user.getUsername(), user.getEmail(),isEnabled), HttpStatus.OK);

    }
}