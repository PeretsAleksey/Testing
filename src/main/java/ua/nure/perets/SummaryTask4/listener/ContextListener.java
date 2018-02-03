package ua.nure.perets.SummaryTask4.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext context = sce.getServletContext();
        String localesFileName = context.getInitParameter("locales");

        String localesFileRealPath = context.getRealPath(localesFileName);

        Properties locales = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(localesFileRealPath);
        } catch (FileNotFoundException e1) {
            System.out.println("Cannot create inputStream " + e1);
        }

        try {
            locales.load(inputStream);
        } catch (IOException e) {
            System.out.println("Cannot configure Log4j " + e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                System.out.println("Cannot close inputStream " + e);
            }
        }

        context.setAttribute("locales", locales);
        ServletContext servletContext = sce.getServletContext();
        initCommandContainer();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    private void initCommandContainer() {

        // initialize commands container
        // just load class to JVM
        try {
            Class.forName("ua.nure.perets.SummaryTask4.web.command.CommandContainer");
        } catch (ClassNotFoundException ex) {
            throw new IllegalStateException("Cannot initialize Command Container");
        }
    }
}
