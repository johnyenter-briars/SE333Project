package edu.depaul.models;

import java.time.Year;
import java.util.UUID;

public class Video {

    private String _movieName;

    private Integer _yearReleased;

    private Integer _numberOfMinutes;

    private Customer _owner;

    private UUID _videoId;

    public Video(){

    }

    public Video(String videoName){
        _movieName = videoName;
    }

    public Video(String videoName, Integer yearReleased, UUID id){
        _movieName = videoName;
        _yearReleased = yearReleased;
        _videoId = id;
    }

    public Video(String videoName, Integer yearReleased, Integer runTimeMins, UUID id){
        _movieName = videoName;
        _yearReleased = yearReleased;
        _videoId = id;
        _numberOfMinutes = runTimeMins;
    }

    public UUID getVideoId() {
        return _videoId;
    }

    public void setVideoId(UUID _videoId) {
        this._videoId = _videoId;
    }

    public String getMovieName() {
        return _movieName;
    }


    public String toString(){
        return String.format("%s %s %s", _movieName, _yearReleased, _videoId);
    }

}
