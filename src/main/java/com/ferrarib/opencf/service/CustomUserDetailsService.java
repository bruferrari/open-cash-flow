package com.ferrarib.opencf.service;

import com.ferrarib.opencf.model.User;
import com.ferrarib.opencf.repository.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by bruno on 3/11/16.
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private Users users;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.findOne(username);
        if(user != null) {
            return user;
        } throw new UsernameNotFoundException("User " + username + " has not found.");
    }
}
