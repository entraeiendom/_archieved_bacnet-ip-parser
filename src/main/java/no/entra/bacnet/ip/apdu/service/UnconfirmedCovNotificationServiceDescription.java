package no.entra.bacnet.ip.apdu.service;

import no.entra.bacnet.ip.utils.HexParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UnconfirmedCovNotificationServiceDescription implements ApduServiceDescription {
    private static final Logger log = LoggerFactory.getLogger( UnconfirmedCovNotificationServiceDescription.class );
    private static final long serialVersionUID = 1L;
    public static final String PD_OPENING_TAG_2_HEX = "2e";
    public static final String PD_CLOSING_TAG_2_HEX = "2f";
    public static final String PD_OPENING_TAG_4_HEX = "4e";
    public static final String PD_CLOSING_TAG_4_HEX = "4f";
    public static final String SD_CONTEXT_TAG_0_HEX = "09";
    public static final String SD_CONTEXT_TAG_1_HEX = "1c";
    public static final String SD_CONTEXT_TAG_2_HEX = "2c";
    public static final String SD_CONTEXT_TAG_3_HEX = "39";
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
        buildObservationTimeRemaining();
        buildListOfValues();
    }

    void buildProcessIdentifiers() {
        char[] hasProcessIdentifier = apduHexString.substring(4,6).toCharArray();
        if (Arrays.equals(hasProcessIdentifier, SD_CONTEXT_TAG_0_HEX.toCharArray())) {
            subscriberProcessIdentifier = apduHexString.substring(6,8).toCharArray();
        }
    }
    void buildObjectIdentifiers() {
        char[] initiatingObjectInstance = apduHexString.substring(8,10).toCharArray();
        if (Arrays.equals(initiatingObjectInstance, SD_CONTEXT_TAG_1_HEX.toCharArray())) {
            char deviceTypeHex = apduHexString.charAt(11);
            ObjectType objectType = ObjectType.fromObjectTypeChar(deviceTypeHex);
            char[] instanceNumberHex = apduHexString.substring(14,18).toCharArray();

            int instanceNumber = HexParser.toInteger(instanceNumberHex);
            ObjectIdentifier objectIdentifier = new ObjectIdentifier(objectType, instanceNumber);
            objectIdentifier.setInitiating(true);
            objectIds.add(objectIdentifier);
        }
        char[] monitoredObjectInstance = apduHexString.substring(18,20).toCharArray();
        if (Arrays.equals(monitoredObjectInstance, SD_CONTEXT_TAG_2_HEX.toCharArray())) {
            char deviceTypeHex = apduHexString.charAt(21);
            ObjectType objcetType = ObjectType.fromObjectTypeChar(deviceTypeHex);
            char[] instanceNumberHex = apduHexString.substring(24,28).toCharArray();

            int instanceNumber = HexParser.toInteger(instanceNumberHex);
            ObjectIdentifier objectIdentifier = new ObjectIdentifier(objcetType, instanceNumber);
            objectIdentifier.setMonitored(true);
            objectIds.add(objectIdentifier);
        }
    }
    void buildObservationTimeRemaining() {
        char[] hasTimeRemainingHex = apduHexString.substring(28,30).toCharArray();
        if (Arrays.equals(hasTimeRemainingHex, SD_CONTEXT_TAG_3_HEX.toCharArray())) {
            char[] timeRemainingHex = apduHexString.substring(30,32).toCharArray();
            if (Arrays.equals(timeRemainingHex, "00".toCharArray())) {
                timeRemaining = Duration.ZERO;
            } else {
                //#1 TODO try to figure out how to parse the TimeRemaining
                log.info("Need to implement parsing to Duration from {}", timeRemainingHex);
                timeRemaining = null;
            }
        }
    }

    void buildListOfValues() {
        int openingTagPosition = apduHexString.indexOf(PD_OPENING_TAG_4_HEX);
        int closingTagPosition = apduHexString.indexOf(PD_CLOSING_TAG_4_HEX);
        if (openingTagPosition >0 && closingTagPosition > 0) {
            String listOfValuesHexString = apduHexString.substring(openingTagPosition, closingTagPosition + PD_CLOSING_TAG_4_HEX.length());
            log.debug("ListOfValues: {}", listOfValuesHexString);
            values = ValuesParser.parseListOfValuesHexString(listOfValuesHexString);
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
