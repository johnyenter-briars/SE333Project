package edu.depaul.models;

import com.opencsv.*;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

import java.io.*;
import java.util.List;
import java.util.UUID;

public class Warehouse implements DVDOwner{

    private List<Video> _inventory;

    public Warehouse(){

    }

    public void loadVideos() throws IOException {
        File file = new File("files/Videos.csv");
        FileReader fileReader = new FileReader(file);

        CsvToBean<Video> csv = new CsvToBean<>();

        CSVReader csvReader = new CSVReader(fileReader);

        csv.setCsvReader(csvReader);
        csv.setMappingStrategy(setColumnMapping());

        List<Video> initialInventory = csv.parse();

        initialInventory.forEach((video) ->{
            video.setVideoId(UUID.randomUUID());
        });

        initialInventory.forEach(System.out::println);
    }

    private static ColumnPositionMappingStrategy<Video> setColumnMapping()
    {
        ColumnPositionMappingStrategy<Video> strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(Video.class);
        String[] columns = new String[] {"_movieName", "_yearReleased", "_numberOfMinutes"};
        strategy.setColumnMapping(columns);
        return strategy;
    }

}
