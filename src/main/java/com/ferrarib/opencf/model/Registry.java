package com.ferrarib.opencf.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by bruno on 2/10/16.
 */
@Entity
public class Registry {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Title field is required")
    private String title;

    @NotNull(message = "Date field is required")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    private Category category;

    @NotNull(message = "Amount field is required")
    @NumberFormat(pattern="#,##0.00")
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private RegistryStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public RegistryStatus getStatus() {
        return status;
    }

    public void setStatus(RegistryStatus status) {
        this.status = status;
    }

    public boolean isCredit() {
        return this.getStatus().equals(RegistryStatus.INCOMING);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Registry registry = (Registry) o;

        return id != null ? id.equals(registry.id) : registry.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Registry{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", category=" + category.getId() +
                ", amount=" + amount +
                ", status=" + status +
                '}';
    }
}
