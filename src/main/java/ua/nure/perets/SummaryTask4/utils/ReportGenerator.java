package ua.nure.perets.SummaryTask4.utils;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.log4j.Logger;
import ua.nure.perets.SummaryTask4.bean.UserTest;
import ua.nure.perets.SummaryTask4.dao.impl.UserTestDaoImpl;
import ua.nure.perets.SummaryTask4.exeption.DBException;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportGenerator {

    private static final Logger LOG = Logger.getLogger(ReportGenerator.class);

    private static final String pathForSaving = "C:\\SummaryTask4\\reports\\report.pdf";
    private static final String pathForPattern = "C:\\SummaryTask4\\reportGenerator\\jrxml\\report.jrxml";

    /**
     * generation of a report on the results of testing
     * @return returns report generation status
     * @throws DBException if SQLException occurred
     */
    public boolean createReports() throws DBException {
        boolean status = false;
        try {

            log("Start of report generation");
            UserTestDaoImpl userTestDao = new UserTestDaoImpl();
            List<UserTest> userTestList = userTestDao.getUsersTests();

            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(userTestList);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("DATE", new Date());
            File reportPattern = new File(pathForPattern);
            JasperDesign jasperDesign = JRXmlLoader.load(reportPattern);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
            JasperExportManager.exportReportToPdfFile(jasperPrint, pathForSaving);
            log("Report generation completed");
            status = true;
            return status;
        } catch (Exception e) {
            LOG.error(e);
            return status;
        }
    }

    private void log(String msg) {
        System.out.println("[ReportGenerator] " + msg);
    }
}
