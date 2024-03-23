package com.subskill.service.impl;

import com.subskill.exception.UserNotFoundException;
import com.subskill.models.User;
import com.subskill.repository.UserRepository;
import com.subskill.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

import static com.subskill.api.ServiceExceptionMessages.USER_NOT_FOUND;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;


    @Transactional()
    @Override
    public UserDetails loadUserByUsername(String email)  {
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(user.getRole().name()))
        );
    }

}