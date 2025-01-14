import java.time.LocalDate;

public class Game {
    private String name;
    private int metaCritic;
    private float userScore;
    private LocalDate releaseDate;

    public Game(String name,int metaCritic,float userScore,LocalDate releaseDate){
        this.name=name;
        this.metaCritic=metaCritic;
        this.userScore=userScore;
        this.releaseDate=releaseDate;
    }

    public String getName() {
        return name;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public float getUserScore() {
        return userScore;
    }

    public int getMetaCritic() {
        return metaCritic;
    }

    @Override
    public String toString() {
        return "Game{" +
                "name='" + name + '\'' +
                ", metaCritic=" + metaCritic +
                ", userScore=" + userScore +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
