package ua.nure.perets.SummaryTask4.utils;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import ua.nure.perets.SummaryTask4.bean.UserTest;
import ua.nure.perets.SummaryTask4.dao.impl.UserTestDaoImpl;
import ua.nure.perets.SummaryTask4.exeption.DBException;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Generator {

    private static final String pathForSaving = "C:\\SummaryTask4\\reports\\report.pdf";
    private static final String pathForPattern = "C:\\SummaryTask4\\reportGenerator\\jrxml\\report.jrxml";


    public boolean createReports() throws DBException {
        boolean status = false;
        try {



          /*  System.out.println("Начало генерации отчёта");
            UserTestDaoImpl userTestDao = new UserTestDaoImpl();
            List<UserTest> userTestList = userTestDao.getUsersTests();

            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(userTestList);

            JasperCompileManager.compileReportToFile(pathForPattern, jasperFileName);
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("DATE", new Date());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFileName, parameters, beanColDataSource);
            JasperExportManager.exportReportToPdfFile(jasperPrint, pathForSaving);
            System.out.println("Генерация отчёта завершена");*/

            //--------------------

            System.out.println("Начало генерации отчёта");
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
            System.out.println("Генерация отчёта завершена");
            status = true;
            return status;
        } catch (Exception e) {
            e.printStackTrace();
            return status;
        }
    }
}
