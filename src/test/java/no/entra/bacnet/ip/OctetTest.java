package no.entra.bacnet.ip;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static no.entra.bacnet.ip.Octet.isValidHexChar;
import static org.junit.jupiter.api.Assertions.*;

class OctetTest {

    @Test
    void constructor() {
        assertThrows(IllegalArgumentException.class, () -> {new Octet(null);});
        String octetHexString = "0e";
        Octet octet = new Octet(octetHexString);
        assertNotNull(octet);
        char [] expected = {'0','e'};
        assertTrue(Arrays.equals(expected,octet.getOctet()));
    }

    @Test
    void isValidHexCharTest() {
        assertTrue(isValidHexChar('a'));
        assertFalse(isValidHexChar('g'));
        assertTrue(isValidHexChar('0'));
    }
}