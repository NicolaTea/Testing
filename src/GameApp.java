import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GameApp {
    public static void main(String[] args){

    }

    /**
     * sort games by metaCritic
     * @param games
     * @return
     */
    public static List<Game> sortbyMetacritic(List<Game> games){
        return games.stream()
                .sorted(Comparator.comparing(Game::getMetaCritic).reversed())
                .collect(Collectors.toList());
    }

    /**
     * sort games by releaseDate
     * @param games
     * @return
     */
    public static List<Game> sortbyReleaseDate(List<Game> games){
        return games.stream()
                .sorted(Comparator.comparing(Game::getReleaseDate).reversed())
                .collect(Collectors.toList());
    }

    /**
     * filter games by UserScore
     * @param games
     * @return
     */
    public static List<Game> filterbyUserScore(List<Game> games){
        return games.stream()
                .filter(game -> game.getUserScore()>9)
                .collect(Collectors.toList());
    }

    /**
     * filter games by name
     * @param games
     * @return
     */
    public static List<Game> filterbyName(List<Game> games){
        return games.stream()
                .filter(game -> game.getName().startsWith("D"))
                .collect(Collectors.toList());
    }


    /**
     * read the file
     * @param filename
     * @return
     */
    private static List<Game> loadGameFromFile(String filename){
        List<Game> games=new ArrayList<>();
        try(BufferedReader reader=new BufferedReader(new FileReader(filename))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\*");
                if (parts.length == 4) {
                    String name=parts[0];
                    int metacritic=Integer.parseInt(parts[1]);
                    float userscore=Float.parseFloat(parts[2]);
                    LocalDate releadedate=LocalDate.parse(parts[3],java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                    games.add(new Game(name,metacritic,userscore,releadedate));
                } else {
                    System.out.println("Invalid line format: " + line);
                }
            }
        }catch (IOException e){
            System.out.println("File not found");
        }
        return  games;
    }

    /**
     * write to file
     * @param games
     * @param filename
     */
    private static void writeGameToFile(List<Game> games,String filename){
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {
            for (Game game : games) {
                writer.println( game.getName()+ "#" +
                        game.getMetaCritic() + "#" +
                       game.getUserScore() + "#" +
                        game.getReleaseDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            }
        } catch (IOException e) {
            System.out.println("Error at writing into the file");
        }
    }
}
