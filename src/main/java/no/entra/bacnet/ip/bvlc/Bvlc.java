package no.entra.bacnet.ip.bvlc;

import java.io.Serializable;
import java.util.Arrays;

public class Bvlc implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final char[] BACNET_HEX = "81".toCharArray();
    private final char[] bacnetOctet;
    private final char[] typeOctet;
    private final char[] lenghtHex;

    public Bvlc(char[] bacnetOctet, char[] typeOctet, char[] lenghtHex) {
        this.bacnetOctet = bacnetOctet;
        this.typeOctet = typeOctet;
        this.lenghtHex = lenghtHex;
    }

    public boolean isBacnet() {
        return bacnetOctet != null && Arrays.equals(bacnetOctet, BACNET_HEX);
    }

    public char[] getBacnetOctet() {
        return bacnetOctet;
    }

    public char[] getTypeOctet() {
        return typeOctet;
    }

    public char[] getLenghtHex() {
        return lenghtHex;
    }
}
