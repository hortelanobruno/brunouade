/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import session.LoginSessionBean;

/**
 *
 * @author bruno
 */
public class GetInfoFibertel implements Runnable {

    private Logger logger = Logger.getLogger("portal");
    private Logger loggerError = Logger.getLogger("portalError");
    private String code;
    private String userid;
    private LoginSessionBean loginSessionBean;

    public GetInfoFibertel(LoginSessionBean loginSessionBean, String code, String userid) {
        this.loginSessionBean = loginSessionBean;
        this.code = code;
        this.userid = userid;
    }

    @Override
    public void run() {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        String a = null;
        try {
            //Get 1

//            HttpGet getRequest = new HttpGet("http://www.fibertel.com.ar/access-token.ashx?client_id=" + loginSessionBean.getSettingsConfiguration().getProperty("fibertelappclientid") + "&client_secret=" + loginSessionBean.getSettingsConfiguration().getProperty("fibertelappsecretid") + "&Code=" + code);
            //http://www.cablevisionfibertel.com.ar/handler/oauth_accesstoken.ashx?ClientId=556E7106-9133-472E-965E-E86333A48F36&ClientSecret=BBBC56B8-5BB6-4D14-B610-07DDCB658D25&Code=
            HttpGet getRequest = new HttpGet("http://www.cablevisionfibertel.com.ar/handler/oauth_accesstoken.ashx?ClientId=" + loginSessionBean.getSettingsConfiguration().getProperty("fibertelappclientid") + "&ClientSecret=" + loginSessionBean.getSettingsConfiguration().getProperty("fibertelappsecretid") + "&Code=" + code);
            HttpResponse response2 = httpClient.execute(getRequest);
            if (response2.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response2.getStatusLine().getStatusCode());
            }
            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response2.getEntity().getContent())));
            String output;
            StringBuilder sb = new StringBuilder();
            logger.info("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
            logger.info(sb.toString());
//            getRequest = new HttpGet("http://crm.fibertel.com.ar/webservice_2/personapi.aspx?access_token=" + sb.toString() + "&user_id=" + userid);            
            getRequest = new HttpGet("http://cablevisionfibertel.preprod.ferengi.com.ar/handler/openproducts.ashx?token=" + sb.toString() + "&productTypeId=1");
            getRequest.addHeader("accept", "application/json");
            response2 = httpClient.execute(getRequest);
            if (response2.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response2.getStatusLine().getStatusCode());
            }
            br = new BufferedReader(
                    new InputStreamReader((response2.getEntity().getContent())));
            sb = new StringBuilder();
            logger.info("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
            logger.info(sb.toString());
            a = sb.toString().split("\"Products\":")[1].split("}  ]}")[0];
            logger.info("Result: " + a);
        } catch (Exception ex) {
            loggerError.error("error gets a CV", ex);
        }
        httpClient.getConnectionManager().shutdown();
        loginSessionBean.saveClienteInfo(userid, a);
    }

    public static void main(String args[]) {
        GetInfoFibertel a = new GetInfoFibertel(null, null, null);
        a.prueba();
    }

    public void prueba() {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        String a = null;
        try {
            //Get
            HttpPost getRequest = new HttpPost("http://www.cablevisionfibertel.com.ar/handler/login.ashx");

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
            nameValuePairs.add(new BasicNameValuePair("ClientId", "556E7106-9133-472E-965E-E86333A48F36"));
            nameValuePairs.add(new BasicNameValuePair("Redirect", "/"));
            nameValuePairs.add(new BasicNameValuePair("Username", "ssensabastiano@gmail.com"));
            nameValuePairs.add(new BasicNameValuePair("Password", "aIojan1234"));
            getRequest.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            //{"Status":"OK", "Errors":"", "Redirect":"http://fbzauth.fibertel.com.ar/loginCV?UserId=ssensabastiano@gmail.com&Code=1E21B2BE-0472-4DCC-BC42-5F72872F9E13", "UserData":"Id=724553&Name=SEBASTIAN SENSABASTIANO&LastName=&Email=Ssensabastiano@gmail.com&Cuic=00427286366"}
            //{"Status":"OK", "Errors":"", "Redirect":"http://fbzauth.fibertel.com.ar/loginCV?UserId=ssensabastiano@gmail.com&Code=B83184F3-0A16-448E-A52B-BAA6D8BEE5EB", "UserData":"Id=724553&Name=SEBASTIAN SENSABASTIANO&LastName=&Email=ssensabastiano@gmail.com&Cuic=00427286366"}
            //{"Status":"ERROR", "Errors":"Invalid_User", "Redirect":"/", "UserData":""}

            HttpResponse response2 = httpClient.execute(getRequest);
            if (response2.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response2.getStatusLine().getStatusCode());
            }
            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response2.getEntity().getContent())));
            String output;
            StringBuilder sb = new StringBuilder();
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
            if (sb.toString().contains("\"Status\":\"OK\"")) {
                System.out.println("OK");
            } else {
                System.out.println("FALSE");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        httpClient.getConnectionManager().shutdown();
    }
}
