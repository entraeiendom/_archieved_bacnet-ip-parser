package no.entra.bacnet.ip.apdu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ApduParserTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void findObjectName() {
        String bvlc = "810a0026";
        String npdu = "0100";
        String apduHex =  "309d0c0c02002dc0194d3e751200";
        String objectName = "4b4e58204761746577617920332e6574673f";
        String hexUdpMessage = bvlc + npdu + apduHex + objectName;
       Apdu apdu = ApduParser.parseFullBacnetIpHex(hexUdpMessage);
       assertNotNull(apdu);
    }
}