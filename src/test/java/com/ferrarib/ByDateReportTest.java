package com.ferrarib;

import com.ferrarib.opencf.model.Registry;
import com.ferrarib.opencf.model.ReportRegistry;
import com.ferrarib.opencf.repository.Registries;
import com.ferrarib.opencf.util.ReportGeneratorBean;
import net.sf.jasperreports.engine.JRException;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by bruno on 3/7/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OpencfApplication.class)
@WebAppConfiguration
public class ByDateReportTest {

    @Autowired
    private Registries registries;

    @Test
    public void mustGenerateReport() throws SQLException, JRException, FileNotFoundException, ParseException {

        Map<String, Object> params = new HashMap<>();

        List<ReportRegistry> reportRegistries = parseModel();
        ReportGeneratorBean gr = new ReportGeneratorBean(reportRegistries, "src/report/by_date.jasper", params);
        gr.createReport(new FileOutputStream("src/report/byDateReport.pdf"));

    }

    private List<ReportRegistry> parseModel() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        List<Registry> registriesList = registries.findByDateBetween(sdf.parse("01/03/2016"), sdf.parse("02/03/2016"));
        List<ReportRegistry> reportRegistries = new ArrayList<>();

        registriesList.forEach(registry -> reportRegistries.add(new ReportRegistry(registry)));

        return reportRegistries;
    }

    @Test
    @Ignore
    public void testList() {
        List<ReportRegistry> reportRegistries = null;
        try {
            reportRegistries = parseModel();
            reportRegistries.forEach(r -> {
                System.out.println(r.getTitle() + " " + r.getId() + " " + r.getAmount() + " " + r.getCategory()
                + " " + r.getStatus() + " " + r.getDate());
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
