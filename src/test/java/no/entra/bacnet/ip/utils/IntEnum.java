package no.entra.bacnet.ip.utils;

public enum IntEnum {
    One(1),
    Two(2);

    private int intType;

    public static IntEnum fromIntType(int intType) {
        for (IntEnum type : values()) {
            if (type.getIntType() == intType) {
                return type;
            }
        }
        return null;
    }

    public int getIntType() {
        return intType;
    }

    private IntEnum(int intType) {
        this.intType = intType;
    }
}
