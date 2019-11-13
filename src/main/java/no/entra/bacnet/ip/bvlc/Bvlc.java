package no.entra.bacnet.ip.bvlc;

import java.io.Serializable;
import java.util.Arrays;

public class Bvlc implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final char[] BACNET_HEX = "81".toCharArray();
    private final char[] bacnetOctet;
    private final char[] functionOctet;
    private final char[] numberOfBvllOctetsLength;

    public Bvlc(char[] bacnetOctet, char[] functionOctet, char[] numberOfBvllOctetsLength) {
        this.bacnetOctet = bacnetOctet;
        this.functionOctet = functionOctet;
        this.numberOfBvllOctetsLength = numberOfBvllOctetsLength;
    }

    public boolean isBacnet() {
        return bacnetOctet != null && Arrays.equals(bacnetOctet, BACNET_HEX);
    }

    public char[] getBacnetOctet() {
        return bacnetOctet;
    }

    public char[] getFunctionOctet() {
        return functionOctet;
    }

    public char[] getNumberOfBvllOctetsLength() {
        return numberOfBvllOctetsLength;
    }

    public int findExpectdNumberOfOctetsInBvll() {
        String length = String.valueOf(numberOfBvllOctetsLength);
        return Integer.parseInt(length, 16);
    }
}
