package no.entra.bacnet.ip.apdu.service;

import java.io.Serializable;

public class ObjectIdentifier implements Serializable {

    private final Enum<ObjectType> objectType;
    private int InstanceNumber;

    public ObjectIdentifier(Enum<ObjectType> objectType, int instanceNumber) {
        this.objectType = objectType;
        InstanceNumber = instanceNumber;
    }
}
