package com.ferrarib.opencf.controller;

import com.ferrarib.opencf.filter.RegistryFilter;
import com.ferrarib.opencf.model.Balance;
import com.ferrarib.opencf.model.Category;
import com.ferrarib.opencf.model.Registry;
import com.ferrarib.opencf.model.RegistryStatus;
import com.ferrarib.opencf.service.CategoryService;
import com.ferrarib.opencf.service.RegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Arrays;
import java.util.List;

/**
 * Created by bruno on 2/10/16.
 */
@Controller
@RequestMapping("/registries")
public class RegistryController {

    @Autowired
    private RegistryService service;

    @Autowired
    private CategoryService categoryService;

    private static final String NEW_REGISTER = "NewRegister";
    private static final String ALL = "AllRegistries";

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView registriesByCurDate(@ModelAttribute("filter") RegistryFilter filter) {
        ModelAndView mv = new ModelAndView(ALL);

        List<Registry> result = service.filter(filter);
        mv.addObject("registries", result);
        mv.addObject("balance", new Balance(result));

        return mv;
    }

    @RequestMapping("/new")
    public ModelAndView newRegistry() {
        ModelAndView mv = new ModelAndView(NEW_REGISTER);
        mv.addObject(new Registry());

        return mv;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String save(@Validated Registry registry, Errors errors, RedirectAttributes attrb) {

        if(errors.hasErrors()) {
            return NEW_REGISTER;
        }
        try {
            service.save(registry);
            attrb.addFlashAttribute("message", registry.getTitle() + " has been stored with success!");
            return "redirect:/registries/new";
        } catch (DataIntegrityViolationException e) {
            errors.rejectValue("data", null, "Invalid date format");
            return NEW_REGISTER;
        }
    }

    @ModelAttribute("statuses")
    public List<RegistryStatus> registryStatuses() {
        return Arrays.asList(RegistryStatus.values());
    }

    @ModelAttribute("categories")
    public List<Category> getCategories() {
        return categoryService.findAll();
    }

}
