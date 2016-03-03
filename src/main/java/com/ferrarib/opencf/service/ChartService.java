package com.ferrarib.opencf.service;

import com.ferrarib.opencf.model.charts.*;
import com.ferrarib.opencf.repository.DailyBalances;
import com.ferrarib.opencf.repository.Registries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bruno on 3/1/16.
 */
@Service
public class ChartService {

    @Autowired
    private DailyBalances balances;

    @Autowired
    private Registries registries;

    public DailyBalanceWrapper dailyBalanceChart() {
        List<DailyBalance> result = balances.findTop15ByOrderByDateDesc();

        DailyBalanceWrapper wrapper = new DailyBalanceWrapper();
        wrapper.setDailyBalances(result);

        return wrapper;
    }

    public CategoryOutgoingWrapper categoryOutgoingChart() {
        List<Object[]> result = registries.findCategoryOutgoing();
        List<CategoryOutgoing> categoryOutgoings = new ArrayList<>();

        result.forEach(o -> {
            CategoryOutgoing outgoing = new CategoryOutgoing();
            outgoing.setAmount(new BigDecimal(String.valueOf(o[0])));
            outgoing.setCategoryName(String.valueOf(o[1]));

            categoryOutgoings.add(outgoing);
        });

        CategoryOutgoingWrapper wrapper = new CategoryOutgoingWrapper();
        wrapper.setCategoryOutgoings(categoryOutgoings);

        return wrapper;
    }

    public MonthlyBalanceWrapper monthlyBalanceChart() {
        List<Object[]> result = balances.findBalancePerMonth();
        List<MonthlyBalance> balanceList = new ArrayList<>();

        result.forEach(o -> {
            MonthlyBalance bpm = new MonthlyBalance();
            bpm.setBalance(new BigDecimal(o[0].toString()));
            bpm.setMonth(Integer.parseInt(o[1].toString()));

            balanceList.add(bpm);
        });

        MonthlyBalanceWrapper wrapper = new MonthlyBalanceWrapper();
        wrapper.setMonthlyBalances(balanceList);

        return wrapper;
    }

    public CategoryIncomingWrapper categoryIncomingChart() {
        List<Object[]> result = registries.findCategoryIncoming();
        List<CategoryIncoming> incomings = new ArrayList<>();

        result.forEach(o -> {
            CategoryIncoming incoming = new CategoryIncoming();
            incoming.setAmount(new BigDecimal(o[0].toString()));
            incoming.setCategoryName(o[1].toString());

            incomings.add(incoming);
        });

        CategoryIncomingWrapper wrapper = new CategoryIncomingWrapper();
        wrapper.setCategoryIncomings(incomings);

        return wrapper;
    }

//    public MonthlyBalanceWrapper fakeMonthlyBalanceChart() {
//        List<MonthlyBalance> result = new ArrayList<>();
//        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
//
//        for(int i = 0; i < months.length; i++) {
//            MonthlyBalance bal = new MonthlyBalance();
//            bal.setMonth(months[i]);
//            bal.setBalance(new BigDecimal(Math.random() * 10000));
//
//            result.add(bal);
//        }
//        MonthlyBalanceWrapper wrapper = new MonthlyBalanceWrapper();
//        wrapper.setMonthlyBalances(result);
//
//        return wrapper;
//    }


}
