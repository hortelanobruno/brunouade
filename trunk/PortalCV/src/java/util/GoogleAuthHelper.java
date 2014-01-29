/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonParser;
import com.google.api.client.json.JsonToken;
import com.google.api.client.json.jackson.JacksonFactory;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Brunoli
 */
public class GoogleAuthHelper {

    /**
     * Please provide a value for the CLIENT_ID constant before proceeding, set
     * this up at https://code.google.com/apis/console/
     */
    private static final String DEFAULT_CLIENT_ID = "672316175965.apps.googleusercontent.com";
    /**
     * Please provide a value for the CLIENT_SECRET constant before proceeding,
     * set this up at https://code.google.com/apis/console/
     */
    private static final String DEFAULT_CLIENT_SECRET = "-m5uEP6tasiyLzhj6ysvLS0t";
    /**
     * Callback URI that google will redirect to after successful authentication
     */
    private static final String CALLBACK_URI = "http://fbzauth.fibertel.com.ar/oauth2callback";
    // start google authentication constants
    private static final Iterable<String> SCOPE = Arrays.asList("https://www.googleapis.com/auth/userinfo.profile;https://www.googleapis.com/auth/userinfo.email".split(";"));
    private static final String USER_INFO_URL = "https://www.googleapis.com/oauth2/v1/userinfo";
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    // end google authentication constants
    private String stateToken;
    private final GoogleAuthorizationCodeFlow flow;

    /**
     * Constructor initializes the Google Authorization Code Flow with CLIENT
     * ID, SECRET, and SCOPE
     */
    public GoogleAuthHelper(String clientId, String clientSecret) {
        
        if(clientId==null){
            clientId=DEFAULT_CLIENT_ID;
        }
        if(clientSecret==null){
            clientSecret=DEFAULT_CLIENT_SECRET;
        }
        
        flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT,
                JSON_FACTORY, clientId, clientSecret, SCOPE).build();
        generateStateToken();
    }

    /**
     * Builds a login URL based on client ID, secret, callback URI, and scope
     */
    public String buildLoginUrl() {

        final GoogleAuthorizationCodeRequestUrl url = flow.newAuthorizationUrl();

        return url.setRedirectUri(CALLBACK_URI).setState(stateToken).build();
    }

    /**
     * Generates a secure state token
     */
    private void generateStateToken() {

        SecureRandom sr1 = new SecureRandom();

        stateToken = "google;" + sr1.nextInt();

    }

    /**
     * Accessor for state token
     */
    public String getStateToken() {
        return stateToken;
    }

    /**
     * Expects an Authentication Code, and makes an authenticated request for
     * the user's profile information
     *
     * @return JSON formatted user profile information
     * @param authCode authentication code provided by google
     */
    public String getUserInfoJson(final String authCode) throws IOException {

        final GoogleTokenResponse response = flow.newTokenRequest(authCode).setRedirectUri(CALLBACK_URI).execute();
        final Credential credential = flow.createAndStoreCredential(response, null);
        final HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(credential);
        // Make an authenticated request
        final GenericUrl url = new GenericUrl(USER_INFO_URL);
        final HttpRequest request = requestFactory.buildGetRequest(url);
        request.getHeaders().setContentType("application/json");
        final String jsonIdentity = request.execute().parseAsString();

        return jsonIdentity;

    }

//    public String getEmailInfoJson(final String authCode) throws IOException {
//        final GoogleTokenResponse response = flow.newTokenRequest(authCode).setRedirectUri(CALLBACK_URI).execute();
//        final Credential credential = flow.createAndStoreCredential(response, null);
//        final HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(credential);
//        // Make an authenticated request
//        final GenericUrl url = new GenericUrl(USER_INFO_URL);
//        final HttpRequest request = requestFactory.buildGetRequest(url);
//        request.getHeaders().setContentType("application/json");
//        final String result = request.execute().parseAsString();
//        System.out.println(result);
//        JsonParser parser = JSON_FACTORY.createJsonParser(result);
//        parser.nextToken();
//        while(!parser.getText().equalsIgnoreCase("email")){
//            parser.nextToken();
//        }
//        parser.nextToken();
//        return parser.getText();
//    }
    
    public Map<String,String> getInfoJson(final String authCode) throws IOException {
        final GoogleTokenResponse response = flow.newTokenRequest(authCode).setRedirectUri(CALLBACK_URI).execute();
        final Credential credential = flow.createAndStoreCredential(response, null);
        final HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(credential);
        // Make an authenticated request
        final GenericUrl url = new GenericUrl(USER_INFO_URL);
        final HttpRequest request = requestFactory.buildGetRequest(url);
        request.getHeaders().setContentType("application/json");
        final String result = request.execute().parseAsString();
        System.out.println(result);
        Map<String,String> mapa = new HashMap<String, String>();
        JsonParser parser = JSON_FACTORY.createJsonParser(result);
        
        System.out.println(parser.nextToken());
        System.out.println(parser.getText());
        
        String key,value;
        
        JsonToken token = parser.nextToken();
        
        while(token.toString().equals("FIELD_NAME")){
            key = parser.getText();
            parser.nextToken();
            value = parser.getText();
            mapa.put(key, value);
            token = parser.nextToken();
        }
        
        return mapa;
    }
}
