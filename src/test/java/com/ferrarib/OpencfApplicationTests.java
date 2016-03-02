package com.ferrarib;

import com.ferrarib.opencf.model.charts.DailyBalance;
import com.ferrarib.opencf.service.DailyBalanceService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OpencfApplication.class)
@WebAppConfiguration
public class OpencfApplicationTests {

	@Autowired
	private DailyBalanceService dbService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void mustReturnBalanceFromCurrentDate() {
		DailyBalance db = dbService.dailyBalanceProducer();
		Assert.assertEquals(db.getBalance(), new BigDecimal("130.00"));
	}
}
