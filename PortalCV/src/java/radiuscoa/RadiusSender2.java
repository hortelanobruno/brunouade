/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package radiuscoa;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import net.jradius.client.RadiusClient;
import net.jradius.dictionary.Attr_UserName;
import net.jradius.dictionary.vsa_cisco.Attr_CiscoAVPair;
import net.jradius.dictionary.vsa_cisco.Attr_CiscoAccountInfo;
import net.jradius.dictionary.vsa_cisco.Attr_CiscoSubscriberPassword;
import net.jradius.packet.CoARequest;
import net.jradius.packet.RadiusPacket;
import net.jradius.packet.attribute.AttributeFactory;
import net.jradius.packet.attribute.AttributeList;
import net.jradius.packet.attribute.RadiusAttribute;
import net.jradius.util.MD5;
import org.apache.log4j.Logger;

/**
 *
 * @author bruno
 */
public class RadiusSender2 {

    private String radiusServer;
    private String sharedSecret = "callis";
    private Integer port = 1700;
    private String initialVector = "IIIIIIIIIIIIIIII";
    private Logger logger = Logger.getLogger("portal");
    private Logger loggerError = Logger.getLogger("portalError");
    private RadiusClient radiusClient = null;

    public static void main(String args[]) {
        try {
            RadiusSender2 sender = new RadiusSender2("192.168.3.1", 1700, "callis");
            System.out.println("1");
            System.out.println(sender.logon("mike", "mike", "192.168.4.100"));
            System.out.println("sleep");
            Thread.sleep(1000 * 30);
            System.out.println("2");
            System.out.println(sender.logon("mike", "mike", "192.168.4.100"));
            System.out.println("3");
    //        sender.logoff("test2", "172.16.100.51");
            System.out.println(sender.sessionQuery("test122", "192.168.4.100").getResultType());
        } catch (Exception ex) {
        }
    }

    public RadiusSender2(String radiusServer, Integer radiusport, String sharedSecret) {
        try {
            this.port = radiusport;
            this.sharedSecret = sharedSecret;
            this.radiusServer = radiusServer;

            radiusClient = new RadiusClient(InetAddress.getByName(radiusServer), // String - Shared Secret for remote RADIUS Server
                    sharedSecret, port, port, 10); // String - Shared Secret for remote RADIUS Server
        } catch (Exception ex) {
            loggerError.error("RadiusSender2 constructor", ex);
        }
    }

    public void close() {
        if (radiusClient != null) {
            radiusClient.close();
        }
    }

    public RadiusResult logon(String username, String password, String ciscoAccountInfo) {
        AttributeFactory.loadAttributeDictionary("net.jradius.dictionary.AttributeDictionaryImpl");
        RadiusResult result = new RadiusResult();
        try {
            AttributeList lista = new AttributeList();
            lista.add(new Attr_UserName(username));
            if (password != null) {
                Attr_CiscoSubscriberPassword b = new Attr_CiscoSubscriberPassword();
                b.setValue(makeCiscoSubscriberPasswordValue2(radiusClient, password));
                lista.add(b);
            }
            Attr_CiscoAVPair a = new Attr_CiscoAVPair();
            a.setValue("subscriber:command=account-logon");
            lista.add(a);
            lista.add(new Attr_CiscoAccountInfo("S" + ciscoAccountInfo));
            CoARequest request = new CoARequest(radiusClient, lista);
            logger.debug("Sending logon:\n" + request.toString());
            RadiusPacket reply = radiusClient.changeOfAuth((CoARequest) request, 1);
            result.setReply(reply);
            if (reply == null || reply.getCode() == 45) {
                result.setResultType(RadiusCoaResult.INVALID_USERNAME_AND_PASSWORD);
                if (reply != null) {
                    logger.debug("Received logon ack/nack:\n" + reply.toString());
                }
                return result;
            }
            RadiusAttribute att = reply.getAttributes().get("Reply-Message");
            if (att != null) {
            }
            logger.debug("Received logon ack/nack:\n" + reply.toString());
            result.setResultType(RadiusCoaResult.LOGON);
            return result;
        } catch (Exception ex) {
            loggerError.error("logon", ex);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            result.setException(sw.toString());
            result.setResultType(RadiusCoaResult.UNKNOWN);
            return result;
        }
    }

    public RadiusResult logoff(String username, String ciscoAccountInfo) {
        AttributeFactory.loadAttributeDictionary("net.jradius.dictionary.AttributeDictionaryImpl");
        RadiusResult result = new RadiusResult();
        try {
            AttributeList lista = new AttributeList();
            lista.add(new Attr_UserName(username));
            Attr_CiscoAVPair a = new Attr_CiscoAVPair();
            a.setValue("subscriber:command=account-logoff");
            lista.add(a);
            lista.add(new Attr_CiscoAccountInfo("S" + ciscoAccountInfo));
            CoARequest request = new CoARequest(radiusClient, lista);
            logger.debug("Sending logoff:\n" + request.toString());
            RadiusPacket reply = radiusClient.changeOfAuth((CoARequest) request, 1);
            result.setReply(reply);
            if (reply == null || reply.getCode() == 45) {
                result.setResultType(RadiusCoaResult.INVALID_USERNAME_AND_PASSWORD);
                if (reply != null) {
                    logger.debug("Received logoff ack/nack:\n" + reply.toString());
                }
                return result;
            }
            logger.debug("Received logoff ack/nack:\n" + reply.toString());
            result.setResultType(RadiusCoaResult.LOGOFF);
            return result;
        } catch (Exception ex) {
            loggerError.error("logoff", ex);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            result.setException(sw.toString());
            result.setResultType(RadiusCoaResult.UNKNOWN);
            return result;
        }
    }

    public RadiusResult sessionQuery(String username, String ciscoAccountInfo) {
        AttributeFactory.loadAttributeDictionary("net.jradius.dictionary.AttributeDictionaryImpl");
        RadiusResult result = new RadiusResult();
        try {
            AttributeList lista = new AttributeList();
            lista.add(new Attr_UserName(username));
            Attr_CiscoAVPair a = new Attr_CiscoAVPair();
            a.setValue("subscriber:command=account-profile-status-query");
            lista.add(a);
            lista.add(new Attr_CiscoAccountInfo("S" + ciscoAccountInfo));
            CoARequest request = new CoARequest(radiusClient, lista);
            logger.debug("Sending sessionQuery:\n" + request.toString());
            System.out.println("Sending sessionQuery:\n" + request.toString());
            RadiusPacket reply = radiusClient.changeOfAuth((CoARequest) request, 1);
            result.setReply(reply);
            //REGISTRATION -> login
            boolean registration = false;
            for (RadiusAttribute att : reply.getAttributes().getAttributeList()) {
                if (att.getAttributeName().equals("Cisco-Account-Info")) {
                    if (att.getValue().toString().contains("REGISTRATION")) {
                        registration = true;
                        break;
                    }
                }
            }
            boolean valid = false;
            if (!registration) {
                logger.debug("false");
                if (reply == null) {
                    result.setResultType(RadiusCoaResult.INVALID_SESSION);
                    if (reply != null) {
                        logger.debug("Received session query ack/nack:\n" + reply.toString());
                        System.out.println("Received session query ack/nack:\n" + reply.toString());
                    }
                    return result;
                } else {
                    for (RadiusAttribute att : reply.getAttributes().getAttributeList()) {
                        if (att.getAttributeName().equals("Cisco-Account-Info")) {
                            if (att.getValue().toString().contains("BASIC") || att.getValue().toString().contains("PREMIUM")) {
                                valid = true;
                                break;
                            }
                        }
                    }
                    if (!valid) {
                        result.setResultType(RadiusCoaResult.INVALID_SESSION);
                        if (reply != null) {
                            logger.debug("Received session query ack/nack:\n" + reply.toString());
                            System.out.println("Received session query ack/nack:\n" + reply.toString());
                        }
                        return result;
                    }
                }
            } else {
                logger.debug("registration true");
                //El usuario esta activando la cuenta, lo tengo que mandar al portal
                result.setResultType(RadiusCoaResult.INVALID_SESSION);
                if (reply != null) {
                    logger.debug("Received session query ack/nack:\n" + reply.toString());
                    System.out.println("Received session query ack/nack:\n" + reply.toString());
                }
                return result;
            }
            logger.debug("Received session query ack/nack:\n" + reply.toString());
            System.out.println("Received session query ack/nack:\n" + reply.toString());
            result.setResultType(RadiusCoaResult.VALID_SESSION);
            return result;
        } catch (Exception ex) {
            loggerError.error("session query", ex);
            ex.printStackTrace();
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            result.setException(sw.toString());
            result.setResultType(RadiusCoaResult.UNKNOWN);
            return result;
        }
    }

    public RadiusResult sessionQuery2(String username, String ciscoAccountInfo) {
        AttributeFactory.loadAttributeDictionary("net.jradius.dictionary.AttributeDictionaryImpl");
        RadiusResult result = new RadiusResult();
        try {
            AttributeList lista = new AttributeList();
            lista.add(new Attr_UserName(username));
            Attr_CiscoAVPair a = new Attr_CiscoAVPair();
            a.setValue("subscriber:command=account-status-query");
            lista.add(a);
            lista.add(new Attr_CiscoAccountInfo("S" + ciscoAccountInfo));
            CoARequest request = new CoARequest(radiusClient, lista);
            logger.debug("Sending sessionQuery:\n" + request.toString());
            System.out.println("Sending sessionQuery:\n" + request.toString());
            RadiusPacket reply = radiusClient.changeOfAuth((CoARequest) request, 1);
            result.setReply(reply);
            //REGISTRATION -> login
            boolean valid = false;
            if (reply == null) {
                result.setResultType(RadiusCoaResult.INVALID_SESSION);
                if (reply != null) {
                    logger.debug("Received session query ack/nack:\n" + reply.toString());
                    System.out.println("Received session query ack/nack:\n" + reply.toString());
                }
                return result;
            } else {
                //chequeo que tenga el attributo username y desp el servicio de redireccion
                for (RadiusAttribute att : reply.getAttributes().getAttributeList()) {
                    if (att.getAttributeName().equals("User-Name")) {
                        valid = true;
                        break;
                    }
                }
                if (!valid) {
                    result.setResultType(RadiusCoaResult.INVALID_SESSION);
                    if (reply != null) {
                        logger.debug("Received session query ack/nack:\n" + reply.toString());
                        System.out.println("Received session query ack/nack:\n" + reply.toString());
                    }
                    return result;
                } else {
                    valid = false;
                    for (RadiusAttribute att : reply.getAttributes().getAttributeList()) {
                        if (att.getAttributeName().equals("Cisco-Account-Info")) {
                            if (att.getValue().toString().contains("RDT_SERVICE")) {
                                valid = true;
                                break;
                            }
                        }
                    }
                    if (!valid) {
                        result.setResultType(RadiusCoaResult.INVALID_SESSION);
                        if (reply != null) {
                            logger.debug("Received session query ack/nack:\n" + reply.toString());
                            System.out.println("Received session query ack/nack:\n" + reply.toString());
                        }
                        return result;
                    }
                }
            }
            logger.debug("Received session query ack/nack:\n" + reply.toString());
            System.out.println("Received session query ack/nack:\n" + reply.toString());
            result.setResultType(RadiusCoaResult.VALID_SESSION);
            return result;
        } catch (Exception ex) {
            loggerError.error("session query", ex);
            ex.printStackTrace();
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            result.setException(sw.toString());
            result.setResultType(RadiusCoaResult.UNKNOWN);
            return result;
        }
    }

    private byte[] makeCiscoSubscriberPasswordValue2(RadiusClient radiusClient, String password) {

        byte len = (byte) (password.length() & 0xff);

        byte[] lenPassword = new byte[16];
        lenPassword[0] = (byte) (len);
        System.arraycopy(password.getBytes(), 0, lenPassword, 1, password.length());

        byte[] secret = radiusClient.getSharedSecret().getBytes();
        byte[] iVector = initialVector.getBytes();

        byte[] result = new byte[secret.length + iVector.length];

        System.arraycopy(secret, 0, result, 0, secret.length);
        System.arraycopy(iVector, 0, result, secret.length, iVector.length);

        byte[] b1 = MD5.md5(result);

        byte[] xor = new byte[16];

        for (int i = 0; i < xor.length; i++) {
            xor[i] = (byte) (lenPassword[i] ^ b1[i]);
        }

        byte[] result2 = new byte[32];
        System.arraycopy(iVector, 0, result2, 0, iVector.length);
        System.arraycopy(xor, 0, result2, iVector.length, xor.length);

        return result2;
    }

    public static String getHexString(byte[] b) throws Exception {
        String result = "";
        for (int i = 0; i < b.length; i++) {
            result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }
}
