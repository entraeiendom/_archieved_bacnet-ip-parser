package no.entra.bacnet.ip.apdu;

public enum  PduTypeOctet {
    ConfirmedRequest("00"),
    UnconfirmedRequest("10"),
    SimpleAck("20"),
    ComplexAck("30");
//    SegmentACK('4'),
//    Error('5'),
//    Reject('6'),
//    Abort('7');

    private String pduTypeOctet;

    public static PduTypeOctet fromPduTypeOctet(String pduTypeChar) {
        for (PduTypeOctet type : values()) {
            if (type.getPduTypeOctet().equals(pduTypeChar)) {
                return type;
            }
        }
        return null;
    }

    public String getPduTypeOctet() {
        return pduTypeOctet;
    }

    // enum constructor - cannot be public or protected
    private PduTypeOctet(String pduTypeOctet) {
        this.pduTypeOctet = pduTypeOctet;
    }
}
