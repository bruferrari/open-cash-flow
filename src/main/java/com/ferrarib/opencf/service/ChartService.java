package com.ferrarib.opencf.service;

import com.ferrarib.opencf.model.charts.DailyBalance;
import com.ferrarib.opencf.model.charts.DailyBalanceWrapper;
import com.ferrarib.opencf.model.charts.MonthlyBalance;
import com.ferrarib.opencf.model.charts.MonthlyBalanceWrapper;
import com.ferrarib.opencf.repository.DailyBalances;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bruno on 3/1/16.
 */
@Service
public class ChartService {

    @Autowired
    private DailyBalances balances;

    public DailyBalanceWrapper dailyBalanceChart() {
        List<DailyBalance> result = balances.findTop15ByOrderByDateDesc();

        DailyBalanceWrapper wrapper = new DailyBalanceWrapper();
        wrapper.setDailyBalances(result);

        return wrapper;
    }

//    public DailyBalanceWrapper dailyBalanceChart() {
//        List<DailyBalance> result = new ArrayList<>();
//        Calendar cal = Calendar.getInstance();
//
//        for(int i = 0; i < 15; i++) {
//            DailyBalance bal = new DailyBalance();
//
//            cal.add(Calendar.DATE, -1);
//
//            bal.setDate(cal.getTime());
//            bal.setBalance(BigDecimal.valueOf(Math.random() * 10000));
//            result.add(bal);
//        }
//        DailyBalanceWrapper wrapper = new DailyBalanceWrapper();
//        wrapper.setDailyBalances(result);
//        return wrapper;
//    }

    public MonthlyBalanceWrapper monthlyBalanceChart() {
        List<MonthlyBalance> result = new ArrayList<>();
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        for(int i = 0; i < months.length; i++) {
            MonthlyBalance bal = new MonthlyBalance();
            bal.setMonth(months[i]);
            bal.setBalance(Math.random() * 10000);

            result.add(bal);
        }
        MonthlyBalanceWrapper wrapper = new MonthlyBalanceWrapper();
        wrapper.setMonthlyBalances(result);

        return wrapper;
    }


}
