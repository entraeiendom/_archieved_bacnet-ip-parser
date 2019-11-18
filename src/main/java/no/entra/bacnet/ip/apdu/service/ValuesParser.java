package no.entra.bacnet.ip.apdu.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static no.entra.bacnet.ip.apdu.service.UnconfirmedCovNotificationServiceDescription.*;

public class ValuesParser {
    private static final Logger log = LoggerFactory.getLogger( ValuesParser.class );
    public static final String APPLICTION_TAG_9_HEX = "91";

    public static List<Value> parseApduHexString(String apduHexString) {
        return null;
    }

    /**
     * The string should start with PD_OPENING_TAG_HEX(4e), and end with PD_CLOSING_TAG_HEX(4f)
     * @param listOfValuesHexString
     * @return
     * @throws IllegalArgumentException
     */
    public static List<Value> parseListOfValuesHexString(String listOfValuesHexString) throws IllegalArgumentException {
//        String json = listOfValuesHexString.replace("2e", "{");
//        json = json.replace("2f", "}");
//        System.out.println(json);
        List<Value> values = new ArrayList<>();
        if (listOfValuesHexString != null && listOfValuesHexString.startsWith(PD_OPENING_TAG_4_HEX) && listOfValuesHexString.endsWith(PD_CLOSING_TAG_4_HEX)) {
            log.debug("Are able to parse {}", listOfValuesHexString);
            System.out.println("To Parse: " + listOfValuesHexString);
            String listInHex = listOfValuesHexString.substring(PD_OPENING_TAG_4_HEX.length(), listOfValuesHexString.length() - PD_CLOSING_TAG_4_HEX.length());
           /*
 X'4E' PD Opening Tag 4 (List of Values)
    X'09' SD Context Tag 0 (Property Identifier, L=1)
    X'55' 85 (PRESENT_VALUE)
    X'2E' PD Opening Tag 2 (Value)
        X'44' Application Tag 4 (Real, L=4)
        X'42820000' 65.0
    X'2F' PD Closing Tag 2 (Value)
    X'09' SD Context Tag 0 (Property Identifier, L=1)
    X'6F' 111 (STATUS_FLAGS)
       X'2E' PD Opening Tag 2 (Value)
        X'82' Application Tag 8 (Bit String, L=2)
        X'0400' 0,0,0,0 (FALSE, FALSE, FALSE, FALSE)
    X'2F' PD Closing Tag 2 (Value)
X'4F'
            */
            //4e09702e91002f09cb2e2ea4770b0105b40d2300442f2f09c42e91002f4f
            int startTag = 0;
            /*listInHex.indexOf(SD_CONTEXT_TAG_0_HEX);
            int endTag = listInHex.indexOf(PD_CLOSING_TAG_2_HEX) + 2;
            String listItemHex = listInHex.substring(startTag, endTag);
            Value value = parseListItem(listItemHex);
            if (value != null) {
                values.add(value);
            }
            listInHex = listInHex.substring(endTag+2);

             */
            startTag = listInHex.indexOf(SD_CONTEXT_TAG_0_HEX);
            while (startTag > -1) {
                int endTag = listInHex.indexOf(PD_CLOSING_TAG_2_HEX) + 2;
                String listItemHex = listInHex.substring(startTag, endTag);
                Value value = parseListItem(listItemHex);
                if (value != null) {
                    values.add(value);
                }
                listInHex = listInHex.substring(endTag);
                startTag = listInHex.indexOf(SD_CONTEXT_TAG_0_HEX);
            }
           /*
               //Remove parsed
               listInHex = listInHex.substring()
               position = position + valueHexString.length();
               System.out.println("Cahr at " + position + "; " + listInHex.charAt(position));
               if (listInHex.indexOf(PD_OPENING_TAG_2_HEX, position) > 0) {
                   hexPair = listInHex.substring(position, position + 2);
                   if (hexPair.equals(SD_CONTEXT_TAG_0_HEX)) {
                       type = listInHex.substring(position +2, position +4);
                       valueClosing = listInHex.indexOf(PD_CLOSING_TAG_2_HEX, position);
                       valueHexString = listInHex.substring(position, valueClosing + PD_CLOSING_TAG_2_HEX.length());
                       value = buildValue(type, valueHexString);
                       if (value != null) {
                           values.add(value);
                       }
                   }
               }
            */
        } else {
            throw new IllegalArgumentException("listOfValuesHexString must start with " + PD_OPENING_TAG_4_HEX +
                    ", and end with " + PD_CLOSING_TAG_4_HEX + ". Actual content: " + listOfValuesHexString);
        }

        return values;
    }

    public static Value parseListItem(String listItemHex) {
        Value value = null;
        String hexPair = listItemHex.substring(0, 2);
        if (hexPair.equals(SD_CONTEXT_TAG_0_HEX)) {
            hexPair = listItemHex.substring(2, 4);
            String type = hexPair;
            int position = 4;
            int valueClosing = listItemHex.indexOf(PD_CLOSING_TAG_2_HEX, position);
            String valueHexString = listItemHex.substring(position, valueClosing + PD_CLOSING_TAG_2_HEX.length());
            value = buildValue(type, valueHexString);
        }
        return value;
    }

    public static Value buildValue(String typeHex, String valueHexString) {
        //2e91002f
        Value value = null;
        String valueInHex = valueHexString.substring(PD_OPENING_TAG_2_HEX.length(), valueHexString.length() - PD_CLOSING_TAG_2_HEX.length());
        System.out.println(valueHexString);
        String hexPair = valueInHex.substring(0,2);
        if (hexPair.equals(APPLICTION_TAG_9_HEX)) {
            char valueHex = valueInHex.charAt(3);
            value = new Value(typeHex, valueHex);
        }
        return value;
    }
}
