package no.entra.bacnet.ip.apdu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApduParser {
    private static final Logger log = LoggerFactory.getLogger( ApduParser.class );

    public static Apdu parseApduHex(String apduHexString) {
        char pduTypeChar = apduHexString.charAt(0);
        char sender = apduHexString.charAt(1);
        char[] invokeId = apduHexString.substring(2,4).toCharArray();
        char[] serviceChoice = apduHexString.substring(4,6).toCharArray();
        Apdu apdu = new Apdu(pduTypeChar, sender, invokeId, serviceChoice);
        return apdu;
    }

    public static Apdu parseFullBacnetIpHex(String fullHexString) {
        String apduHexString = fullHexString.substring(12);
        Apdu Apdu = parseApduHex(apduHexString);
        return Apdu;
    }
}
