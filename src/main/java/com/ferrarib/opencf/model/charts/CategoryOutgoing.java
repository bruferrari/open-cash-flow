package com.ferrarib.opencf.model.charts;

import java.math.BigDecimal;

/**
 * Created by bruno on 3/2/16.
 */
public class CategoryOutgoing {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryOutgoing that = (CategoryOutgoing) o;

        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        return categoryName != null ? categoryName.equals(that.categoryName) : that.categoryName == null;

    }

    @Override
    public int hashCode() {
        int result = amount != null ? amount.hashCode() : 0;
        result = 31 * result + (categoryName != null ? categoryName.hashCode() : 0);
        return result;
    }
}
