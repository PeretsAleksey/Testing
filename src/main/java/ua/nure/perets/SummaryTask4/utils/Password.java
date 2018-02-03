package ua.nure.perets.SummaryTask4.utils;

import ua.nure.perets.SummaryTask4.exeption.AppException;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Password {
    private static final String ERROR = "Password hash error";

    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * password hash method
     *
     * @param str value, that must be hashed
     * @return hashed value
     * @throws AppException contains UnsupportedEncodingException, NoSuchAlgorithmException
     */

    public static String hash(String str) throws AppException {
        MessageDigest digest;
        StringBuffer hexString = new StringBuffer();
        try {
            digest = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new AppException(ERROR, e);
        }
        try {
            digest.update(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new AppException(ERROR, e);
        }
        for (byte d : digest.digest()) {
            hexString.append(getFirstHexDigit(d)).append(getSecondHexDigit(d));
        }
        return hexString.toString();
    }

    /**
     * @param x byte value
     * @return hashed char value
     */

    private static char getFirstHexDigit(byte x) {
        return HEX_DIGITS[(0xFF & x) / 16];
    }

    /***
     *
     * @param x byte value
     * @return hashed char value
     */
    private static char getSecondHexDigit(byte x) {
        return HEX_DIGITS[(0xFF & x) % 16];
    }
}
