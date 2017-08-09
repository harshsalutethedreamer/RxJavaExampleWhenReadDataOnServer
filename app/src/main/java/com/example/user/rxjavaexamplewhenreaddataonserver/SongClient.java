package com.example.user.rxjavaexamplewhenreaddataonserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 8/10/2017.
 */

public class SongClient {

    public List<String> getSongs(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return getSongsFromServer();
    }

    public List<String> getSongsFromServer(){
        List<String> songs=new ArrayList<>();
        songs.add("do i love");
        songs.add("forever");
        songs.add("what the life");
        songs.add("my life is awesome");
        return songs;
    }

}
