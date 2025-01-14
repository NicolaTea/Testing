package test;

import Controller.GameController;
import Repo.InMemoryRepo;
import model.Trophy;
import model.TrophyTypes;
import model.VideoGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {
    private InMemoryRepo<VideoGame> videoGameRepo;
    private InMemoryRepo<Trophy> trophyRepo;
    private GameController gameController;

    @BeforeEach
    public void setUp() {
        videoGameRepo = new InMemoryRepo<>();
        trophyRepo = new InMemoryRepo<>();
        gameController = new GameController(videoGameRepo, trophyRepo);
    }

    @Test
    public void testAddGameUnique() {
        gameController.addGame("Super Mario");
        assertEquals(1, videoGameRepo.getAll().size());
        assertEquals("Super Mario", videoGameRepo.getAll().get(0).getName());


        gameController.addGame("Super Mario");
        assertEquals(1, videoGameRepo.getAll().size());
    }

    @Test
    public void testAddTrophyToGame() {
        gameController.addGame("Super Mario");
        gameController.addTrophyToGame("Super Mario", "First Steps", "Complete the first level", TrophyTypes.GOLD);
        VideoGame game = videoGameRepo.getAll().get(0);
        assertEquals(1, game.getTrophyList().size());
        assertEquals("First Steps", game.getTrophyList().get(0).getTitle());
        assertEquals(1, trophyRepo.getAll().size());
        assertEquals("First Steps", trophyRepo.getAll().get(0).getTitle());
    }

    @Test
    public void testDeleteTrophyFromGame() {
        gameController.addGame("Super Mario");
        gameController.addTrophyToGame("Super Mario", "First Steps", "Complete the first level", TrophyTypes.GOLD);
        gameController.deleteTrophyFromGame("Super Mario", "First Steps");
        VideoGame game = videoGameRepo.getAll().get(0);
        assertEquals(0, game.getTrophyList().size());
        assertEquals(0, trophyRepo.getAll().size());
    }

    @Test
    public void testUpdateGame() {
        gameController.addGame("Super Mario");
        gameController.updateGame("Super Mario", "Super Mario Odyssey");
        assertEquals(1, videoGameRepo.getAll().size());
        assertEquals("Super Mario Odyssey", videoGameRepo.getAll().get(0).getName());
    }

    @Test
    public void testDeleteGame() {
        gameController.addGame("Super Mario");
        gameController.deleteGame("Super Mario");
        assertEquals(0, videoGameRepo.getAll().size());
    }
}
