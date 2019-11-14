package no.entra.bacnet.ip.apdu;

import no.entra.bacnet.ip.apdu.service.ObjectIdentifier;
import no.entra.bacnet.ip.apdu.service.UnconfirmedCovNotificationServiceDescription;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;

import static no.entra.bacnet.ip.apdu.PduType.ComplexAck;
import static no.entra.bacnet.ip.apdu.PduType.UnconfirmedRequest;
import static no.entra.bacnet.ip.apdu.ServiceChoice.UnconfirmedCovNotification;
import static no.entra.bacnet.ip.apdu.service.ObjectType.DEVICE;
import static org.junit.jupiter.api.Assertions.*;

class ApduParserTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void findObjectName() {
        String bvlc = "810a0026";
        String npdu = "0100";
        String apduHex =  "30010c0c02002dc0194d3e751200";
        String objectName = "4b4e58204761746577617920332e6574673f";
        String hexUdpMessage = bvlc + npdu + apduHex + objectName;
//        hexUdpMessage = "810a003a010030020c0c020004d2194c3ec4020004d2c40c405c00001c405000001c401800000c400800002c400800003c4044000003f6574344a4f29a74e247fffffff4f1f00";
       Apdu apdu = ApduParser.parseFullBacnetIpHex(hexUdpMessage);
       assertNotNull(apdu);
       assertEquals(ComplexAck,apdu.getPduType());
       assertEquals(Sender.CLIENT, apdu.getSender());
    }

    @Test
    void findOperational() {
        String bvlc = "810b0034";
        String npdu = "0100";
        String apduHex = "100209001c020007d12c020007d139004e09702e91002f09cb2e"+
                "2ea4770b0105b40d2300442f2f09c42e91002f4f";
        //decoded: 810b00340100100209001c020007d12c020007d1
        String hexUdpMessage = bvlc + npdu + apduHex;
        UnconfirmedRequestApdu apdu = (UnconfirmedRequestApdu) ApduParser.parseFullBacnetIpHex(hexUdpMessage);
        assertNotNull(apdu);
        assertEquals(UnconfirmedRequest,apdu.getPduType());
        assertEquals(UnconfirmedCovNotification, apdu.getServiceChoice());
        UnconfirmedCovNotificationServiceDescription serviceDescription = (UnconfirmedCovNotificationServiceDescription) apdu.getServiceDescription();
        assertNotNull(serviceDescription);
        assertTrue(serviceDescription.hasProcessIdentifier()); //09
        assertEquals(0, serviceDescription.getProcessIdentifierNumber()); //00
        List<ObjectIdentifier> objectIdentifiers = serviceDescription.getObjectIds();
        assertNotNull(objectIdentifiers);
        assertEquals(2,objectIdentifiers.size());
        ObjectIdentifier initiatingDeviceIdentifier = objectIdentifiers.get(0);
        assertTrue(initiatingDeviceIdentifier.isInitiating());
        assertEquals(2001, initiatingDeviceIdentifier.getInstanceNumber());
        assertEquals(DEVICE, initiatingDeviceIdentifier.getObjectType());
        ObjectIdentifier monitoredDeviceIdentifier = objectIdentifiers.get(1);
        assertTrue(monitoredDeviceIdentifier.isMonitored());
        assertEquals(2001, monitoredDeviceIdentifier.getInstanceNumber());
        assertEquals(DEVICE, monitoredDeviceIdentifier.getObjectType());
        assertEquals(Duration.ZERO, serviceDescription.getTimeRemaining());
        //3909
        /*
        ProcessIdentifier (0)
        ObjectIdentifier device, 2001
        ObjectIdentifier device, 2001
        Time remaining (indefinite)
        List of Values (4e)
        system-status....Property-Identifier(112), se 862 112==9, her 09
        	Ñ,Ñ9N	p./	Ë..¤w´
#D//	Ä./O
         */
    }
}