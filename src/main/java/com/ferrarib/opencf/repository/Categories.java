package com.ferrarib.opencf.repository;

import com.ferrarib.opencf.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bruno on 2/14/16.
 */
@Repository
public interface Categories extends JpaRepository<Category, Long> {

}
