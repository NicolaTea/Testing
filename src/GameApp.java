import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GameApp {
    public static void main(String[] args){

    }



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
