package edu.depaul.dennysdvds;

import edu.depaul.models.*;

import org.junit.jupiter.api.Test;
//import org.junit.Test;
import org.mockito.stubbing.OngoingStubbing;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;


public class DennysDVDsTest {

    //DennysDVDs under test
    @Test
    public void Test_Checkout_DennysDVDs() throws IOException {
        //Arrange
        Customer mockCustomer = mock(Customer.class);
        DennysDVDs dennysDVDs = new DennysDVDs(new Warehouse());

        //Act
        dennysDVDs.checkoutVideo(mockCustomer, "Star Wars");

        //Assert
        assertFalse(dennysDVDs.currentlyHasVideo("Star Wars"));
        verify(mockCustomer, times(1)).addToCurrentlyRented(any(Video.class));
    }

    //DennysDVDs under test
    @Test
    public void Test_CheckIn_DennysDVDs() throws IOException {
        //Arrange
        Customer mockCustomer = mock(Customer.class);
        DennysDVDs dennysDVDs = new DennysDVDs(new Warehouse());

        //Act
        dennysDVDs.checkoutVideo(mockCustomer, "Star Wars");
        dennysDVDs.checkinVideo(mockCustomer, new Video("Star Wars"));

        //Assert
        assertAll("DVD was checked in correctly", () ->{
            assertTrue(dennysDVDs.currentlyHasVideo("Star Wars"));
        }, () ->{
            assertEquals(2, dennysDVDs.getLedger().size());
        });
        verify(mockCustomer, times(1)).addToCurrentlyRented(any(Video.class));
    }

    //Functional Test
//    @Test(expected=IllegalArgumentException.class)
    @Test
    public void Test_ErrorHandling_DennysDVDs(){
        //Arrange
        //Act
        //Assert
        assertThrows(IllegalArgumentException.class, ()->{
            DennysDVDs dennysDVDs = new DennysDVDs(null);
        });
    }

    @Test
    public void Test_ValidateLedger_DennysDVDs(){
        //Arrange
        DennysDVDs dennysDVDs = new DennysDVDs(new Warehouse());
        Customer customer = new Customer();

        //Act
        dennysDVDs.checkoutVideo(customer, "Star Wars");
        dennysDVDs.checkinVideo(customer, customer.getCurrentlyOwnedVideos().get(0));

        //Assert
        assertTrue(dennysDVDs.validateLedger());
    }

    @Test
    public void Test_ValidateLedger_Invalid_DennysDVDs(){
        //Arrange
        Warehouse warehouse = new Warehouse();
        DennysDVDs dennysDVDs = new DennysDVDs(warehouse);
        Customer customer = new Customer();
        Video targetVideo = warehouse.stream().findFirst().get();

        //Act
        dennysDVDs.checkoutVideo(customer, "Titanic");
        warehouse.add(targetVideo);

        //Assert
        assertFalse(dennysDVDs.validateLedger());
    }
}
