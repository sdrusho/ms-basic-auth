package org.basic.micorservice.controllers;

import org.basic.micorservice.Exception.CustomException;
import org.basic.micorservice.model.User;
import org.basic.micorservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/users")
@RestController
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
        List <User> users = userService.allUsers();

        return ResponseEntity.ok(users);
    }
}
