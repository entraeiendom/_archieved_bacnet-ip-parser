package no.entra.bacnet.ip.apdu;

import org.junit.jupiter.api.Test;

import static no.entra.bacnet.ip.apdu.PduType.ConfirmedRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PduTypeTest {

    @Test
    void confirmedRequest() {
        assertEquals(ConfirmedRequest.getPduTypeChar(), '0');
        assertEquals(PduType.fromPduTypeChar('0'), ConfirmedRequest);
        assertNull(PduType.fromPduTypeChar('x'));
    }
}