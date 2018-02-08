package ua.nure.perets.SummaryTask4;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestTest {

    private static ua.nure.perets.SummaryTask4.bean.Test test;

    @BeforeClass
    public static void constructorTest() {
        test = new ua.nure.perets.SummaryTask4.bean.Test();
    }

    @Test
    public void getterSetterTest() {

        test.setId(2);
        test.setName("some name");
        test.setDifficulty("elementary");
        test.setQuestionsCount(2);
        test.setTimeForTest(20);
        test.setThemesId(2);

        Assert.assertEquals(2, test.getId());
        Assert.assertEquals("some name", test.getName());
        Assert.assertEquals("elementary", test.getDifficulty());
        Assert.assertEquals(2, test.getQuestionsCount());
        Assert.assertEquals(20, test.getTimeForTest());
        Assert.assertEquals(2, test.getThemesId());
    }

    @Test
    public void toStringTest() {
        String toString = test.toString();
        Assert.assertTrue(toString.contains("id=" + test.getId()));
    }
}
