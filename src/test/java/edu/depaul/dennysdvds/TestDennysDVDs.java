package edu.depaul.dennysdvds;

import edu.depaul.models.*;

import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class TestDennysDVDs {

    @Test
    void Test_Checkout_Customer() throws IOException {
        //Arrange
        DennysDVDs dennysDVDs = new DennysDVDs(new Warehouse());
        Customer customer1 = new Customer();

        //Act
        dennysDVDs.checkoutVideo(customer1, "Star Wars");

        //Assert
        assertTrue(customer1.currentlyHasVideo("Star Wars"));
        assertFalse(dennysDVDs.currentlyHasVideo("Star Wars"));
    }

    @Test
    void Test_CheckIn_Customer() throws IOException {
        //Arrange
        DennysDVDs dennysDVDs = new DennysDVDs(new Warehouse());
        Customer customer1 = new Customer();

        //Act
        dennysDVDs.checkoutVideo(customer1, "Star Wars");

        //Assert
        assertTrue(customer1.currentlyHasVideo("Star Wars"));
        dennysDVDs.checkinVideo(customer1, customer1.getCurrentlyRented().get(0));
        assertFalse(customer1.currentlyHasVideo("Star Wars"));
    }

    @Test
    void Test_Checkout_DennysDVDs() throws IOException {
        //Arrange
        Customer mockCustomer = mock(Customer.class);
        DennysDVDs dennysDVDs = new DennysDVDs(new Warehouse());

        //Act
        dennysDVDs.checkoutVideo(mockCustomer, "Star Wars");

        //Assert
        assertFalse(dennysDVDs.currentlyHasVideo("Star Wars"));
    }

    @Test
    void Test_CheckIn_DennysDVDs() throws IOException {
        //Arrange
        Customer mockCustomer = mock(Customer.class);
        DennysDVDs dennysDVDs = new DennysDVDs(new Warehouse());

        //Act
        dennysDVDs.checkoutVideo(mockCustomer, "Star Wars");
        dennysDVDs.checkinVideo(mockCustomer, new Video("Star Wars"));

        //Assert
        assertTrue(dennysDVDs.currentlyHasVideo("Star Wars"));
        assertEquals(2, dennysDVDs.getLedger().size());
    }

}
