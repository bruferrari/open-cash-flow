package com.ferrarib.opencf.service;

import com.ferrarib.opencf.model.User;
import com.ferrarib.opencf.repository.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by bruno on 3/14/16.
 */
@Service
public class UserService {

    private static final String DEFAULT_PASSWD = "opencf2016";

    @Autowired
    private Users users;

    private User userLogged = null;

    public void updateUserBCryptHash(String username) {
        User user = users.findOne(username);
        user.setPassword(DEFAULT_PASSWD);
        users.save(user);
    }

    public User getUserLoggedEntity() {
        userLogged = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User entityUser = users.findOne(userLogged.getEmail());
//        entityUser.addBlankPasswd();

        return entityUser;
    }

    public void flushAuthenticationToken(User user) {
        Authentication auth = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    public void updateBasicInformation(User user) {
        User principal = users.findOne(user.getEmail());
        user.setPassword(principal.getPassword());
        users.save(user);
    }

    public boolean resetPassword(User user) {
        if(!user.validatePasswordFields())
            return false;

        User principal = users.findOne(user.getEmail());
        boolean matchResult = new BCryptPasswordEncoder().matches(user.getPassword(), principal.getPassword());
        //check if both passwds are the same
        if(matchResult)
            try {
                // check retype passwd
                if (isPasswordMatch(user)) {
                    user.setFirstName(principal.getFirstName());
                    user.setLastName(principal.getLastName());

                    //encrypt passwd and save on database
                    String encryptedPassword = user.encryptPassword(user.getNewPassword());
                    user.setPassword(encryptedPassword);
                    users.save(user);
                    System.out.println("Password reseted for user -> " + user.getEmail());

                    return true;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        return false;
    }

    private boolean isPasswordMatch(User user) {
        return user.getNewPassword().equals(user.getRetypePassword()) ? true : false;
    }

    public boolean existsNewPasswd(User user) {
        return isPasswordMatch(user) && !user.getNewPassword().isEmpty() ? true : false;
    }
}
