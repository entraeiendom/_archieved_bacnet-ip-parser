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
        assertArrayEquals( "81".toCharArray(), bvlc.getBacnetOctet());
        assertArrayEquals( "0b".toCharArray(), bvlc.getFunctionOctet());
        assertArrayEquals( "0007".toCharArray(), bvlc.getNumberOfBvllOctetsLength());
    }

    @Test
    void findLenght() {
        String hexString = "810b0007018000";
        Bvlc bvlc = BvlcParser.parseHex(hexString);
        assertEquals(7, bvlc.findExpectdNumberOfOctetsInBvll());
        String longerHexString = "810a003a010030020c0c020004d2194c3ec4020004d2c40c405c00001c405000001c401800000c400800002c40080000" +
                "3c4044000003f6574344a4f29a74e247fffffff4f1f00";
        Bvlc longer = BvlcParser.parseHex(longerHexString);
        assertEquals(58, longer.findExpectdNumberOfOctetsInBvll());
    }
}