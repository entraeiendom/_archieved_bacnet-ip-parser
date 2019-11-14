package no.entra.bacnet.ip.apdu.service;

import java.io.Serializable;

public class ObjectIdentifier implements Serializable {

    private final ObjectType objectType;
    private int InstanceNumber;
    private boolean initiating = false;
    private boolean monitored = false;

    public ObjectIdentifier(ObjectType objectType, int instanceNumber) {
        this.objectType = objectType;
        InstanceNumber = instanceNumber;
    }

    public ObjectType getObjectType() {
        return objectType;
    }

    public int getInstanceNumber() {
        return InstanceNumber;
    }

    public void setInstanceNumber(int instanceNumber) {
        InstanceNumber = instanceNumber;
    }

    public boolean isInitiating() {
        return initiating;
    }

    public void setInitiating(boolean initiating) {
        this.initiating = initiating;
    }

    public boolean isMonitored() {
        return monitored;
    }

    public void setMonitored(boolean monitored) {
        this.monitored = monitored;
    }
}
