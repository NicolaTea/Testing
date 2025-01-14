import Controller.GameController;
import Repo.InMemoryRepo;
import model.Trophy;
import model.TrophyTypes;
import model.VideoGame;

import java.util.Scanner;


public class Main {
    private static GameController controller;
    private static Scanner scanner;
    public static void main(String[] args) {
        InMemoryRepo<VideoGame> videoGameRepo = new InMemoryRepo<>();
        InMemoryRepo<Trophy> trophyRepo = new InMemoryRepo<>();
        controller = new GameController(videoGameRepo, trophyRepo);
        scanner = new Scanner(System.in);
        addInitialData();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Game");
            System.out.println("2. Delete Game");
            System.out.println("3. Update Game");
            System.out.println("4. Add Trophy to Game");
            System.out.println("5. Delete Trophy from Game");
            System.out.println("6. Update Trophy in Game");
            System.out.println("7. Display All Games");
            System.out.println("8. Display All Trophies");
            System.out.println("9. Exit");
            System.out.print("Select an option (1-9): ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addGame();
                    break;

                case 2:
                    deleteGame();
                    break;

                case 3:
                    updateGame();
                    break;

                case 4:
                    addTrophyToGame();
                    break;

                case 5:
                    deleteTrophyFromGame();
                    break;

                case 6:
                    updateTrophyForGame();
                    break;

                case 7:
                    showAllGames();
                    break;

                case 8:
                    showAllTrophies();
                    break;

                case 9:
                    System.out.println("Exiting App...");
                    return;
                default:
                    System.out.println("Invalid option.Try again.");
            }
        }
    }
    // Add a game
    private static void addGame() {
        System.out.print("Enter the name of the game: ");
        String gameName = scanner.nextLine();
        controller.addGame(gameName);
    }

    // Delete a game
    private static void deleteGame() {
        System.out.print("Enter the name of the game to delete: ");
        String deleteGameName = scanner.nextLine();
        controller.deleteGame(deleteGameName);
    }

    // Update a game
    private static void updateGame() {
        System.out.print("Enter the name of the game to update: ");
        String oldGameName = scanner.nextLine();
        System.out.print("Enter the new name for the game: ");
        String newGameName = scanner.nextLine();
        controller.updateGame(oldGameName, newGameName);

    }

    // Add a trophy to a game
    private static void addTrophyToGame() {
        System.out.print("Enter the name of the game: ");
        String gameNameForTrophy = scanner.nextLine();
        System.out.print("Enter the title of the trophy: ");
        String trophyTitle = scanner.nextLine();
        System.out.print("Enter the description of the trophy: ");
        String trophyDescription = scanner.nextLine();
        System.out.print("Enter the trophy type (GOLD, SILVER, BRONZE): ");
        String trophyTypeStr = scanner.nextLine().toUpperCase();
        TrophyTypes trophyType = TrophyTypes.valueOf(trophyTypeStr);
        controller.addTrophyToGame(gameNameForTrophy, trophyTitle, trophyDescription, trophyType);
    }

    // Delete a trophy from a game
    private static void deleteTrophyFromGame() {
        System.out.print("Enter the name of the game: ");
        String gameNameForDeleteTrophy = scanner.nextLine();
        System.out.print("Enter the title of the trophy to delete: ");
        String trophyTitleToDelete = scanner.nextLine();
        controller.deleteTrophyFromGame(gameNameForDeleteTrophy, trophyTitleToDelete);
    }

    // Update a trophy in a game
    private static void updateTrophyForGame() {
        System.out.print("Enter the name of the game: ");
        String gameNameForUpdateTrophy = scanner.nextLine();
        System.out.print("Enter the title of the trophy to update: ");
        String oldTrophyTitle = scanner.nextLine();
        System.out.print("Enter the new title for the trophy: ");
        String newTrophyTitle = scanner.nextLine();
        System.out.print("Enter the new description for the trophy: ");
        String newTrophyDescription = scanner.nextLine();
        System.out.print("Enter the new trophy type (GOLD, SILVER, BRONZE): ");
        String newTrophyTypeStr = scanner.nextLine().toUpperCase();
        TrophyTypes newTrophyType = TrophyTypes.valueOf(newTrophyTypeStr);
        controller.updateTrophyForGame(gameNameForUpdateTrophy, oldTrophyTitle, newTrophyTitle, newTrophyDescription, newTrophyType);
    }

    // Display all games
    private static void showAllGames() {
        System.out.println("\nAll games:");
        controller.getAllGames().forEach(System.out::println);
    }

    // Display all trophies
    private static void showAllTrophies() {
        System.out.println("\nAll trophies:");
        controller.getAllTrophies().forEach(System.out::println);
    }

    private static void addInitialData() {
        // Adding some sample games
        controller.addGame("The Witcher 3: Wild Hunt");
        controller.addGame("Cyberpunk 2077");
        controller.addGame("Minecraft");

        // Adding some sample trophies
        controller.addTrophyToGame("The Witcher 3: Wild Hunt", "Monster Hunter", "Defeat 100 monsters", TrophyTypes.GOLD);
        controller.addTrophyToGame("The Witcher 3: Wild Hunt", "Master of the Sword", "Win 50 sword fights", TrophyTypes.SILVER);
        controller.addTrophyToGame("Cyberpunk 2077", "Street Kid", "Reach level 20", TrophyTypes.BRONZE);
        controller.addTrophyToGame("Minecraft", "Explorer", "Find all biomes", TrophyTypes.GOLD);

        System.out.println("Initial data has been added.");
    }


}