package ua.nure.perets.SummaryTask4;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.nure.perets.SummaryTask4.bean.Question;

public class QuestionTest {

    private static Question question;

    @BeforeClass
    public static void constructorTest() {
        question = new Question();
    }

    @Test
    public void getterSetterTest() {

        question.setId(3);
        question.setName("some name");
        question.setTestId(3);

        Assert.assertEquals(3, question.getId());
        Assert.assertEquals("some name", question.getName());
        Assert.assertEquals(3, question.getTestId());
    }

    @Test
    public void toStringTest() {
        String toString = question.toString();
        Assert.assertTrue(toString.contains("testId=" + question.getTestId()));
    }
}
