package com.ferrarib.opencf.model.charts;

import java.math.BigDecimal;

/**
 * Created by bruno on 3/3/16.
 */
public class BalancePerMonth {

    private BigDecimal balance;
    private Integer month;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BalancePerMonth that = (BalancePerMonth) o;

        if (balance != null ? !balance.equals(that.balance) : that.balance != null) return false;
        return month != null ? month.equals(that.month) : that.month == null;

    }

    @Override
    public int hashCode() {
        int result = balance != null ? balance.hashCode() : 0;
        result = 31 * result + (month != null ? month.hashCode() : 0);
        return result;
    }
}
