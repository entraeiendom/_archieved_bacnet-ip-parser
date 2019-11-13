package no.entra.bacnet.ip.apdu;

import java.io.Serializable;

public class Apdu implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Enum<Sender> sender;
    private final Enum<PduType> pduType;

    private final char pduTypeChar;
    private final char senderChar;
    private final char[] invokeId;
    private final char[] serviceChoice;

    public Apdu(char pduTypeChar, char senderChar, char[] invokeId, char[] serviceChoice) {
        this.pduTypeChar = pduTypeChar;
        this.pduType = PduType.fromPduTypeChar(pduTypeChar);
        this.senderChar = senderChar;
        sender = Sender.fromSenderChar(senderChar);
        this.invokeId = invokeId;
        this.serviceChoice = serviceChoice;
    }

    public Enum<PduType> getPduType() {
        return pduType;
    }

    public char getPduTypeChar() {
        return pduTypeChar;
    }

    public Enum<Sender> getSender() {
        return sender;
    }

    public char getSenderChar() {
        return senderChar;
    }

    public char[] getInvokeId() {
        return invokeId;
    }

    public char[] getServiceChoice() {
        return serviceChoice;
    }
}
