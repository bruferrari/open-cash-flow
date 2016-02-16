package com.ferrarib.opencf.filter;

import java.util.Date;

/**
 * Created by bruno on 2/12/16.
 */
public class RegistryFilter {

    private String title;
    private Date from;
    private Date to;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }
}
