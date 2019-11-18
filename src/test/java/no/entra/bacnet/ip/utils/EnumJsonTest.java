package no.entra.bacnet.ip.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnumJsonTest {

    @Test
    void jsonAsEnum() throws JSONException {
        Gson gson = new GsonBuilder().create();
        IntEnum two = IntEnum.Two;
        assertEquals(IntEnum.Two, two);
        HasInt hasInt = new HasInt(two);
        String json = gson.toJson(hasInt);
        String expected = "{\"hasInt\":\"Two\"}";
        JSONAssert.assertEquals(expected, json, true);
    }
}
