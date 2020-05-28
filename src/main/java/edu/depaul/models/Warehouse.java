package edu.depaul.models;

import com.opencsv.*;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Consumer;

public class Warehouse implements DVDOwner, Iterable<Video>, Collection<Video>{

    private List<Video> _inventory = new LinkedList<>();

    private List<String> _validVideoSources =
            new LinkedList<String>(Arrays.asList("files/Videos.csv", "files/MoreVideos.csv"));

    public Warehouse(){
        loadVideos(_validVideoSources.get(0));
    }

    public void loadVideos(String path) {
        if(!_validVideoSources.contains(path))
            throw new IllegalArgumentException("Trying to obtain videos from an incorrect path!");

        try{
            File file = new File(path);
            FileReader fileReader = new FileReader(file);

            CsvToBean<Video> csv = new CsvToBean<>();

            CSVReader csvReader = new CSVReader(fileReader);

            csv.setCsvReader(csvReader);
            csv.setMappingStrategy(setColumnMapping());

            List<Video> initialInventory = csv.parse();

            initialInventory.forEach((video) ->{
                video.setVideoId(UUID.randomUUID());
            });

            _inventory.addAll(initialInventory);
        }catch (IOException e){
            System.out.println("Error while loading files!" + e);
        }
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
        return _inventory.stream().filter(v -> v.getMovieName().contains(name)).findAny().orElse(null);
    }

    public void addMoreVideosToInventory() {
        loadVideos(_validVideoSources.get(1));
    }

    @Override
    public int size() {
        return _inventory.size();
    }

    @Override
    public boolean isEmpty() {
        return _inventory.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        if(!(o instanceof Video))
            throw new IllegalArgumentException("You can only search for Videos on the Warehouse object");
        return _inventory.contains(o);
    }

    @Override
    public Iterator<Video> iterator() {
        return _inventory.iterator();
    }

    @Override
    public Object[] toArray() {
        return _inventory.toArray();
    }

    // TODO: 4/24/20
    @Override
    public <T> T[] toArray(T[] ts) {
        return null;
    }

    @Override
    public boolean add(Video video) {
        if(video == null)
            throw new IllegalArgumentException("Video parameter must not be null!");
        return _inventory.add(video);
    }

    @Override
    public boolean remove(Object o) {
        if(!(o instanceof Video))
            throw new IllegalArgumentException("Must remove a Video object from the warehouse!");
        return _inventory.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return _inventory.containsAll(collection);
    }

    @Override
    public boolean addAll(Collection<? extends Video> collection) {
        return _inventory.addAll(collection);
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return _inventory.removeAll(collection);
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return _inventory.retainAll(collection);
    }

    @Override
    public void clear() { _inventory.clear(); }

    @Override
    public void forEach(Consumer<? super Video> action) {
        _inventory.forEach(action);
    }

    @Override
    public Spliterator<Video> spliterator() {
        return _inventory.spliterator();
    }

    @Override
    public void printCurrentlyOwnedVideos() {
        _inventory.forEach(System.out::println);
    }
}
