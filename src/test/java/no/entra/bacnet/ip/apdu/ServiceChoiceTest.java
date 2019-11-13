package no.entra.bacnet.ip.apdu;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ServiceChoiceTest {

    @Test
    void fromServiceChoiceHex() {
        char[] serviceChoiceHex = "02".toCharArray();
        ServiceChoice unconfirmed = ServiceChoice.fromServiceChoiceHex(serviceChoiceHex);
        assertNotNull(unconfirmed);
        assertEquals(ServiceChoice.UnconfirmedCovNotification, unconfirmed);
    }

    @Test
    void charEquals() {
        char[] undonfirmedHex = "02".toCharArray();
        assertTrue(Arrays.equals(ServiceChoice.UnconfirmedCovNotification.getServiceChoice(), undonfirmedHex));
    }
}