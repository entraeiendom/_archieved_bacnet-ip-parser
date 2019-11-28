package no.entra.bacnet.ip.apdu;

import org.junit.jupiter.api.Test;

import static no.entra.bacnet.ip.apdu.PduType.ConfirmedRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PduTypeOctetTest {

    @Test
    void confirmedRequest() {
        assertEquals(ConfirmedRequest.fromPduTypeOctet("00"), ConfirmedRequest);
        assertEquals(PduType.fromPduTypeOctet("00"), ConfirmedRequest);
        assertNull(PduType.fromPduTypeOctet("xx"));
    }
}