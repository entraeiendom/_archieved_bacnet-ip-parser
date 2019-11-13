package no.entra.bacnet.ip.apdu;

public enum PduType {
    ConfirmedRequest('0'), ComplexAck('3');

    private char pduTypeChar;

    public static PduType fromPduTypeChar(char pduTypeChar) {
        for (PduType type : values()) {
            if (type.getPduTypeChar() == pduTypeChar) {
                return type;
            }
        }
        return null;
    }

    public char getPduTypeChar() {
        return pduTypeChar;
    }

    // enum constructor - cannot be public or protected
    private PduType(char pduTypeChar) {
        this.pduTypeChar = pduTypeChar;
    }

}
