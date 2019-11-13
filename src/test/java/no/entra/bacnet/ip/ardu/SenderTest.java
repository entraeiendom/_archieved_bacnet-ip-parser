package no.entra.bacnet.ip.ardu;

import org.junit.jupiter.api.Test;

import static no.entra.bacnet.ip.ardu.Sender.CLIENT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SenderTest {

    @Test
    void client() {
        assertEquals(CLIENT.getSenderChar(), '0');
        assertEquals(Sender.fromSenderChar('0'), CLIENT);
        assertEquals(Sender.fromSenderChar('1'), Sender.SERVER);
        assertNull(Sender.fromSenderChar('x'));
    }
}