package com.example.sweater.services;

import com.example.sweater.domains.Role;
import com.example.sweater.domains.User;
import com.example.sweater.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final MailSender mailSender;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public boolean createUser(User user) {
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb != null) {
            return false;
        }

        user.setActive(false);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        userRepository.save(user);


        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                "Hello, %s! \n" +
                "Welcome to Sweater =)" +
                "Please, visit next link http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );

            mailSender.send(user.getEmail(), user.getActivationCode(), message);
        }

        return true;
    }

    public boolean activate(String code) {
        User user = userRepository.findByActivationCode(code);

        if (user != null) {

            user.setActivationCode(null);
            user.setActive(true);
            userRepository.save(user);

            return true;
        }

        return false;
    }
}
