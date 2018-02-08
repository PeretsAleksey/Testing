package ua.nure.perets.SummaryTask4;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.nure.perets.SummaryTask4.exeption.AppException;
import ua.nure.perets.SummaryTask4.utils.Password;

public class PasswordTest {

    private static Password pass;

    @BeforeClass
    public static void constructorTest() {
        Assert.assertNotNull(pass = new Password());
    }

    @Test
    public void hashTest() throws AppException {
        Assert.assertEquals(128, Password.hash("anyString").length());
    }
}
