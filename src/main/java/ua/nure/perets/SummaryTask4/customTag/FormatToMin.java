package ua.nure.perets.SummaryTask4.customTag;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class FormatToMin extends SimpleTagSupport {

    private static final Logger LOG = Logger.getLogger(FormatToMin.class);

    private String number;

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public void doTag() throws JspException, IOException {

        LOG.trace("Number: " + number);

        try {
            getJspContext().getOut().write(String.valueOf(Integer.valueOf(number)%3600/60));
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }

    }
}