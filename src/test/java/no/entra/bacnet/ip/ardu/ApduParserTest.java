package no.entra.bacnet.ip.ardu;

import no.entra.bacnet.ip.UdpParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ApduParserTest {


    @BeforeEach
    void setUp() {

    }

    @Test
    void findObjectName() {
        String bvlc = "810a0026";
        String npdu = "0100";
        String apdu =  "309d0c0c02002dc0194d3e751200";
        String objectName = "4b4e58204761746577617920332e6574673f";
        String hexUdpMessage = bvlc + npdu + apdu + objectName;
        byte[] apduBytes = UdpParser.findApdu(hexUdpMessage);
        System.out.println(apduBytes.toString());
    }
}