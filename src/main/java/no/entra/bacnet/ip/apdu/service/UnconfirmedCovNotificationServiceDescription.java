package no.entra.bacnet.ip.apdu.service;

import no.entra.bacnet.ip.utils.HexParser;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UnconfirmedCovNotificationServiceDescription implements ApduServiceDescription {
    private static final long serialVersionUID = 1L;
    private final String apduHexString;

    private char[] subscriberProcessIdentifier = null;
    private final List<ObjectIdentifier> objectIds = new ArrayList<>();
    private Duration timeRemaining;
    private List<Value> values;

    public UnconfirmedCovNotificationServiceDescription(String apduHexString) {
        this.apduHexString = apduHexString;
        buildDescription();
    }

    @Override
    public void buildDescription() {
        buildProcessIdentifiers();
        buildObjectIdentifiers();
    }

    void buildProcessIdentifiers() {
        char[] hasProcessIdentifier = apduHexString.substring(4,6).toCharArray();
        if (Arrays.equals(hasProcessIdentifier, "09".toCharArray())) {
            subscriberProcessIdentifier = apduHexString.substring(6,8).toCharArray();
        }
    }
    void buildObjectIdentifiers() {
        char[] initiatingObjectInstance = apduHexString.substring(8,10).toCharArray();
        if (Arrays.equals(initiatingObjectInstance, "1c".toCharArray())) {
            char deviceTypeHex = apduHexString.charAt(11);
            ObjectType objectType = ObjectType.fromObjectTypeChar(deviceTypeHex);
            char[] instanceNumberHex = apduHexString.substring(14,18).toCharArray();

            int instanceNumber = HexParser.toInteger(instanceNumberHex);
            ObjectIdentifier objectIdentifier = new ObjectIdentifier(objectType, instanceNumber);
            objectIdentifier.setInitiating(true);
            objectIds.add(objectIdentifier);
        }
        char[] monitoredObjectInstance = apduHexString.substring(18,20).toCharArray();
        if (Arrays.equals(monitoredObjectInstance, "2c".toCharArray())) {
            char deviceTypeHex = apduHexString.charAt(21);
            ObjectType objcetType = ObjectType.fromObjectTypeChar(deviceTypeHex);
            char[] instanceNumberHex = apduHexString.substring(24,28).toCharArray();

            int instanceNumber = HexParser.toInteger(instanceNumberHex);
            ObjectIdentifier objectIdentifier = new ObjectIdentifier(objcetType, instanceNumber);
            objectIdentifier.setMonitored(true);
            objectIds.add(objectIdentifier);
        }
    }

    public String getApduHexString() {
        return apduHexString;
    }

    public char[] getSubscriberProcessIdentifier() {
        return subscriberProcessIdentifier;
    }

    public List<ObjectIdentifier> getObjectIds() {
        return objectIds;
    }

    public Duration getTimeRemaining() {
        return timeRemaining;
    }

    public List<Value> getValues() {
        return values;
    }

    public boolean hasProcessIdentifier() {
        return subscriberProcessIdentifier != null;
    }

    public int getProcessIdentifierNumber() {
        if (!hasProcessIdentifier()) {
            return -1;
        }
        int number = Integer.parseInt(new String(subscriberProcessIdentifier));
        return number;
    }
}
