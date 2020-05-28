package edu.depaul.models;

public class VideoExchange {

    private DVDOwner _goingTo;

    private DVDOwner _comingFrom;

    private Video _video;

    public VideoExchange(DVDOwner goingTo, DVDOwner comingFrom, Video video){
        if(goingTo.getClass() == comingFrom.getClass())
            throw new IllegalArgumentException("The same type of object cant be passing a video object to itself");

        _goingTo = goingTo;
        _comingFrom = comingFrom;
        _video = video;
    }

    public DVDOwner get_goingTo() {
        return _goingTo;
    }

    public DVDOwner get_comingFrom() {
        return _comingFrom;
    }

    public Video get_video(){ return _video; }

    public boolean matchingExchange(VideoExchange otherExchange){
        return _goingTo == otherExchange.get_comingFrom() && _comingFrom == otherExchange.get_goingTo();
    }
}
