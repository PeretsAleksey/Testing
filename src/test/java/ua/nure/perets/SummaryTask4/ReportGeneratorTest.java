package ua.nure.perets.SummaryTask4;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.nure.perets.SummaryTask4.exeption.DBException;
import ua.nure.perets.SummaryTask4.utils.ReportGenerator;

public class ReportGeneratorTest {

    private static ReportGenerator reportGenerator;

    @BeforeClass
    public static void constructorTest() {
        reportGenerator = new ReportGenerator();
    }

    @Test
    public void createReportsTest() throws DBException {
        boolean status = reportGenerator.createReports();
        Assert.assertEquals(true, status);
    }
}
