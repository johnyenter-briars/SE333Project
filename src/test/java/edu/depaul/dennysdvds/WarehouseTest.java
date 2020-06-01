package edu.depaul.dennysdvds;

import edu.depaul.models.Response;
import edu.depaul.models.ResponseStatus;
import edu.depaul.models.Video;
import edu.depaul.models.Warehouse;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class WarehouseTest {

    @Test
    public void Test_ErrorHandling_RemoveVideo_Warehouse() throws IOException {
        //Arrange
        Warehouse warehouse = new Warehouse();

        //Act
        //Assert
        assertThrows(IllegalArgumentException.class, ()->{
            warehouse.remove("Not a Video Object!");
        });
    }

    @Test
    public void Test_ErrorHandling_AddVideo_Warehouse() throws IOException {
        //Arrange
        Warehouse warehouse = new Warehouse();

        //Act
        //Assert
        assertThrows(IllegalArgumentException.class, ()->{
            warehouse.add(null);
        });
    }

    @Test
    public void Test_ErrorHandling_ContainsVideo_Warehouse() throws IOException {
        //Arrange
        Warehouse warehouse = new Warehouse();

        //Act
        //Assert
        assertThrows(IllegalArgumentException.class, ()->{
            warehouse.contains("Not a video object!");
        });
    }

    @Test
    public void Test_ContainsVideo_Warehouse() throws IOException {
        //Arrange
        Warehouse warehouse = new Warehouse();

        //Act
        //Assert
        for (Video vid : warehouse) {
            assertTrue(warehouse.contains(vid));
        }
    }

    @Test
    public void Test_AddMoreVideos_Warehouse(){
        //Arrange
        Warehouse warehouse = new Warehouse();
        assertEquals(3, warehouse.size());

        //Act
        warehouse.addMoreVideosToInventory();

        //Assert
        assertTrue(warehouse.size() > 3);
    }

    @Test
    public void Test_AddMoreVideos_DennysDVDs_Warehouse(){
        //Arrange
        Warehouse warehouse = new Warehouse();
        DennysDVDs dennysDVDs = new DennysDVDs(warehouse);

        //Act
        dennysDVDs.addMoreVideosToStock();

        //Assert
        assertEquals(8, warehouse.size());
    }
}
