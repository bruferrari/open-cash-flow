package com.ferrarib.opencf.model;

/**
 * Created by bruno on 3/8/16.
 */
public enum ReportFormat {

    PDF("PDF"),
    XLS("Microsoft Excel (XLS)");

    private String description;

    ReportFormat(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
