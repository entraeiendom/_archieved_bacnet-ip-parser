package no.entra.bacnet.ip.ardu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static no.entra.bacnet.ip.ardu.PduType.ConfirmedRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PduTypeTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void confirmedRequest() {
        assertEquals(ConfirmedRequest.getPduTypeChar(), '0');
        assertEquals(PduType.fromPduTypeChar('0'), ConfirmedRequest);
        assertNull(PduType.fromPduTypeChar('x'));
    }
}