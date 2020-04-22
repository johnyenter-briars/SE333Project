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

    public UUID getVideoId() {
        return _videoId;
    }

    public void setVideoId(UUID _videoId) {
        this._videoId = _videoId;
    }

    public String toString(){
        return String.format("%s %s %s", _movieName, _yearReleased, _videoId);
    }

}
