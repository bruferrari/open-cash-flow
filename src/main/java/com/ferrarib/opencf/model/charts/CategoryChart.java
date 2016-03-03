package com.ferrarib.opencf.model.charts;

import java.math.BigDecimal;

/**
 * Created by bruno on 3/3/16.
 */
public abstract class CategoryChart {

    private BigDecimal amount;
    private String categoryName;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
