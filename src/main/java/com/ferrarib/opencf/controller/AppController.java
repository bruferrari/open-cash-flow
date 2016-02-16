package com.ferrarib.opencf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by bruno on 2/10/16.
 */
@Controller
public class AppController {

    @RequestMapping("/welcome")
    public ModelAndView hello() {
        ModelAndView mv = new ModelAndView("Welcome");
        return mv;
    }

}
