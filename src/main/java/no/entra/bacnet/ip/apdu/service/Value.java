package no.entra.bacnet.ip.apdu.service;

import java.io.Serializable;

public class Value implements Serializable {
    private final EventType  type;
    private final EventState observation;

    public Value(String typeHex, char valueHex) {
        this.type = EventType.formEventTypeChar(typeHex);
        this.observation = EventState.formEventStateChar(valueHex);
    }

    public EventType getType() {
        return type;
    }

    public EventState getObservation() {
        return observation;
    }
}
