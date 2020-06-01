package edu.depaul.dennysdvds;

import edu.depaul.models.Customer;
import edu.depaul.models.Video;
import edu.depaul.models.VideoExchange;
import edu.depaul.models.Warehouse;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;

public class VariousObjTest {

    @Test
    void Test_VideoString(){
        //Arrange
        UUID id = UUID.randomUUID();
        Video video = new Video("Star Wars", 1977, id);
        //Act
        //Assert
        assertAll("Video string is properly formatted", ()->{
            assertEquals(String.format("Star Wars 1977 %s", id.toString()), video.toString());
        }, ()->{
            assertEquals(id, video.getVideoId());
        });
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
        assertAll("Video exchanges are matching", ()->{
            assertTrue(videoExchange1.matchingExchange(videoExchange2));
        }, ()->{
            assertTrue(videoExchange2.matchingExchange(videoExchange1));
        });
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
        assertAll("Video exchanges are not matching", ()->{
            assertFalse(videoExchange1.matchingExchange(videoExchange2));
        }, ()->{
            assertFalse(videoExchange2.matchingExchange(videoExchange1));
        });
    }
}
