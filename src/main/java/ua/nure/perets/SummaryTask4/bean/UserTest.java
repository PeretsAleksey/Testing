package ua.nure.perets.SummaryTask4.bean;

public class UserTest {

    private int id;
    private String fName;
    private String lName;
    private String themeName;
    private String testName;
    private String testDifficulty;
    private String date;
    private int result;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestDifficulty() {
        return testDifficulty;
    }

    public void setTestDifficulty(String testDifficulty) {
        this.testDifficulty = testDifficulty;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "UserTestDao{" +
                "id=" + id +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", themeName='" + themeName + '\'' +
                ", testName='" + testName + '\'' +
                ", testDifficulty='" + testDifficulty + '\'' +
                ", date='" + date + '\'' +
                ", result=" + result +
                '}';
    }
}
