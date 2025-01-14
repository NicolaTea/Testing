import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class GameApp {
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        while(true){
            System.out.println("WELCOME TO GAMEAPP");
            System.out.println("1. Sortieren die Daten nach Metacritic Punktanzahl");
            System.out.println("2. Sortieren die Daten nach Erscheinungsdatum");
            System.out.println("3. Filtern die Daten nach der UserScore");
            System.out.println("4. Filtern die Daten nach den Namen die mit D anfangen");
            System.out.println("5. Exit");
            System.out.println("Choose your option: ");
            int option=scanner.nextInt();
            scanner.nextLine();

            switch (option){
                case 1:
                   handleSortByMetacritic();
                    break;
                case 2:
                   handleSortbyReleaseDate();
                   break;
                case 3:
                    handleFilterbyUserScore();
                    break;
                case 4:
                    handleFilterbyName();
                    break;
                case 5:
                    System.out.println("Goodbye");
                    return;
                default:
                    System.out.println("Invalid option. Try again");
                    break;
            }
        }

    }

    private static void handleSortByMetacritic(){
        List<Game> games=loadGameFromFile("src/logs/top-games-shuffled.txt");
        writeGameToFile(sortbyMetacritic(games),"src/logs/top-games-metacritic.txt");
        System.out.println("The date was successfully added!");
    }

    private static  void handleSortbyReleaseDate(){
        List<Game> games=loadGameFromFile("src/logs/top-games-shuffled.txt");
        writeGameToFile(sortbyReleaseDate(games),"src/logs/top-games-date.txt");
        System.out.println("The date was successfully added!");
    }

    private static void handleFilterbyUserScore(){
        List<Game> games=loadGameFromFile("src/logs/top-games-shuffled.txt");
        writeGameToFile(filterbyUserScore(games),"src/logs/top-games-user.txt");
        System.out.println("The date was successfully added!");
    }

    private static void handleFilterbyName(){
        List<Game> games=loadGameFromFile("src/logs/top-games-shuffled.txt");
        writeGameToFile(filterbyName(games),"src/logs/top-games-names.txt");
        System.out.println("The date was successfully added!");
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
                    LocalDate releaseDate = LocalDate.parse(parts[3],
                            java.time.format.DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH));
                    games.add(new Game(name,metacritic,userscore,releaseDate));
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
                        game.getReleaseDate().format(DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH)));
            }
        } catch (IOException e) {
            System.out.println("Error at writing into the file");
        }
    }
}
