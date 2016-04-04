package com.ferrarib.opencf.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by bruno on 3/11/16.
 */
@Entity
public class User implements UserDetails {

    @Id
    private String email;

//    @NotEmpty(message = "Password is required")
    private String password;

    @NotEmpty(message = "First name is required")
    private String firstName;

    @NotEmpty(message = "Last name is required")
    private String lastName;

    @Transient
    private String newPassword;

    @Transient
    private String retypePassword;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Role> roles = new ArrayList<>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRetypePassword() {
        return retypePassword;
    }

    public void setRetypePassword(String retypePassword) {
        this.retypePassword = retypePassword;
    }

    public List<Role> getRoles() {
        return roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getRoles();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void addBlankPasswd() {
        this.password = new String();
    }

    public String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    private boolean isPasswordValid() {
        return !(this.password == null || this.password.isEmpty());
    }

    private boolean isNewPasswordValid() {
        return !(this.newPassword == null || this.newPassword.isEmpty());
    }

    private boolean isRetypePasswordValid() {
        return !(this.retypePassword == null || this.retypePassword.isEmpty());
    }

    public boolean validatePasswordFields() {
        if(!isPasswordValid() || !this.isNewPasswordValid()
                || !this.isRetypePasswordValid())
            return false;
        else
            return true;
    }

}
