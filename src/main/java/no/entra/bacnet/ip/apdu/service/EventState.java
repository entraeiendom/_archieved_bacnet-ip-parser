package no.entra.bacnet.ip.apdu.service;

public enum EventState {
    NORMAL('0'),
    OUT_OF_RANGE('5');

    private char eventStateChar;

    public static EventState formEventStateChar(char eventStateChar) {
        for (EventState type : values()) {
            if (type.getEventStateChar() == eventStateChar) {
                return type;
            }
        }
        return null;
    }

    public char getEventStateChar() {
        return eventStateChar;
    }

    // enum constructor - cannot be public or protected
    private EventState(char eventStateChar) {
        this.eventStateChar = eventStateChar;
    }
}
