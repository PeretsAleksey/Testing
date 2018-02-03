package ua.nure.perets.SummaryTask4.utils;

import java.util.ArrayList;
import java.util.List;

public class ValidEmails {

    private static final String MAIL_RU = "@mail.ru";
    private static final String GMAIL_COM = "@gmail.com";
    private static final String GOOGLE_COM = "@google.com";

    private static final List<String> VALID_MAIL = new ArrayList<>();

    static {
        VALID_MAIL.add(MAIL_RU);
        VALID_MAIL.add(GMAIL_COM);
        VALID_MAIL.add(GOOGLE_COM);
    }


    public static List<String> getValidMail() {
        return VALID_MAIL;
    }
}
