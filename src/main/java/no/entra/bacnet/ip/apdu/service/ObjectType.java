package no.entra.bacnet.ip.apdu.service;

public enum ObjectType {
    DEVICE('2');

    private char objectTypeChar;

    public static ObjectType fromObjectTypeChar(char pduTypeChar) {
        for (ObjectType type : values()) {
            if (type.getObjectTypeChar() == pduTypeChar) {
                return type;
            }
        }
        return null;
    }

    public char getObjectTypeChar() {
        return objectTypeChar;
    }

    // enum constructor - cannot be public or protected
    private ObjectType(char objectTypeChar) {
        this.objectTypeChar = objectTypeChar;
    }
}
