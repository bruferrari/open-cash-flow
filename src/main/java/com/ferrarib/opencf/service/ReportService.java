package com.ferrarib.opencf.service;

import com.ferrarib.opencf.filter.ByDateReportFilter;
import com.ferrarib.opencf.model.DailyBalance;
import com.ferrarib.opencf.model.Registry;
import com.ferrarib.opencf.model.ReportFormat;
import com.ferrarib.opencf.model.charts.*;
import com.ferrarib.opencf.repository.DailyBalances;
import com.ferrarib.opencf.repository.Registries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by bruno on 3/1/16.
 */
@Service
public class ReportService {

    @Autowired
    private DailyBalances balances;

    @Autowired
    private Registries registries;

    public final static String REPORT_COMPILED_MODEL_PATH = "src/report/by_date.jasper";

    public DailyBalanceWrapper dailyBalanceChart() {
        List<DailyBalance> result = balances.findTop15ByOrderByDateDesc();

        DailyBalanceWrapper wrapper = new DailyBalanceWrapper();
        wrapper.setDailyBalances(result);

        return wrapper;
    }

    public CategoryOutgoingWrapper categoryOutgoingChart() {
        List<Object[]> result = registries.findCategoryOutgoing();
        List<CategoryOutgoing> categoryOutgoings = new ArrayList<>();

        result.forEach(o -> {
            CategoryOutgoing outgoing = new CategoryOutgoing();
            outgoing.setAmount(new BigDecimal(String.valueOf(o[0])));
            outgoing.setCategoryName(String.valueOf(o[1]));

            categoryOutgoings.add(outgoing);
        });

        CategoryOutgoingWrapper wrapper = new CategoryOutgoingWrapper();
        wrapper.setCategoryOutgoings(categoryOutgoings);

        return wrapper;
    }

    public MonthlyBalanceWrapper monthlyBalanceChart() {
        List<Object[]> result = balances.findBalancePerMonth();
        List<MonthlyBalance> balanceList = new ArrayList<>();

        result.forEach(o -> {
            MonthlyBalance bpm = new MonthlyBalance();
            bpm.setBalance(new BigDecimal(o[0].toString()));
            bpm.setMonth(Integer.parseInt(o[1].toString()));

            balanceList.add(bpm);
        });

        MonthlyBalanceWrapper wrapper = new MonthlyBalanceWrapper();
        wrapper.setMonthlyBalances(balanceList);

        return wrapper;
    }

    public CategoryIncomingWrapper categoryIncomingChart() {
        List<Object[]> result = registries.findCategoryIncoming();
        List<CategoryIncoming> incomings = new ArrayList<>();

        result.forEach(o -> {
            CategoryIncoming incoming = new CategoryIncoming();
            incoming.setAmount(new BigDecimal(o[0].toString()));
            incoming.setCategoryName(o[1].toString());

            incomings.add(incoming);
        });

        CategoryIncomingWrapper wrapper = new CategoryIncomingWrapper();
        wrapper.setCategoryIncomings(incomings);

        return wrapper;
    }

    public List<Registry> reportByDate(Date from, Date to) {
        return registries.findByDateBetween(from, to);
    }

    public HttpServletResponse contentNegotiation(HttpServletResponse response, ReportFormat format) {
        if(format.equals(ReportFormat.PDF)) {
            response.setContentType("application/x-pdf");
            response.setHeader("Content-disposition", "inline; filename=ocf_report" + new Date().getTime() + ".pdf");
        } else if(format.equals(ReportFormat.XLS)){
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment; filename=ocf_report" + new Date().getTime() + ".xls");
        }

        return response;
    }

    public Map<String, Object> prepareReportParams(ByDateReportFilter bdr) {
        Map<String, Object> params = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        params.put("from", sdf.format(bdr.getFrom()));
        params.put("to", sdf.format(bdr.getTo()));

        return params;
    }

}
