package utils;

import java.util.HashMap;
import java.util.Map;


public class HeaderBuilder {
    public static final String X_AUTH_TOKEN = "X-KM-AUTH-TOKEN";
    public static final String REQUEST_TYPE = "requestType";
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static final String DELETE = "DELETE";
    public static final String GET = "GET";

    String par = null;
    String serviceId = null;
    String userId = null;
    String mpaId = null;
    String cardId = null;
    String aspId = null;
    String requestType = null;
    String xAuthToken = null;


    public HeaderBuilder() {

    }





    public HeaderBuilder addRequestType(String requestType) {
        this.requestType = requestType;
        return this;
    }

    public HeaderBuilder addXAuthToken(String token) {
        this.xAuthToken = token;
        return this;
    }

    public HeaderBuilder addinvalidXAuthToken(String token) {
        this.xAuthToken = token+"1";
        return this;
    }

    public Map<String, String> build() {
        Map<String, String> result = new HashMap<String, String>();

        if (xAuthToken != null) {
            result.put("X-Auth-Key", this.xAuthToken);
        }

        return result;
    }
}
