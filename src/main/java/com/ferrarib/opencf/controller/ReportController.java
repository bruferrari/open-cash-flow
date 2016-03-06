package com.ferrarib.opencf.controller;

import com.ferrarib.opencf.model.ByDateReport;
import com.ferrarib.opencf.model.Registry;
import com.ferrarib.opencf.model.charts.CategoryIncomingWrapper;
import com.ferrarib.opencf.model.charts.CategoryOutgoingWrapper;
import com.ferrarib.opencf.model.charts.DailyBalanceWrapper;
import com.ferrarib.opencf.model.charts.MonthlyBalanceWrapper;
import com.ferrarib.opencf.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

/**
 * Created by bruno on 2/29/16.
 */
@Controller
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService service;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showReports() {
        ModelAndView mv = new ModelAndView("Reports");
        mv.addObject("dailyBalancesList", service.dailyBalanceChart().getDailyBalances());
        mv.addObject("report", new ByDateReport());

        return mv;
    }

//    @RequestMapping(method = RequestMethod.POST)
//    public @ResponseBody List<Registry> generateReportByDate(ByDateReport bdr, RedirectAttributes attrb) {
//        List<Registry> result = service.reportByDate(bdr.getFrom(), bdr.getTo());
////        ModelAndView mv = new ModelAndView("redirect:/reports");
//
////        attrb.addFlashAttribute("registries", result);
////        mv.addObject("registries", result);
////        System.out.println(result);
//
//        return result;
//    }

    @RequestMapping(method = RequestMethod.POST)
    public String generateReportByDate(ByDateReport bdr, Model model) {
        model.addAttribute("registries", service.reportByDate(bdr.getFrom(), bdr.getTo()));
//        ModelAndView mv = new ModelAndView("redirect:/reports");

//        attrb.addFlashAttribute("registries", result);
//        mv.addObject("registries", result);
//        System.out.println(result);

        return "ReportByDateTable :: resultsList";
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
