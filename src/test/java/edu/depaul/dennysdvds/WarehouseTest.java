package edu.depaul.dennysdvds;

import edu.depaul.models.Response;
import edu.depaul.models.ResponseStatus;
import edu.depaul.models.Video;
import edu.depaul.models.Warehouse;
import org.junit.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class WarehouseTest {

    @Test(expected=IllegalArgumentException.class)
    public void Test_ErrorHandling_RemoveVideo_Warehouse() throws IOException {
        //Arrange
        Warehouse warehouse = new Warehouse();

        //Act
        //Assert
        warehouse.remove("Not a Video Object!");
    }

    @Test(expected=IllegalArgumentException.class)
    public void Test_ErrorHandling_AddVideo_Warehouse() throws IOException {
        //Arrange
        Warehouse warehouse = new Warehouse();

        //Act
        //Assert
        warehouse.add(null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void Test_ErrorHandling_ContainsVideo_Warehouse() throws IOException {
        //Arrange
        Warehouse warehouse = new Warehouse();

        //Act
        //Assert
        warehouse.contains("Not a video object!");
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
