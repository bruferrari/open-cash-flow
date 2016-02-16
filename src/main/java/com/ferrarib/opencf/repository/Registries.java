package com.ferrarib.opencf.repository;

import com.ferrarib.opencf.model.Registry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by bruno on 2/10/16.
 */
@Repository
public interface Registries extends JpaRepository<Registry, Long> {

    @Modifying
    @Transactional
    @Query("select r from #{#entityName} r where r.date = CURRENT_DATE")
    List<Registry> findRegistriesByCurrentDate();

    @Modifying
    @Transactional
    @Query("select r from #{#entityName} r where r.date = CURRENT_DATE and r.title like ?1%")
    List<Registry> findByTitleContainingAndCurrentDate(String title);

    @Modifying
    @Transactional
    @Query("select r from #{#entityName} r where r.date between ?1 and ?2")
    List<Registry> findByDateBetween(Date from, Date to);

    List<Registry> findByTitleContaining(String title);
}
