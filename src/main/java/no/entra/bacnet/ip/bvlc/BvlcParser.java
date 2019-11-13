package no.entra.bacnet.ip.bvlc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BvlcParser {
    private static final Logger log = LoggerFactory.getLogger( BvlcParser.class );

    public static Bvlc parseHex(String hexString) {
        char[] bacnetOctet = hexString.substring(0,2).toCharArray();
        char[] typeOctet = hexString.substring(2,3).toCharArray();
        char[] lengthHex = hexString.substring(4,7).toCharArray();
        Bvlc bvlc = new Bvlc(bacnetOctet, typeOctet, lengthHex);
        return bvlc;
    }
}
