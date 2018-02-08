package ua.nure.perets.SummaryTask4;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.nure.perets.SummaryTask4.bean.Theme;

public class ThemeTest {

    private static Theme theme;

    @BeforeClass
    public static void constructorTest() {
        theme = new Theme();
    }

    @Test
    public void getterSetterTest() {

        theme.setId(2);
        theme.setName("some name");
        theme.setBlocked(false);

        Assert.assertEquals(2, theme.getId());
        Assert.assertEquals("some name", theme.getName());
        Assert.assertEquals(false, theme.isBlocked());
    }

    @Test
    public void toStringTest() {
        String toString = theme.toString();
        Assert.assertTrue(toString.contains("id=" + theme.getId()));
    }
}
