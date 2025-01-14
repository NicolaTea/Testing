package model;

import java.util.ArrayList;
import java.util.List;

public class VideoGame {
    private String name;
    List<Trophy> trophyList;

    public VideoGame(String name){
        this.name=name;
        this.trophyList=new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Trophy> getTrophyList() {
        return trophyList;
    }

    public void setTrophyList(List<Trophy> trophyList) {
        this.trophyList = trophyList;
    }


    @Override
    public String toString() {
        return "VideoGame{" +
                "name='" + name + '\'' +
                ", trophyList=" + trophyList +
                '}';
    }
}
