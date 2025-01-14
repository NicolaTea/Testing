package model;

public class Trophy {
    private String title;
    private String description;
    TrophyTypes type;

    public Trophy(String title,String description,TrophyTypes type){
        this.title=title;
        this.description=description;
        this.type=type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setType(TrophyTypes type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TrophyTypes getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Trophy{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                '}';
    }
}
