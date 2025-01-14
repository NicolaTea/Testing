package Repo;

import model.VideoGame;

import java.util.ArrayList;
import java.util.List;

public class VideoGameRepo {
    private final List<VideoGame> videoGames=new ArrayList<>();

   public void add(VideoGame videoGame){
       videoGames.add(videoGame);
   }

   public void delete(VideoGame videoGame){
       videoGames.remove(videoGame);
   }

   public void update(VideoGame videoGame){
       for (int i = 0; i <videoGames.size(); i++) {
           if (videoGames.get(i).getName().equals(videoGame.getName())) {
               videoGames.set(i, videoGame);
               return;
           }
       }
   }

   public List<VideoGame> getAll(){
       return videoGames;
   }
}
