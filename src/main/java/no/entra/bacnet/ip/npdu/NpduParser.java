package no.entra.bacnet.ip.npdu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NpduParser {
    private static final Logger log = LoggerFactory.getLogger( NpduParser.class );

    public static Npdu parseNpduHex(String npduHexString) {
        char[] versionOctet = npduHexString.substring(0,2).toCharArray();
        char[] controlOctet = npduHexString.substring(2,4).toCharArray();
        Npdu npdu = new Npdu(versionOctet, controlOctet);
        return npdu;
    }

    public static Npdu parseFullBacnetIpHex(String fullHexString) {
        String npduHexString = fullHexString.substring(8,12);
        Npdu npdu = parseNpduHex(npduHexString);
        return npdu;
    }
}
