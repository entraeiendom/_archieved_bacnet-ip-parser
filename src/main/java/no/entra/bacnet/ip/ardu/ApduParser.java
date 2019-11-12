package no.entra.bacnet.ip.ardu;

public class ApduParser {

    private final byte[] hexcontent;

    public ApduParser(byte[] hexcontent) {
        this.hexcontent = hexcontent;
    }

    public String findObjectName() {
        return "hei";
    }
}
