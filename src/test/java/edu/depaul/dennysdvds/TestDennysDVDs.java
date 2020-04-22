package edu.depaul.dennysdvds;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDennysDVDs {

    @Test
    void testDenny(){
        DennysDVDs dennysDVDs = new DennysDVDs();

        assertEquals(5, dennysDVDs.getFive());
    }

}
