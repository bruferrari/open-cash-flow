package com.ferrarib.opencf.model;

import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by bruno on 2/13/16.
 */
public class Balance {

    public List<Registry> registries;

    public Balance(List<Registry> registries) {
        this.registries = registries;
    }

    @NumberFormat(pattern="#,##0.00")
    private BigDecimal incoming;

    @NumberFormat(pattern="#,##0.00")
    private BigDecimal outgoing;

    @NumberFormat(pattern="#,##0.00")
    private BigDecimal balance;

    public BigDecimal getIncoming() {
        return registries.stream().filter(r -> r.getStatus().equals(RegistryStatus.INCOMING))
                .map(Registry::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getOutgoing() {
        return registries.stream().filter(r -> r.getStatus().equals(RegistryStatus.OUTGOING))
                .map(Registry::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getBalance() {
        return this.getIncoming().subtract(this.getOutgoing());
    }

    public boolean isNegative() {
        return this.getBalance().compareTo(BigDecimal.ZERO) < 0 ? true : false;
    }
}
