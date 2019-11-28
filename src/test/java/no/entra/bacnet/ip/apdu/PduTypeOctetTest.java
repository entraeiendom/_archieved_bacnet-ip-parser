package no.entra.bacnet.ip.apdu;

import org.junit.jupiter.api.Test;

import static no.entra.bacnet.ip.apdu.PduTypeOctet.ConfirmedRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PduTypeOctetTest {

    @Test
    void confirmedRequest() {
        assertEquals(ConfirmedRequest.getPduTypeOctet(), "00");
        assertEquals(PduTypeOctet.fromPduTypeOctet("00"), ConfirmedRequest);
        assertNull(PduTypeOctet.fromPduTypeOctet("xx"));
    }
}