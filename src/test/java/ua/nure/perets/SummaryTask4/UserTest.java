package ua.nure.perets.SummaryTask4;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.nure.perets.SummaryTask4.bean.User;

public class UserTest {

    private static User user;

    @BeforeClass
    public static void constructorTest() {
        user = new User();
    }

    @Test
    public void getterSetterTest() {

        user.setId(1);
        user.setLogin("some login");
        user.setPassword("some password");
        user.setfName("some fname");
        user.setlName("some lname");
        user.seteMail("some eMail");
        user.setBlocked(true);
        user.setRoleId(0);

        Assert.assertEquals(1, user.getId());
        Assert.assertEquals("some login", user.getLogin());
        Assert.assertEquals("some password", user.getPassword());
        Assert.assertEquals("some fname", user.getfName());
        Assert.assertEquals("some lname", user.getlName());
        Assert.assertEquals("some eMail", user.geteMail());
        Assert.assertEquals(true, user.isBlocked());
        Assert.assertEquals(0, user.getRoleId());
    }

    @Test
    public void toStringTest() {
        String toString = user.toString();
        Assert.assertTrue(toString.contains("id=" + user.getId()));
    }
}
