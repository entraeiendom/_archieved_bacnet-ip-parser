package no.entra.bacnet.ip.apdu;

import no.entra.bacnet.ip.apdu.service.ApduServiceDescription;
import no.entra.bacnet.ip.apdu.service.UnconfirmedCovNotificationServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import static no.entra.bacnet.ip.apdu.PduType.UnconfirmedRequest;

public class UnconfirmedRequestApdu extends Apdu implements Serializable {
    private static final Logger log = LoggerFactory.getLogger( UnconfirmedRequestApdu.class );

    private final static PduType pduType = UnconfirmedRequest;
    private ApduServiceDescription serviceDescription = null;
    public UnconfirmedRequestApdu(char senderChar, char[] serviceChoiceHex, String apduHexString) {
        super(UnconfirmedRequest.getPduTypeChar(), senderChar, serviceChoiceHex, apduHexString);
        super.setServiceChoice(ServiceChoice.fromServiceChoiceHex(serviceChoiceHex));
        buildContentFromServiceChoice();
    }

    public PduType getPduType() {
        return pduType;
    }

    public void buildContentFromServiceChoice() {
        ServiceChoice serviceChoice = getServiceChoice();
        switch (serviceChoice) {
            case UnconfirmedCovNotification:
                String apduHexString = getApduHexString();
                serviceDescription = new UnconfirmedCovNotificationServiceDescription(apduHexString);
                break;
            default:
                log.debug("ServiceDescription could not be built from {}", serviceChoice);

        }
    }

    public ApduServiceDescription getServiceDescription() {
        return serviceDescription;
    }
}
