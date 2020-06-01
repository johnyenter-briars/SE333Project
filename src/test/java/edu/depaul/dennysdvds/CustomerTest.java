package edu.depaul.dennysdvds;

import edu.depaul.models.Customer;
import edu.depaul.models.Warehouse;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    //Customer under test
    @Test
    public void Test_Checkout_Customer() throws IOException {
        //Arrange
        DennysDVDs dennysDVDs = new DennysDVDs(new Warehouse());
        Customer customer1 = new Customer();

        //Act
        dennysDVDs.checkoutVideo(customer1, "Star Wars");

        //Assert
        assertAll("Video was checked out correctly", ()->{
            assertTrue(customer1.currentlyHasVideo("Star Wars"));
        }, ()->{
            assertFalse(dennysDVDs.currentlyHasVideo("Star Wars"));
        });
    }

    //Customer under test
    @Test
    public void Test_CheckIn_Customer() throws IOException {
        //Arrange
        DennysDVDs dennysDVDs = new DennysDVDs(new Warehouse());
        Customer customer1 = new Customer();

        //Act
        dennysDVDs.checkoutVideo(customer1, "Star Wars");

        //Assert
        assertTrue(customer1.currentlyHasVideo("Star Wars"));
        dennysDVDs.checkinVideo(customer1, customer1.getCurrentlyOwnedVideos().get(0));
        assertFalse(customer1.currentlyHasVideo("Star Wars"));
    }
}
