package org.basic.micorservice.service;

import org.basic.micorservice.dtos.LoginUserDto;
import org.basic.micorservice.dtos.RegisterUserDto;
import org.basic.micorservice.model.User;
import org.basic.micorservice.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input) {
        User user = new User();
        user.setUserName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setUserId(Long.parseLong("201"));
        user.setRoleId(Long.parseLong("1"));
        try{
            User created = userRepository.save(user);
            return created;
        }catch (Exception ex){
            return null;
        }

    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        User usr = userRepository.findByEmail(input.getEmail())
                .orElse(null);

        return usr;
    }
}
