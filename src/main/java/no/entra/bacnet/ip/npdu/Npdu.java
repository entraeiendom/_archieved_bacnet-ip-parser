package no.entra.bacnet.ip.npdu;

import java.io.Serializable;

public class Npdu implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final char[] VERSION_HEX = "01".toCharArray();
    private final char[] versionOctet;
    private final char[] controlOctet;


    public Npdu(char[] versionOctet, char[] controlOctet) {
        this.versionOctet = versionOctet;
        this.controlOctet = controlOctet;
    }

    public char[] getVersionOctet() {
        return versionOctet;
    }

    public char[] getControlOctet() {
        return controlOctet;
    }
}
