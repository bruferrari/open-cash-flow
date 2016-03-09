package com.ferrarib.opencf.controller;

import com.ferrarib.opencf.filter.ByDateReportFilter;
import com.ferrarib.opencf.model.Balance;
import com.ferrarib.opencf.model.Registry;
import com.ferrarib.opencf.model.ReportFormat;
import com.ferrarib.opencf.model.ReportRegistry;
import com.ferrarib.opencf.model.charts.CategoryIncomingWrapper;
import com.ferrarib.opencf.model.charts.CategoryOutgoingWrapper;
import com.ferrarib.opencf.model.charts.DailyBalanceWrapper;
import com.ferrarib.opencf.model.charts.MonthlyBalanceWrapper;
import com.ferrarib.opencf.service.ReportService;
import com.ferrarib.opencf.util.ReportGenerator;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bruno on 2/29/16.
 */
@Controller
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService service;

    private List<Registry> registries;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showReports() {
        ModelAndView mv = new ModelAndView("Reports");
        mv.addObject("dailyBalancesList", service.dailyBalanceChart().getDailyBalances());
        mv.addObject("report", new ByDateReportFilter());
        mv.addObject("formats", ReportFormat.values());

        return mv;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String generateReportByDate(@Validated ByDateReportFilter bdr, Model model) {
        registries = service.reportByDate(bdr.getFrom(), bdr.getTo());
        model.addAttribute("registries", registries);
        model.addAttribute("balance", new Balance(registries));

        //Returns template processed by thymeleaf
        return "ReportByDateTable :: resultsList";
    }

    @RequestMapping(value = "exportByDate", method = RequestMethod.POST)
    public void downloadReportByDate(@Validated ByDateReportFilter bdr, HttpServletResponse response) {
        Map<String, Object> params = new HashMap<>();

        try {
            List<ReportRegistry> reportRegistries = service.parseModel(registries);
            ReportGenerator generator = new ReportGenerator(reportRegistries,
                    service.REPORT_COMPILED_MODEL_PATH, params, bdr.getFormat());
            service.contentNegotiation(response, bdr.getFormat());
            generator.createReport(response.getOutputStream());

        } catch (JRException | IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "dailyBalance", method = RequestMethod.GET)
    public @ResponseBody DailyBalanceWrapper getDailyBalance() {
        return service.dailyBalanceChart();
    }

    @RequestMapping(value = "monthlyBalance", method = RequestMethod.GET)
    public @ResponseBody MonthlyBalanceWrapper getMonthlyBalance() {
        return service.monthlyBalanceChart();
    }

    @RequestMapping(value = "categoryOutgoing", method = RequestMethod.GET)
    public @ResponseBody CategoryOutgoingWrapper getCategoryOutgoing() {
        return service.categoryOutgoingChart();
    }

    @RequestMapping(value = "categoryIncoming", method = RequestMethod.GET)
    public @ResponseBody CategoryIncomingWrapper getCategoryIncoming() {
        return service.categoryIncomingChart();
    }

}
