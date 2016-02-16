package com.ferrarib.opencf.service;

import com.ferrarib.opencf.filter.RegistryFilter;
import com.ferrarib.opencf.model.Registry;
import com.ferrarib.opencf.model.RegistryStatus;
import com.ferrarib.opencf.model.Balance;
import com.ferrarib.opencf.repository.Registries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by bruno on 2/10/16.
 */
@Service
public class RegistryService {

    @Autowired
    private Registries repository;

    public List<Registry> filter(RegistryFilter filter) {
        if(filter.getTitle() == null || filter.getTitle().isEmpty()) {
            return repository.findRegistriesByCurrentDate();
        }

        return repository.findByTitleContaining(filter.getTitle());
    }

    public void save(Registry registry) {
        repository.save(registry);
    }

    public List<Registry> findByTitleContainingAndCurrentDate(RegistryFilter filter) {
        String title = filter.getTitle() == null ? "%" : filter.getTitle();
        return repository.findByTitleContainingAndCurrentDate(title);
    }

    public List<Registry> findByDateBetween(RegistryFilter filter) {
        if(filter.getFrom() != null && filter.getTo() != null)
            return repository.findByDateBetween(filter.getFrom(), filter.getTo());
        return null;
    }
}
