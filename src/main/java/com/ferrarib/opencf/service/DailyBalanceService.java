package com.ferrarib.opencf.service;

import com.ferrarib.opencf.filter.RegistryFilter;
import com.ferrarib.opencf.model.Balance;
import com.ferrarib.opencf.model.Registry;
import com.ferrarib.opencf.model.DailyBalance;
import com.ferrarib.opencf.repository.DailyBalances;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.List;

/**
 * Created by bruno on 3/1/16.
 */
@Service
public class DailyBalanceService {

    @Autowired
    private DailyBalances balances;

    @Autowired
    private RegistryService registryService;

    @Scheduled(cron = "0 0/15 * * * *")
    public void saveDailyBalance() {
        DailyBalance dailyBalance = this.dailyBalanceProducer();
        System.out.println("Storing balance on database for " + Calendar.getInstance().getTime());
        balances.save(dailyBalance);
    }

    public DailyBalance dailyBalanceProducer() {
        List<Registry> result = registryService.filter(new RegistryFilter());
        Balance balance = new Balance(result);

        DailyBalance dailyBalance = new DailyBalance();

        dailyBalance.setDate(Calendar.getInstance().getTime());
        dailyBalance.setBalance(balance.getBalance());
        dailyBalance.setCredit(balance.getCredit());
        dailyBalance.setDebit(balance.getDebit());

        return dailyBalance;
    }

}
