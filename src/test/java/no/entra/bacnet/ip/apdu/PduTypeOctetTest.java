package no.entra.bacnet.ip.apdu;

import no.entra.bacnet.ip.Octet;
import org.junit.jupiter.api.Test;

import static no.entra.bacnet.ip.apdu.PduType.ConfirmedRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PduTypeOctetTest {

    @Test
    void confirmedRequest() {
        Octet pduType = new Octet("00");
        assertEquals(ConfirmedRequest.fromPduTypeOctet(pduType), ConfirmedRequest);
        assertEquals(PduType.fromPduTypeOctet(pduType), ConfirmedRequest);
        assertNull(PduType.fromPduTypeOctet(null));
    }
}