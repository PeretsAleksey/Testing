package ua.nure.perets.SummaryTask4.bean;

public class Test {

    private int id;
    private String name;
    private String difficulty;
    private int questionsCount;
    private int timeForTest;
    private int themesId;

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

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getQuestionsCount() {
        return questionsCount;
    }

    public void setQuestionsCount(int questionsCount) {
        this.questionsCount = questionsCount;
    }

    public int getTimeForTest() {
        return timeForTest;
    }

    public void setTimeForTest(int timeForTest) {
        this.timeForTest = timeForTest;
    }

    public int getThemesId() {
        return themesId;
    }

    public void setThemesId(int themesId) {
        this.themesId = themesId;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", questionsCount=" + questionsCount +
                ", timeForTest=" + timeForTest +
                ", themesId=" + themesId +
                '}';
    }
}
