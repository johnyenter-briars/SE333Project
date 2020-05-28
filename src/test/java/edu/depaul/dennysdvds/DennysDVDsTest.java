package edu.depaul.dennysdvds;

import edu.depaul.models.*;

import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;


public class DennysDVDsTest {

    //Customer under test
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

    //Customer under test
    @Test
    void Test_CheckIn_Customer() throws IOException {
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

    //DennysDVDs under test
    @Test
    void Test_Checkout_DennysDVDs() throws IOException {
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
        verify(mockCustomer, times(1)).addToCurrentlyRented(any(Video.class));
    }

    @Test
    void Test_ErrorHandling_DennysDVDs(){
        //Arrange
        //Act
        //Assert
        assertThrows(IllegalArgumentException.class, () ->{
            DennysDVDs dennysDVDs = new DennysDVDs(null);
        });
    }

    @Test
    void Test_ErrorHandling_RemoveVideo_Warehouse() throws IOException {
        //Arrange
        Warehouse warehouse = new Warehouse();

        //Act
        //Assert
        assertThrows(IllegalArgumentException.class, ()->{
            warehouse.remove("Not a Video Object!");
        });
    }

    @Test
    void Test_ErrorHandling_AddVideo_Warehouse() throws IOException {
        //Arrange
        Warehouse warehouse = new Warehouse();

        //Act
        //Assert
        assertThrows(IllegalArgumentException.class, ()->{
            warehouse.add(null);
        });
    }

    @Test
    void Test_ErrorHandling_ContainsVideo_Warehouse() throws IOException {
        //Arrange
        Warehouse warehouse = new Warehouse();

        //Act
        //Assert
        assertThrows(IllegalArgumentException.class, ()->{
            warehouse.contains("Not a video object!");
        });
    }

    @Test
    void Test_ContainsVideo_Warehouse() throws IOException {
        //Arrange
        Warehouse warehouse = new Warehouse();

        //Act
        //Assert
        for (Video vid : warehouse) {
            assertTrue(warehouse.contains(vid));
        }
    }

    @Test
    void Test_GetResponseStatus(){
        //Arrange
        Response response = new Response(ResponseStatus.OK);

        //Act
        //Assert
        assertEquals(ResponseStatus.OK, response.getStatus());
    }

    @Test
    void Test_VideoString(){
        //Arrange
        UUID id = UUID.randomUUID();
        Video video = new Video("Star Wars", 1977, id);
        //Act
        //Assert
        assertEquals(String.format("Star Wars 1977 %s", id.toString()), video.toString());
        assertEquals(id, video.getVideoId());
    }

    @Test
    void Test_TypeMismatch_VideoExchange(){
        //Arrange
        Video video = new Video("Star Wars", 1977, UUID.randomUUID());
        //Act
        //Assert
        assertThrows(IllegalArgumentException.class, () ->{
            VideoExchange videoExchange = new VideoExchange(new Customer(), new Customer(), video);
        });
    }

    @Test
    void Test_Matching_VideoExchange(){
        //Arrange
        Customer customer = mock(Customer.class);
        Warehouse warehouse = mock(Warehouse.class);
        VideoExchange videoExchange1 =
                new VideoExchange(customer, warehouse, new Video("Star Wars", 1977, UUID.randomUUID()));
        VideoExchange videoExchange2 =
                new VideoExchange(warehouse, customer, new Video("Star Wars", 1977, UUID.randomUUID()));

        //Act
        //Assert
        assertTrue(videoExchange1.matchingExchange(videoExchange2));
        assertTrue(videoExchange2.matchingExchange(videoExchange1));
    }

    @Test
    void Test_NotMatching_VideoExchange(){
        //Arrange
        Customer customer = mock(Customer.class);
        Warehouse warehouse = mock(Warehouse.class);
        VideoExchange videoExchange1 =
                new VideoExchange(customer, warehouse, new Video("Star Wars", 1977, UUID.randomUUID()));
        VideoExchange videoExchange2 =
                new VideoExchange(customer, warehouse, new Video("Star Wars", 1977, UUID.randomUUID()));

        //Act
        //Assert
        assertFalse(videoExchange1.matchingExchange(videoExchange2));
        assertFalse(videoExchange2.matchingExchange(videoExchange1));
    }

    @Test
    void Test_AddMoreVideos_Warehouse(){
        //Arrange
        Warehouse warehouse = new Warehouse();
        assertEquals(3, warehouse.size());

        //Act
        warehouse.addMoreVideosToInventory();

        //Assert
        assertTrue(warehouse.size() > 3);

    }

    @Test
    void Test_ValidateLedger_DennysDVDs(){
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
    void Test_ValidateLedger_Invalid_DennysDVDs(){
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

    @Test
    void Test_AddMoreVideos_DennysDVDs_Warehouse(){
        //Arrange
        Warehouse warehouse = new Warehouse();
        DennysDVDs dennysDVDs = new DennysDVDs(warehouse);

        //Act
        dennysDVDs.addMoreVideosToStock();

        //Assert
        assertEquals(8, warehouse.size());
    }
}
