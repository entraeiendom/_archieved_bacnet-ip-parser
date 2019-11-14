package no.entra.bacnet.ip.apdu;

import java.io.Serializable;

public class Apdu implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Enum<Sender> sender;
    private final Enum<PduType> pduType;
    private ServiceChoice serviceChoice = null;

    private final char pduTypeChar;
    private final char senderChar;
    private char[] invokeId;
    private final char[] serviceChoiceHex;
    private String apduHexString;

    private Apdu(char pduTypeChar, char senderChar, char[] serviceChoiceHex) {
        this.pduTypeChar = pduTypeChar;
        this.pduType = PduType.fromPduTypeChar(pduTypeChar);
        this.senderChar = senderChar;
        sender = Sender.fromSenderChar(senderChar);
        this.serviceChoiceHex = serviceChoiceHex;
    }

    /**
     *
     * @param pduTypeChar
     * @param senderChar
     * @param serviceChoiceHex
     * @param apduHexString
     */
    public Apdu(char pduTypeChar, char senderChar, char[] serviceChoiceHex, String apduHexString) {
        this(pduTypeChar, senderChar, serviceChoiceHex);
        this.apduHexString = apduHexString;
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

    public char[] getServiceChoiceHex() {
        return serviceChoiceHex;
    }

    public void setServiceChoice(ServiceChoice serviceChoice) {
        this.serviceChoice = serviceChoice;
    }

    public ServiceChoice getServiceChoice() {
        return serviceChoice;
    }

    public void setInvokeId(char[] invokeId) {
        this.invokeId = invokeId;
    }

    public void setApduHexString(String apduHexString) {
        this.apduHexString = apduHexString;
    }

    public String getApduHexString() {
        return apduHexString;
    }
}
