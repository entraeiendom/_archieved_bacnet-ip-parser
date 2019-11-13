package no.entra.bacnet.ip.apdu;

import java.util.Arrays;

public enum ServiceChoice {
    UnconfirmedCovNotification("02".toCharArray());

    private char[] serviceChoice;

    public static ServiceChoice fromServiceChoiceHex(char[] serviceChoiceChar) {
        for (ServiceChoice type : values()) {
            if (Arrays.equals(type.getServiceChoice(),serviceChoiceChar)) {
                return type;
            }
        }
        return null;
    }

    public char[] getServiceChoice() {
        return serviceChoice;
    }

    // enum constructor - cannot be public or protected
    private ServiceChoice(char[] serviceChoice) {
        this.serviceChoice = serviceChoice;
    }

}
