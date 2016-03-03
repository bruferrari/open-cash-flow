package com.ferrarib;

import com.ferrarib.opencf.model.charts.CategoryIncoming;
import com.ferrarib.opencf.repository.Registries;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bruno on 3/3/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OpencfApplication.class)
@WebAppConfiguration
public class RegistriesTest {

    @Autowired
    private Registries registries;

    @Test
    public void mustReturnIncomingsByCategory() {
        List<Object[]> result = registries.findCategoryIncoming();
        List<CategoryIncoming> incomings = new ArrayList<>();

        result.forEach(o -> {
            CategoryIncoming incoming = new CategoryIncoming();
            incoming.setAmount(new BigDecimal(o[0].toString()));
            incoming.setCategoryName(o[1].toString());

            incomings.add(incoming);
        });

        incomings.forEach(incoming -> {
            System.out.println("amount: " + incoming.getAmount() + " category: " + incoming.getCategoryName());
        });
    }
}
