import edu.depaul.dennysdvds.DennysDVDs;
import edu.depaul.models.Customer;
import edu.depaul.models.Warehouse;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        DennysDVDs dennysDVDs = new DennysDVDs(new Warehouse());

        dennysDVDs.printDVDs();

        Customer customer1 = new Customer();




    }
}
