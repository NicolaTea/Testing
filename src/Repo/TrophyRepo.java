package Repo;

import model.Trophy;

import java.util.ArrayList;
import java.util.List;

public class TrophyRepo {
    private final List<Trophy> trophyList=new ArrayList<>();


    public void add(Trophy trophy){
        trophyList.add(trophy);
    }


    public List<Trophy> getAll(){
        return trophyList;
    }



    public void delete(Trophy trophy){
        trophyList.remove(trophy);
    }


    public void update(Trophy trophy){
        for (int i = 0; i <trophyList.size(); i++) {
            if (trophyList.get(i).getTitle().equals(trophy.getTitle())) {
                trophyList.set(i, trophy);
                return;
            }
        }
    }
}
