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
        _warehouse.forEach(System.out::println);
    }

    public Response checkoutVideo(Customer customer, String name){
        Video targetVideo = _warehouse.getVideoByNameAprox(name);

        VideoExchange videoExchange = new VideoExchange(customer, _warehouse, targetVideo);

        _ledger.add(videoExchange);

        _warehouse.remove(targetVideo);

        return null;
    }


}
