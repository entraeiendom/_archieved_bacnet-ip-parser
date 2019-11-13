package no.entra.bacnet.ip.utils;

public class HexParser {

    public static String toHexString(byte octet) {
        return String.format("%02x", octet);
    }

    public static String toHexString(char[] hex) {
        return String.valueOf(hex);
    }
    public static String toText(char[] hex) {
        String hexString = String.format("%02x", hex);
        String theString = hexString;
        return theString;
    }
}
