package com.subskill.service;

import com.subskill.models.User;
import com.subskill.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(), user.getPassword(), Collections.singleton(user.getRole()));
        } else {
            throw new UsernameNotFoundException("User doesn't exists");
        }
    }

}