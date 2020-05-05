package edu.depaul.dennysdvds;

import edu.depaul.models.Customer;
import edu.depaul.models.Warehouse;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TestDennysDVDs {

    @Test
    void Test_CheckoutFeature() throws IOException {
        DennysDVDs dennysDVDs = new DennysDVDs(new Warehouse());

        Customer customer1 = new Customer();

        dennysDVDs.checkoutVideo(customer1, "Star Wars");

        assertTrue(customer1.currentlyHasVideo("Star Wars"));

        assertFalse(dennysDVDs.currentlyHasVideo("Star Wars"));
    }

    @Test
    void Test_CheckInFeature() throws IOException {
        DennysDVDs dennysDVDs = new DennysDVDs(new Warehouse());

        Customer customer1 = new Customer();

        dennysDVDs.checkoutVideo(customer1, "Star Wars");

        assertTrue(customer1.currentlyHasVideo("Star Wars"));

        assertFalse(dennysDVDs.currentlyHasVideo("Star Wars"));

        dennysDVDs.checkinVideo(customer1, customer1.getCurrentlyRented().get(0));

        assertFalse(customer1.currentlyHasVideo("Star Wars"));

        assertTrue(dennysDVDs.currentlyHasVideo("Star Wars"));
    }

}
