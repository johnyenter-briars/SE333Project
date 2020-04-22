import edu.depaul.models.Warehouse;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println("Hello there!!");

        Warehouse warehouse = new Warehouse();

        warehouse.loadVideos();
    }
}
