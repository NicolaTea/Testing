package Controller;

import Repo.InMemoryRepo;
import model.Trophy;
import model.TrophyTypes;
import model.VideoGame;
import java.util.List;

public class GameController {
    private final InMemoryRepo<VideoGame> videoGameRepo;
    private final InMemoryRepo<Trophy> trophyRepo;


    public GameController(InMemoryRepo<VideoGame> videoGameRepo, InMemoryRepo<Trophy> trophyRepo) {
        this.videoGameRepo = videoGameRepo;
        this.trophyRepo = trophyRepo;
    }

    // CRUD VideoGame
    public void addGame(String name) {
        VideoGame newGame = new VideoGame(name);
        boolean added = videoGameRepo.add(newGame, VideoGame::getName);
        if (added) {
            System.out.println("Game '" + name + "' added successfully.");
        }
    }

    public void deleteGame(String name) {
        VideoGame gameToDelete = videoGameRepo.find(game -> game.getName().equals(name)).stream().findFirst().orElse(null);
        if (gameToDelete != null) {
            videoGameRepo.delete(gameToDelete);
            System.out.println("Game '" + name + "' deleted successfully.");
        } else {
            System.out.println("Game '" + name + "' not found.");
        }
    }

    public void updateGame(String oldName, String newName) {
        VideoGame gameToUpdate = videoGameRepo.find(game -> game.getName().equals(oldName)).stream().findFirst().orElse(null);
        if (gameToUpdate != null) {
            gameToUpdate.setName(newName);
            videoGameRepo.update(gameToUpdate, game -> game.getName().equals(oldName));
            System.out.println("Game '" + oldName + "' updated to '" + newName + "'.");
        } else {
            System.out.println("Game '" + oldName + "' not found.");
        }
    }

    // CRUD Trophy
    public void addTrophyToGame(String gameName, String title, String description, TrophyTypes type) {
        VideoGame game = videoGameRepo.find(vg -> vg.getName().equals(gameName)).stream().findFirst().orElse(null);
        if (game != null) {
            Trophy newTrophy = new Trophy(title, description, type);
            boolean added = trophyRepo.add(newTrophy, Trophy::getTitle);
            if (added) {
                game.getTrophyList().add(newTrophy);
                System.out.println("Trophy '" + title + "' added to game '" + gameName + "'.");
            }
        } else {
            System.out.println("Game '" + gameName + "' not found.");
        }
    }

    public void deleteTrophyFromGame(String gameName, String title) {
        VideoGame game = videoGameRepo.find(vg -> vg.getName().equals(gameName)).stream().findFirst().orElse(null);
        if (game != null) {
            Trophy trophyToDelete = game.getTrophyList().stream().filter(trophy -> trophy.getTitle().equals(title)).findFirst().orElse(null);
            if (trophyToDelete != null) {
                game.getTrophyList().remove(trophyToDelete);
                trophyRepo.delete(trophyToDelete);
                System.out.println("Trophy '" + title + "' removed from game '" + gameName + "'.");
            } else {
                System.out.println("Trophy '" + title + "' not found in game '" + gameName + "'.");
            }
        } else {
            System.out.println("Game '" + gameName + "' not found.");
        }
    }

    public void updateTrophyForGame(String gameName, String oldTitle, String newTitle, String newDescription, TrophyTypes newType) {
        VideoGame game = videoGameRepo.find(vg -> vg.getName().equals(gameName)).stream().findFirst().orElse(null);
        if (game != null) {
            Trophy trophyToUpdate = game.getTrophyList().stream().filter(trophy -> trophy.getTitle().equals(oldTitle)).findFirst().orElse(null);
            if (trophyToUpdate != null) {
                trophyToUpdate.setTitle(newTitle);
                trophyToUpdate.setDescription(newDescription);
                trophyToUpdate.setType(newType);
                System.out.println("Trophy '" + oldTitle + "' updated to '" + newTitle + "'.");
            } else {
                System.out.println("Trophy '" + oldTitle + "' not found in game '" + gameName + "'.");
            }
        } else {
            System.out.println("Game '" + gameName + "' not found.");
        }
    }

    public List<VideoGame> getAllGames() {
        return videoGameRepo.getAll();
    }

    public List<Trophy> getAllTrophies() {
        return trophyRepo.getAll();
    }
}
