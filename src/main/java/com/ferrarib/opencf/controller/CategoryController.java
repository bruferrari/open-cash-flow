package com.ferrarib.opencf.controller;

import com.ferrarib.opencf.model.Category;
import com.ferrarib.opencf.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by bruno on 2/18/16.
 */
@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getCategories() {
        ModelAndView mv = new ModelAndView("ListCategories");
        List<Category> result = service.findAll();
        mv.addObject("categories", result);
        mv.addObject("category", new Category());

        return mv;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String save(@Validated Category category, Errors errors, RedirectAttributes attr) {

        if(errors.hasErrors()) {
            return "redirect:/categories";
        }

        service.save(category);
        attr.addFlashAttribute("message", category.getDescription() + " has been added with success!");
        return "redirect:/categories";

    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") Category category) {
        ModelAndView mv = new ModelAndView("ListCategories");
        mv.addObject("category", category);

        return mv;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public String remove(@PathVariable Long id, RedirectAttributes attr) {
        service.remove(id);
        attr.addFlashAttribute("message", "Category has been removed with success!");

        return "redirect:/categories";
    }
}
