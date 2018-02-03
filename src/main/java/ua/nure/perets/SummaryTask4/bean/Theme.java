package ua.nure.perets.SummaryTask4.bean;

public class Theme {

    private int id;
    private String name;
    private boolean blocked;

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

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public String toString() {
        return "ThemeDao{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", blocked=" + blocked +
                '}';
    }
}
