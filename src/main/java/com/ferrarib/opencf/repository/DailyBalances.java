package com.ferrarib.opencf.repository;

import com.ferrarib.opencf.model.DailyBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by bruno on 3/1/16.
 */
@Repository
public interface DailyBalances extends JpaRepository<DailyBalance, Date> {

    List<DailyBalance> findTop15ByOrderByDateDesc();

    @Modifying
    @Transactional
    @Query("select sum(db.balance), month(db.date) from #{#entityName} db group by month(db.date)")
    List<Object[]> findBalancePerMonth();
}
