package com.ferrarib.opencf.repository;

import com.ferrarib.opencf.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bruno on 3/11/16.
 */
@Repository
public interface Users extends JpaRepository<User, String> {

}
