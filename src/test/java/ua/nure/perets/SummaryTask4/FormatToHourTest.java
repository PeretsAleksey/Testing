package ua.nure.perets.SummaryTask4;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.nure.perets.SummaryTask4.customTag.FormatToHour;

import javax.servlet.jsp.JspException;
import java.io.IOException;

public class FormatToHourTest {

    private static FormatToHour formatToHour;

    @BeforeClass
    public static void constructorTest() {
        formatToHour = new FormatToHour();
    }

    @Test
    public void doTagTest() throws IOException, JspException {
        formatToHour.setNumber("3000");
        formatToHour.doTag();
    }

}
