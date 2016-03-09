package com.ferrarib.opencf.util;

import com.ferrarib.opencf.model.ReportRegistry;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * Created by bruno on 3/7/16.
 */
public class ReportGeneratorBean {

    private List<ReportRegistry> reportRegistries;
    private String fileName;
    private Map<String, Object> params;

    public ReportGeneratorBean(List<ReportRegistry> reportRegistries, String fileName, Map<String, Object> params) {
        this.reportRegistries = reportRegistries;
        this.fileName = fileName;
        this.params = params;
    }

    public void createReport(OutputStream output) throws JRException, FileNotFoundException {
        JasperCompileManager.compileReportToFile("src/report/by_date.jrxml");

        JRDataSource dataSource = new JRBeanCollectionDataSource(reportRegistries);

        JasperPrint jasperPrint = JasperFillManager.fillReport(fileName, params, dataSource);

        JRExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, output);

        exporter.exportReport();
    }
}
