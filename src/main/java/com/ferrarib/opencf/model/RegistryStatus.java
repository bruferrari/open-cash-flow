package com.ferrarib.opencf.model;

/**
 * Created by bruno on 2/10/16.
 */
public enum RegistryStatus {
    INCOMING("Incoming"),
    OUTGOING("Outgoing");

    private String description;

    RegistryStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
