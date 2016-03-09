package com.ferrarib.opencf.util;

import com.ferrarib.opencf.model.ReportFormat;
import com.ferrarib.opencf.model.ReportRegistry;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * Created by bruno on 3/7/16.
 * JasperReport generator - encapsulates reports engine
 */
public class ReportGenerator {

    private List<ReportRegistry> reportRegistries;
    private String fileName;
    private Map<String, Object> params;
    private ReportFormat format;

    public ReportGenerator(List<ReportRegistry> reportRegistries, String fileName, Map<String, Object> params, ReportFormat format) {
        this.reportRegistries = reportRegistries;
        this.fileName = fileName;
        this.params = params;
        this.format = format;
    }

    public void createReport(OutputStream output) throws JRException, FileNotFoundException {
//        JasperCompileManager.compileReportToFile("/src/report/by_date.jrxml");

        JRDataSource dataSource = new JRBeanCollectionDataSource(reportRegistries, false);

        JasperPrint jasperPrint = JasperFillManager.fillReport(fileName, params, dataSource);

        if(format.equals(ReportFormat.PDF)) {
            JRPdfExporter pdfExporter = new JRPdfExporter();
            pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
            pdfExporter.exportReport();
        } else if (format.equals(ReportFormat.XLS)) {
            JRXlsExporter xlsExporter = new JRXlsExporter();
            xlsExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
            xlsExporter.exportReport();
        }
    }
}
