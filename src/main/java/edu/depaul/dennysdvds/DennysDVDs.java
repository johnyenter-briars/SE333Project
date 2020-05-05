package edu.depaul.dennysdvds;

import edu.depaul.models.*;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class DennysDVDs {

    private Warehouse _warehouse;

    private List<VideoExchange> _ledger;

    public DennysDVDs(Warehouse warehouse){
        if(warehouse == null)
            throw new IllegalArgumentException("Warehouse can not be null");

        _warehouse = warehouse;
        _ledger = new LinkedList<>();
    }

    public void printDVDs(){
        _warehouse.printCurrentlyOwnedVideos();
    }

    public Response checkoutVideo(Customer customer, String name){
        Video targetVideo = _warehouse.getVideoByNameAprox(name);

        VideoExchange videoExchange = new VideoExchange(customer, _warehouse, targetVideo);

        _ledger.add(videoExchange);

        _warehouse.remove(targetVideo);

        customer.addToCurrentlyRented(targetVideo);

        return new Response(ResponseStatus.OK);
    }

    public Response checkinVideo(Customer customer, Video video){

        VideoExchange videoExchange = new VideoExchange(customer, _warehouse, video);

        _ledger.add(videoExchange);

        customer.removeFromCurrentlyRented(video);

        _warehouse.add(video);

        return new Response(ResponseStatus.OK);
    }

    public boolean currentlyHasVideo(String videoName){
        return _warehouse.stream().anyMatch(v -> v.getMovieName().contains(videoName));
    }


}
