package com.ferrarib.opencf.model.charts;

import java.math.BigDecimal;

/**
 * Created by bruno on 3/1/16.
 */
public class MonthlyBalance {

    private Integer month;
    private BigDecimal balance;

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
