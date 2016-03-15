package com.ferrarib.opencf.controller;

import com.ferrarib.opencf.model.User;
import com.ferrarib.opencf.repository.Users;
import com.ferrarib.opencf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by bruno on 3/14/16.
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private Users users;

    @Autowired
    private UserService service;

    private static final String PROFILE_VIEW = "UserProfile";

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView editUserProfile() {
        ModelAndView mv = new ModelAndView("UserProfile");
        User user = service.getUserLoggedEntity();
        mv.addObject("user", user);

        return mv;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String updateUserProfile(@Validated User user, Errors errors, RedirectAttributes attrb) {

        if(errors.hasErrors()) {
            return PROFILE_VIEW;
        }
        try {
            users.save(user);
            service.flushAuthenticationToken(user); // flushing modifications on auth credentials
            attrb.addFlashAttribute("message", "Profile modifications saved!");
            return "redirect:/profile";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
