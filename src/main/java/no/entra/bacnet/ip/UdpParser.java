package no.entra.bacnet.ip;

public class UdpParser {

    private static final int APDU_START_IN_UDP = 12;

    public static byte[] findApdu(String hexUdpMessage) {
        String apduMessage = hexUdpMessage.substring(APDU_START_IN_UDP);
        return apduMessage.getBytes();
    }
}
