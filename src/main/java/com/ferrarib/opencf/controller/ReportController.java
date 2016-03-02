package com.ferrarib.opencf.controller;

import com.ferrarib.opencf.model.charts.DailyBalanceWrapper;
import com.ferrarib.opencf.model.charts.MonthlyBalanceWrapper;
import com.ferrarib.opencf.service.ChartService;
import com.ferrarib.opencf.service.DailyBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by bruno on 2/29/16.
 */
@Controller
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ChartService service;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showReports() {
        ModelAndView mv = new ModelAndView("Reports");
        return mv;
    }

    @RequestMapping(value = "dailyBalance", method = RequestMethod.GET)
    public @ResponseBody DailyBalanceWrapper getDailyBalance() {
        return service.dailyBalanceChart();
    }

    @RequestMapping(value = "monthlyBalance", method = RequestMethod.GET)
    public @ResponseBody MonthlyBalanceWrapper getMonthlyBalance() {
        return service.monthlyBalanceChart();
    }

}
