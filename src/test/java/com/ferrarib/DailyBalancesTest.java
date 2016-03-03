package com.ferrarib;

import com.ferrarib.opencf.model.charts.BalancePerMonth;
import com.ferrarib.opencf.repository.DailyBalances;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bruno on 3/3/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OpencfApplication.class)
@WebAppConfiguration
public class DailyBalancesTest {

    @Autowired
    private DailyBalances repository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void mustReturnListOfBalancesPerMonth() {
        List<Object[]> result = repository.findBalancePerMonth();
        List<BalancePerMonth> bpmList = new ArrayList<>();

        result.forEach(o -> {
            BalancePerMonth bpm = new BalancePerMonth();
            bpm.setBalance(new BigDecimal(o[0].toString()));
            bpm.setMonth(Integer.parseInt(o[1].toString()));

            bpmList.add(bpm);
        });

        bpmList.forEach(bpm -> {
            System.out.println(bpm.getBalance());
            System.out.println(bpm.getMonth());
        });
    }

}
