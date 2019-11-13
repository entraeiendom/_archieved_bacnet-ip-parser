package no.entra.bacnet.ip.ardu;

import java.io.Serializable;

public class Apdu implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String SENDER_CLIENT = "0";
    public static final String SENDER_SERVER = "1";
    private final Enum<Sender> sender;
    private final Enum<PduType> pduType;

    public enum Sender {CLIENT, SERVER}

    private final char pduTypeChar;
    private final char senderChar;
    private final char[] invokeId;
    private final char[] serviceChoice;

    public Apdu(char pduTypeChar, char senderChar, char[] invokeId, char[] serviceChoice) {
        this.pduTypeChar = pduTypeChar;
        this.pduType = PduType.valueOf(String.valueOf(pduTypeChar));
        this.senderChar = senderChar;
        if (Character.toString(senderChar).equals(SENDER_CLIENT)) {
            sender = Sender.CLIENT;
        } else if (Character.toString(senderChar).equals(SENDER_SERVER)) {
            sender = Sender.SERVER;
        } else {
            sender = null;
        }
        this.invokeId = invokeId;
        this.serviceChoice = serviceChoice;
    }

}
