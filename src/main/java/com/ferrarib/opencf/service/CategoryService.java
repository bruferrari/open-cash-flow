package com.ferrarib.opencf.service;

import com.ferrarib.opencf.model.Category;
import com.ferrarib.opencf.repository.Categories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bruno on 2/14/16.
 */
@Service
public class CategoryService {

    @Autowired
    private Categories categories;

    public List<Category> findAll() {
        return categories.findAll();
    }

    public void save(Category category) {
        categories.save(category);
    }

    public void remove(Long id) {
        categories.delete(id);
    }
}
