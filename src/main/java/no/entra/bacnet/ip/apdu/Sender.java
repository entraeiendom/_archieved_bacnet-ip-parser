package no.entra.bacnet.ip.apdu;

public enum Sender {
    CLIENT('0'), SERVER('1');

    private char senderChar;

    public static Sender fromSenderChar(char senderChar) {
        for (Sender sender : values()) {
            if (sender.getSenderChar() == senderChar) {
                return sender;
            }
        }
        return null;
    }

    public char getSenderChar() {
        return senderChar;
    }

    // enum constructor - cannot be public or protected
    private Sender(char SenderChar) {
        this.senderChar = SenderChar;
    }}
