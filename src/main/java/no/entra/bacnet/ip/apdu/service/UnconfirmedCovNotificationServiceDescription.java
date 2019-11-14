package no.entra.bacnet.ip.apdu.service;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class UnconfirmedCovNotificationServiceDescription implements ApduServiceDescription {
    private static final long serialVersionUID = 1L;
    private final String apduHexString;

    private char[] subscriberProcessIdentifier = null;
    private List<ObjectIdentifier> objectIds;
    private Duration timeRemaining;
    private List<Value> values;

    public UnconfirmedCovNotificationServiceDescription(String apduHexString) {
        this.apduHexString = apduHexString;
        buildDescription();
    }

    @Override
    public void buildDescription() {
        char[] hasProcessIdentifier = apduHexString.substring(4,6).toCharArray();
        if (Arrays.equals(hasProcessIdentifier, "09".toCharArray())) {
            subscriberProcessIdentifier = apduHexString.substring(6,8).toCharArray();
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
