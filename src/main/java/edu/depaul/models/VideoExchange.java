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

    public void set_goingTo(DVDOwner _goingTo) {
        this._goingTo = _goingTo;
    }

    public DVDOwner get_comingFrom() {
        return _comingFrom;
    }

    public void set_comingFrom(DVDOwner _comingFrom) {
        this._comingFrom = _comingFrom;
    }

    public Video get_video(){ return _video; }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof VideoExchange))
            throw new IllegalArgumentException("You can only compare video exchanes with each other!");
        VideoExchange other = (VideoExchange) obj;
        return _goingTo == other.get_goingTo() && _comingFrom == other.get_comingFrom() && _video == other.get_video();
    }
}
