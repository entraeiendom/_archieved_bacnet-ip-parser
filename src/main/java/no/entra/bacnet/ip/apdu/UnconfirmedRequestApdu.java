package no.entra.bacnet.ip.apdu;

import java.io.Serializable;

import static no.entra.bacnet.ip.apdu.PduType.UnconfirmedRequest;

public class UnconfirmedRequestApdu extends Apdu implements Serializable {

    private final static PduType pduType = UnconfirmedRequest;
    public UnconfirmedRequestApdu(char senderChar, char[] serviceChoiceHex) {
        super(UnconfirmedRequest.getPduTypeChar(), senderChar, serviceChoiceHex);
        super.setServiceChoice(ServiceChoice.fromServiceChoiceHex(serviceChoiceHex));
    }

    public PduType getPduType() {
        return pduType;
    }


}
