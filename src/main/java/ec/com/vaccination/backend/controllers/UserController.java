package ec.com.vaccination.backend.controllers;

import ec.com.vaccination.backend.dtos.UserLoginRequestDTO;
import ec.com.vaccination.backend.dtos.UserResponseDTO;
import ec.com.vaccination.backend.dtos.UserSignUpRequestDTO;
import ec.com.vaccination.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@Valid @RequestBody UserLoginRequestDTO userRequest, Errors errors) {

        return userService.login(userRequest, errors);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> signUp(@Valid @RequestBody UserSignUpRequestDTO userRequest, Errors errors) {

        return userService.signUp(userRequest, errors);
    }

}
