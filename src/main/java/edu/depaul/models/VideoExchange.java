package edu.depaul.models;

public class VideoExchange {

    private DVDOwner _goingTo;

    private DVDOwner _comingFrom;

    public VideoExchange(){

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
}
