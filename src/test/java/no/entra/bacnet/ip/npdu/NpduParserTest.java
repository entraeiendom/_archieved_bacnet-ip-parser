package no.entra.bacnet.ip.npdu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class NpduParserTest {

    private static final String BACNET_HEX_STRING = "810b0007018000";
    private Npdu expectedNpdu;

    @BeforeEach
    void setUp() {
        expectedNpdu = new Npdu("01".toCharArray(), "80".toCharArray());
    }

    @Test
    void parseNpduHex() {
        Npdu npdu = NpduParser.parseNpduHex("0180");
        assertNotNull(npdu);
        assertArrayEquals(expectedNpdu.getVersionOctet(), npdu.getVersionOctet());
        assertArrayEquals(expectedNpdu.getControlOctet(), npdu.getControlOctet());
    }

    @Test
    void parseFullBacnetIpHex() {
        Npdu npdu = NpduParser.parseFullBacnetIpHex(BACNET_HEX_STRING);
        assertNotNull(npdu);
        assertArrayEquals(expectedNpdu.getVersionOctet(), npdu.getVersionOctet());
        assertArrayEquals(expectedNpdu.getControlOctet(), npdu.getControlOctet());
    }
}