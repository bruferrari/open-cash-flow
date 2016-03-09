package com.ferrarib.opencf.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by bruno on 3/9/16.
 */
public class ReportRegistry {

    private Registry registry;

    public ReportRegistry(Registry registry) {
        this.registry = registry;
    }

    public Long getId() {
        return registry.getId();
    }

    public String getTitle() {
        return registry.getTitle();
    }

    public Date getDate() {
        return registry.getDate();
    }

    public String getCategory() {
        return registry.getCategory().getDescription();
    }

    public BigDecimal getAmount() {
        return registry.getAmount();
    }

    public String getStatus() {
        return registry.getStatus().getDescription();
    }
}
