package edu.depaul.models;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Customer implements DVDOwner{

    private String _firstName;

    private String _lastName;

    private Integer _age;

    private UUID _customerId;

    private List<Video> _currentlyRented;

    public Customer(){
        _currentlyRented = new LinkedList<>();
    }

    public void addToCurrentlyRented(Video video){ _currentlyRented.add(video); }

    public void removeFromCurrentlyRented(Video video){ _currentlyRented.remove(video); }

    public boolean currentlyHasVideo(String videoName){
        return getCurrentlyOwnedVideos().stream().anyMatch(v -> v.getMovieName().contains(videoName));
    }

    @Override
    public List<Video> getCurrentlyOwnedVideos() { return _currentlyRented; }
}
