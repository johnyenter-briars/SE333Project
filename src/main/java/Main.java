import edu.depaul.dennysdvds.DennysDVDs;
import edu.depaul.models.Customer;
import edu.depaul.models.Warehouse;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        DennysDVDs dennysDVDs = new DennysDVDs(new Warehouse());

        Customer customer1 = new Customer();

        dennysDVDs.checkoutVideo(customer1, "Star Wars");

        customer1.printCurrentlyOwnedVideos();

        System.out.println("-------");

        dennysDVDs.printDVDs();

    }
}
