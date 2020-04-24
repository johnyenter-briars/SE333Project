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

    public UUID get_customerId() {
        return _customerId;
    }

    public void set_customerId(UUID _customerId) {
        this._customerId = _customerId;
    }

    public List<Video> get_currentlyRented() {
        return _currentlyRented;
    }

    public void addToCurrentlyRented(Video video){ _currentlyRented.add(video); }

    public void removeFromCurrentlyRented(Video video){ _currentlyRented.remove(video); }

    public String toString(){
        return _firstName + " " + _lastName + " " + _customerId;
    }

    @Override
    public void printCurrentlyOwnedVideos() {
        _currentlyRented.forEach(System.out::println);
    }
}
