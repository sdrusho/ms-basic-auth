package org.basic.microservice.controllers;

import org.basic.microservice.exception.CustomException;
import org.basic.microservice.model.User;
import org.basic.microservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RequestMapping("/users")
@RestController
@Slf4j
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/current")
    public ResponseEntity<User> authenticatedUser() throws CustomException{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> allUsers() throws CustomException {
        log.info("Received request to list users");
        List <User> users = userService.allUsers();

        return ResponseEntity.ok(users);
    }
}
