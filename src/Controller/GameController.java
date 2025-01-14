package Controller;

import Repo.InMemoryRepo;
import model.Trophy;
import model.TrophyTypes;
import model.VideoGame;

import java.util.List;
import java.util.function.Predicate;

public class GameController {
    private final InMemoryRepo<VideoGame> videoGameRepo;
    private final InMemoryRepo<Trophy> trophyRepo;


    public GameController(InMemoryRepo<VideoGame> videoGameRepo, InMemoryRepo<Trophy> trophyRepo) {
        this.videoGameRepo = videoGameRepo;
        this.trophyRepo = trophyRepo;
    }

    //CRUD VideoGame
    public void addGame(String name) {
        VideoGame newGame = new VideoGame(name);
        videoGameRepo.add(newGame);
    }

    public void deleteGame(String name) {
        VideoGame gameToDelete = videoGameRepo.find(game -> game.getName().equals(name)).stream().findFirst().orElse(null);
        if (gameToDelete != null) {
            videoGameRepo.delete(gameToDelete);
        }
    }

    public void updateGame(String oldName, String newName) {
        VideoGame gameToUpdate = videoGameRepo.find(game -> game.getName().equals(oldName)).stream().findFirst().orElse(null);
        if (gameToUpdate != null) {
            gameToUpdate.setName(newName);
            videoGameRepo.update(gameToUpdate, game -> game.getName().equals(oldName));
        }
    }


    //CRUD Trophy
    public void addTrophyToGame(String gameName, String title, String description, TrophyTypes type) {
        VideoGame game = videoGameRepo.find(vg -> vg.getName().equals(gameName)).stream().findFirst().orElse(null);
        if (game != null) {
            Trophy newTrophy = new Trophy(title, description, type);
            game.getTrophyList().add(newTrophy);
            trophyRepo.add(newTrophy);
        }
    }

    public void deleteTrophyFromGame(String gameName, String title) {
        VideoGame game = videoGameRepo.find(vg -> vg.getName().equals(gameName)).stream().findFirst().orElse(null);
        if (game != null) {
            Trophy trophyToDelete = game.getTrophyList().stream().filter(trophy -> trophy.getTitle().equals(title)).findFirst().orElse(null);
            if (trophyToDelete != null) {
                game.getTrophyList().remove(trophyToDelete);
                trophyRepo.delete(trophyToDelete);
            }
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
            }
        }
    }

    public List<VideoGame> getAllGames() {
        return videoGameRepo.getAll();
    }

    public List<Trophy> getAllTrophies() {
        return trophyRepo.getAll();
    }

    public List<VideoGame> findGames(Predicate<VideoGame> condition) {
        return videoGameRepo.find(condition);
    }

    public List<Trophy> findTrophies(Predicate<Trophy> condition) {
        return trophyRepo.find(condition);
    }
}
