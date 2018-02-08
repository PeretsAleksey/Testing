package ua.nure.perets.SummaryTask4;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.nure.perets.SummaryTask4.customTag.FormatToMin;

import javax.servlet.jsp.JspException;
import java.io.IOException;

public class FormatToMinTest {

    private static FormatToMin formatToMin;

    @BeforeClass
    public static void constructorTest() {
        formatToMin = new FormatToMin();
    }

    @Test
    public void doTagTest() throws IOException, JspException {
        formatToMin.setNumber("300");
        formatToMin.doTag();
    }
}
