package no.entra.bacnet.ip.bvlc;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BvlcParserTest {

    @Test
    void parseHex() {
        String hexString = "810b0007018000";
        Bvlc bvlc = BvlcParser.parseHex(hexString);
        assertNotNull(bvlc);
        assertTrue(bvlc.isBacnet());
        assertTrue(Arrays.equals(bvlc.getBacnetOctet(), "81".toCharArray()));
    }
}