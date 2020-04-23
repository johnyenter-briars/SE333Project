package edu.depaul.models;

import com.opencsv.*;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

import java.io.*;
import java.util.*;
import java.util.function.Consumer;

public class Warehouse implements DVDOwner, Iterable<Video>, Collection<Video>{

    private List<Video> _inventory;

    public Warehouse() throws IOException {
        loadVideos();
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

        _inventory = initialInventory;
    }

    private static ColumnPositionMappingStrategy<Video> setColumnMapping()
    {
        ColumnPositionMappingStrategy<Video> strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(Video.class);
        String[] columns = new String[] {"_movieName", "_yearReleased", "_numberOfMinutes"};
        strategy.setColumnMapping(columns);
        return strategy;
    }

    public Video getVideoByNameAprox(String name){
        return _inventory.stream().filter(v -> v.get_movieName().contains(name)).findAny().orElse(null);
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<Video> iterator() {
        return _inventory.iterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return null;
    }

    @Override
    public boolean add(Video video) {
        if(video == null)
            return false;
        _inventory.add(video);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if(! (o instanceof Video))
            throw new IllegalArgumentException()
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Video> collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public void forEach(Consumer<? super Video> action) {
        _inventory.forEach(action);
    }

    @Override
    public Spliterator<Video> spliterator() {
        return _inventory.spliterator();
    }
}
