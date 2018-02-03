package ua.nure.perets.SummaryTask4.customTag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class FormatToHour extends SimpleTagSupport {

    private String number;

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public void doTag() throws JspException, IOException {

        try {
            getJspContext().getOut().write(String.valueOf(Integer.valueOf(number) / 3600));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
