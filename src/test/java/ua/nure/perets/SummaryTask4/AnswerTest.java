package ua.nure.perets.SummaryTask4;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.nure.perets.SummaryTask4.bean.Answer;

public class AnswerTest {

    private static Answer answer;

    @BeforeClass
    public static void constructorTest() {
        answer = new Answer();
    }

    @Test
    public void getterSetterTest() {

        answer.setId(1);
        answer.setName("some name");
        answer.setCorrect(true);
        answer.setQuestionId(3);

        Assert.assertEquals(1, answer.getId());
        Assert.assertEquals("some name", answer.getName());
        Assert.assertEquals(true, answer.isCorrect());
        Assert.assertEquals(3, answer.getQuestionId());
    }

    @Test
    public void toStringTest() {
        String toString = answer.toString();
        Assert.assertTrue(toString.contains("id=" + answer.getId()));
    }
}
