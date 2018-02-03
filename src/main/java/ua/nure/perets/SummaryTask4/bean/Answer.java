package ua.nure.perets.SummaryTask4.bean;

public class Answer {

    private int id;
    private String name;
    private boolean correct;
    private int questionId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return "AnswerDao{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", correct=" + correct +
                ", questionId=" + questionId +
                '}';
    }
}
