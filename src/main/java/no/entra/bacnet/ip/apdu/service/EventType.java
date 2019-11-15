package no.entra.bacnet.ip.apdu.service;

public enum EventType {
    SYSTEM_STATUS("70");

    private String eventTypeHex;

    public static EventType formEventTypeChar(String eventTypeHex) {
        for (EventType type : values()) {
            if (type.getEventTypeHex().equals(eventTypeHex)) {
                return type;
            }
        }
        return null;
    }

    public String getEventTypeHex() {
        return eventTypeHex;
    }

    // enum constructor - cannot be public or protected
    private EventType(String eventTypeHex) {
        this.eventTypeHex = eventTypeHex;
    }
}
