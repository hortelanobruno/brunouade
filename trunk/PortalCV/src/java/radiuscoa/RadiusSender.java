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
import stats.ManagerStatsRadiusCoA;

/**
 *
 * @author bruno
 */
public class RadiusSender {

    private String sharedSecret = "callis";
    private Integer port = 1700;
    private String initialVector = "IIIIIIIIIIIIIIII";
    private Logger logger = Logger.getLogger("radiusCoAEvents");
    private Logger loggerError = Logger.getLogger("portalError");

    public RadiusSender() {
    }

    public RadiusSender(Integer radiusport, String secret) {
        this.port = radiusport;
        this.sharedSecret = secret;
    }

    public static void main(String args[]) {
        RadiusSender sender = new RadiusSender();
        sender.logon("192.168.3.1", "mike", "mike", "192.168.4.100");
//        sender.logoff("192.168.1.1", "test2", "172.16.100.51");
//        sender.sessionQuery("192.168.1.1", "test2", "192.168.4.100");
        System.out.println(sender.sessionQuery("192.168.3.1", "test122", "192.168.4.100").getResultType());
    }

    public RadiusResult logon(String radiusServer, String username, String password, String ciscoAccountInfo) {
        AttributeFactory.loadAttributeDictionary("net.jradius.dictionary.AttributeDictionaryImpl");
        RadiusResult result = new RadiusResult();
        RadiusClient radiusClient = null;
        try {
            radiusClient = new RadiusClient(InetAddress.getByName(radiusServer), // InetAddress - Address of remote RADIUS Server
                    sharedSecret, port, port, 10); // String - Shared Secret for remote RADIUS Server
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
            ManagerStatsRadiusCoA.getInstance().incrementLogon();
            logger.debug("reply: " + (reply != null ? reply.getCode() : "null"));
            result.setReply(reply);
            if (reply != null) {
                if (reply.getCode() == 44) {
                    //ACK
                    logger.debug("Received logon ack:\n" + reply.toString());
                    result.setResultType(RadiusCoaResult.LOGON);
                    return result;
                } else {
                    //ANACK
                    RadiusAttribute att = reply.getAttributes().get("Reply-Message");
                    if (att != null) {
                        if (att.getValue().toString().equals("Memory or internal error")) {
                            result.setResultType(RadiusCoaResult.MEMORY_OR_INTERNAL_ERROR);
                            logger.debug("Received logon nack1:\n" + reply.toString());
                            return result;
                        } else if (att.getValue().toString().equals("No valid Session")) {
                            result.setResultType(RadiusCoaResult.INVALID_USERNAME_AND_PASSWORD);
                            logger.debug("Received logon nack2:\n" + reply.toString());
                            return result;
                        } else {
                            logger.debug("Received logon nack3:\n" + reply.toString());
                            result.setResultType(RadiusCoaResult.UNKNOWN);
                            return result;
                        }
                    } else {
                        result.setResultType(RadiusCoaResult.UNKNOWN);
                        return result;
                    }
                }
            } else {
                result.setResultType(RadiusCoaResult.UNKNOWN);
                return result;
            }
        } catch (Exception ex) {
            loggerError.error("logon", ex);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            result.setException(sw.toString());
            result.setResultType(RadiusCoaResult.UNKNOWN);
            return result;
        } finally {
            if (radiusClient != null) {
                radiusClient.close();
            }
        }
    }

    public RadiusResult logoff(String radiusServer, String username, String ciscoAccountInfo) {
        AttributeFactory.loadAttributeDictionary("net.jradius.dictionary.AttributeDictionaryImpl");
        RadiusResult result = new RadiusResult();
        RadiusClient radiusClient = null;
        try {
            radiusClient = new RadiusClient(InetAddress.getByName(radiusServer), // InetAddress - Address of remote RADIUS Server
                    sharedSecret, port, port, 10); // String - Shared Secret for remote RADIUS Server
            AttributeList lista = new AttributeList();
            lista.add(new Attr_UserName(username));
            Attr_CiscoAVPair a = new Attr_CiscoAVPair();
            a.setValue("subscriber:command=account-logoff");
            lista.add(a);
            lista.add(new Attr_CiscoAccountInfo("S" + ciscoAccountInfo));
            CoARequest request = new CoARequest(radiusClient, lista);
            logger.debug("Sending logoff:\n" + request.toString());
            RadiusPacket reply = radiusClient.changeOfAuth((CoARequest) request, 1);
            ManagerStatsRadiusCoA.getInstance().incrementLogoff();
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
        } finally {
            if (radiusClient != null) {
                radiusClient.close();
            }
        }
    }

//    
    public RadiusResult sessionQueryRedirect(String radiusServer, String username, String ciscoAccountInfo) {
        AttributeFactory.loadAttributeDictionary("net.jradius.dictionary.AttributeDictionaryImpl");
        RadiusResult result = new RadiusResult();
        RadiusClient radiusClient = null;
        try {
            radiusClient = new RadiusClient(InetAddress.getByName(radiusServer), // InetAddress - Address of remote RADIUS Server
                    sharedSecret, port, port, 10); // String - Shared Secret for remote RADIUS Server
            AttributeList lista = new AttributeList();
            lista.add(new Attr_UserName(username));
            Attr_CiscoAVPair a = new Attr_CiscoAVPair();
            a.setValue("subscriber:command=account-profile-status-query");
            lista.add(a);
            lista.add(new Attr_CiscoAccountInfo("S" + ciscoAccountInfo));
            CoARequest request = new CoARequest(radiusClient, lista);
            logger.debug("Sending sessionQuery:\n" + request.toString());
            System.out.println("Sending sessionQuery:\n" + request.toString());

            RadiusPacket reply = null;
//            int reintentos = 0;
//            while (reintentos < 3) {
//                logger.debug("it " + reintentos + "\n");
            reply = radiusClient.changeOfAuth((CoARequest) request, 1);
            ManagerStatsRadiusCoA.getInstance().incrementSessionQuery();
            result.setReply(reply);
            if (reply != null) {
                boolean registration = false;
                boolean invalidSession = false;
                boolean limitedSession = false;
                for (RadiusAttribute att : reply.getAttributes().getAttributeList()) {
                    if (att.getAttributeName().equals("Cisco-Account-Info")) {
                        if (att.getValue().toString().contains("N1L4RDT_SERVICE")) {
                            registration = true;
                        }
                        if (att.getValue().toString().contains("N1REDIRECT_PORT80")) {
                            limitedSession = true;
                        }
                    }
                    //Reply-Message = No valid Session
                    if (att.getAttributeName().equals("Reply-Message")) {
                        if (att.getValue().toString().contains("No valid Session")) {
                            invalidSession = true;
                        }
                    }
                }
                //FZONE_INTERNET_FULL,FZONE_INTERNET_LIMITED,FZONE_REDIRECT
                if (!invalidSession) {
                    if (registration) {
                        //redirect
                        logger.debug("Received session query ack:\n" + reply.toString());
                        logger.debug(RadiusCoaResult.FZONE_REDIRECT);
                        System.out.println("Received session query ack:\n" + reply.toString());
                        result.setResultType(RadiusCoaResult.FZONE_REDIRECT);
                        return result;
                    } else if (limitedSession) {
                        //no redirect limited
                        logger.debug("Received session query ack:\n" + reply.toString());
                        logger.debug(RadiusCoaResult.FZONE_INTERNET_LIMITED);
                        System.out.println("Received session query ack:\n" + reply.toString());
                        result.setResultType(RadiusCoaResult.FZONE_INTERNET_LIMITED);
                        return result;
                    } else {
                        //no redirect ilimited
                        logger.debug("Received session query ack:\n" + reply.toString());
                        logger.debug(RadiusCoaResult.FZONE_INTERNET_FULL);
                        System.out.println("Received session query ack:\n" + reply.toString());
                        result.setResultType(RadiusCoaResult.FZONE_INTERNET_FULL);
                        return result;
                    }
                }
            } else {
                logger.debug("Received session query nack RETRY NULL\n");
            }
//                reintentos++;
//            }
            logger.debug("Received session query nack:\n" + reply.toString());
            System.out.println("Received session query nack:\n" + reply.toString());
            result.setResultType(RadiusCoaResult.UNKNOWN);
            logger.debug(RadiusCoaResult.UNKNOWN);
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
        } finally {
            if (radiusClient != null) {
                radiusClient.close();
            }
        }
    }

    public RadiusResult sessionQueryRedirect2(String radiusServer, String username, String ciscoAccountInfo) {
        AttributeFactory.loadAttributeDictionary("net.jradius.dictionary.AttributeDictionaryImpl");
        RadiusResult result = new RadiusResult();
        RadiusClient radiusClient = null;
        try {
            radiusClient = new RadiusClient(InetAddress.getByName(radiusServer), // InetAddress - Address of remote RADIUS Server
                    sharedSecret, port, port, 10); // String - Shared Secret for remote RADIUS Server
            AttributeList lista = new AttributeList();
            lista.add(new Attr_UserName(username));
            Attr_CiscoAVPair a = new Attr_CiscoAVPair();
            a.setValue("subscriber:command=account-status-query");
            lista.add(a);
            lista.add(new Attr_CiscoAccountInfo("S" + ciscoAccountInfo));
            CoARequest request = new CoARequest(radiusClient, lista);
            logger.debug("Sending sessionQuery:\n" + request.toString());
            System.out.println("Sending sessionQuery:\n" + request.toString());

            RadiusPacket reply = null;
//            int reintentos = 0;
//            while (reintentos < 3) {
//                logger.debug("it " + reintentos + "\n");
            reply = radiusClient.changeOfAuth((CoARequest) request, 1);
            ManagerStatsRadiusCoA.getInstance().incrementSessionQuery();
            result.setReply(reply);
            if (reply != null) {
                boolean registration = false;
                boolean invalidSession = false;
                boolean limitedSession = false;
                for (RadiusAttribute att : reply.getAttributes().getAttributeList()) {
                    if (att.getAttributeName().equals("Cisco-Account-Info")) {
                        if (att.getValue().toString().contains("N1L4RDT_SERVICE")) {
                            registration = true;
                        }
                        if (att.getValue().toString().contains("N1REDIRECT_PORT80")) {
                            limitedSession = true;
                        }
                    }
                    //Reply-Message = No valid Session
                    if (att.getAttributeName().equals("Reply-Message")) {
                        if (att.getValue().toString().contains("No valid Session")) {
                            invalidSession = true;
                        }
                    }
                }
                //FZONE_INTERNET_FULL,FZONE_INTERNET_LIMITED,FZONE_REDIRECT
                if (!invalidSession) {
                    if (registration) {
                        //redirect
                        logger.debug("Received session query ack:\n" + reply.toString());
                        logger.debug(RadiusCoaResult.FZONE_REDIRECT);
                        System.out.println("Received session query ack:\n" + reply.toString());
                        result.setResultType(RadiusCoaResult.FZONE_REDIRECT);
                        return result;
                    } else if (limitedSession) {
                        //no redirect limited
                        logger.debug("Received session query ack:\n" + reply.toString());
                        logger.debug(RadiusCoaResult.FZONE_INTERNET_LIMITED);
                        System.out.println("Received session query ack:\n" + reply.toString());
                        result.setResultType(RadiusCoaResult.FZONE_INTERNET_LIMITED);
                        return result;
                    } else {
                        //no redirect ilimited
                        logger.debug("Received session query ack:\n" + reply.toString());
                        logger.debug(RadiusCoaResult.FZONE_INTERNET_FULL);
                        System.out.println("Received session query ack:\n" + reply.toString());
                        result.setResultType(RadiusCoaResult.FZONE_INTERNET_FULL);
                        return result;
                    }
                }
            } else {
                logger.debug("Received session query nack RETRY NULL\n");
            }
//                reintentos++;
//            }
            logger.debug("Received session query nack:\n" + reply.toString());
            System.out.println("Received session query nack:\n" + reply.toString());
            result.setResultType(RadiusCoaResult.UNKNOWN);
            logger.debug(RadiusCoaResult.UNKNOWN);
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
        } finally {
            if (radiusClient != null) {
                radiusClient.close();
            }
        }
    }

    public RadiusResult sessionQuery(String radiusServer, String username, String ciscoAccountInfo) {
        AttributeFactory.loadAttributeDictionary("net.jradius.dictionary.AttributeDictionaryImpl");
        RadiusResult result = new RadiusResult();
        RadiusClient radiusClient = null;
        try {
            radiusClient = new RadiusClient(InetAddress.getByName(radiusServer), // InetAddress - Address of remote RADIUS Server
                    sharedSecret, port, port, 10); // String - Shared Secret for remote RADIUS Server
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
            ManagerStatsRadiusCoA.getInstance().incrementSessionQuery();
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
        } finally {
            if (radiusClient != null) {
                radiusClient.close();
            }
        }
    }

    public RadiusResult sessionQuery2(String radiusServer, String username, String ciscoAccountInfo) {
        AttributeFactory.loadAttributeDictionary("net.jradius.dictionary.AttributeDictionaryImpl");
        RadiusResult result = new RadiusResult();
        RadiusClient radiusClient = null;
        try {
            radiusClient = new RadiusClient(InetAddress.getByName(radiusServer), // InetAddress - Address of remote RADIUS Server
                    sharedSecret, port, port, 10); // String - Shared Secret for remote RADIUS Server
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
            ManagerStatsRadiusCoA.getInstance().incrementSessionQuery();
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
        } finally {
            if (radiusClient != null) {
                radiusClient.close();
            }
        }
    }

//    public RadiusResult sessionQuery2(String radiusServer, String username, String ciscoAccountInfo) {
//        AttributeFactory.loadAttributeDictionary("net.jradius.dictionary.AttributeDictionaryImpl");
//        RadiusResult result = new RadiusResult();
//        RadiusClient radiusClient = null;
//        try {
//            radiusClient = new RadiusClient(InetAddress.getByName(radiusServer), // InetAddress - Address of remote RADIUS Server
//                    sharedSecret, port, port, 10); // String - Shared Secret for remote RADIUS Server
//            AttributeList lista = new AttributeList();
//            lista.add(new Attr_UserName(username));
//            Attr_CiscoAVPair a = new Attr_CiscoAVPair();
//            a.setValue("subscriber:command=account-status-query");
//            lista.add(a);
//            lista.add(new Attr_CiscoAccountInfo("S" + ciscoAccountInfo));
//            CoARequest request = new CoARequest(radiusClient, lista);
//            logger.debug("Sending sessionQuery:\n" + request.toString());
//            System.out.println("Sending sessionQuery:\n" + request.toString());
//            RadiusPacket reply = radiusClient.changeOfAuth((CoARequest) request, 1);
//    ManagerStatsRadiusCoA.getInstance().incrementSessionQuery();
//            result.setReply(reply);
//            //REGISTRATION -> login
//            boolean valid = false;
//            if (reply == null) {
//                result.setResultType(RadiusCoaResult.INVALID_SESSION);
//                if (reply != null) {
//                    logger.debug("Received session query ack/nack:\n" + reply.toString());
//                    System.out.println("Received session query ack/nack:\n" + reply.toString());
//                }
//                return result;
//            } else {
//                //chequeo que tenga el attributo username y desp el servicio de redireccion
//                for (RadiusAttribute att : reply.getAttributes().getAttributeList()) {
//                    if (att.getAttributeName().equals("User-Name")) {
//                        valid = true;
//                        break;
//                    }
//                }
//                if (!valid) {
//                    result.setResultType(RadiusCoaResult.INVALID_SESSION);
//                    if (reply != null) {
//                        logger.debug("Received session query ack/nack:\n" + reply.toString());
//                        System.out.println("Received session query ack/nack:\n" + reply.toString());
//                    }
//                    return result;
//                } else {
//                    valid = false;
//                    for (RadiusAttribute att : reply.getAttributes().getAttributeList()) {
//                        if (att.getAttributeName().equals("Cisco-Account-Info")) {
//                            if (att.getValue().toString().contains("RDT_SERVICE")) {
//                                valid = true;
//                                break;
//                            }
//                        }
//                    }
//                    if (!valid) {
//                        result.setResultType(RadiusCoaResult.INVALID_SESSION);
//                        if (reply != null) {
//                            logger.debug("Received session query ack/nack:\n" + reply.toString());
//                            System.out.println("Received session query ack/nack:\n" + reply.toString());
//                        }
//                        return result;
//                    }
//                }
//            }
//            logger.debug("Received session query ack/nack:\n" + reply.toString());
//            System.out.println("Received session query ack/nack:\n" + reply.toString());
//            result.setResultType(RadiusCoaResult.VALID_SESSION);
//            return result;
//        } catch (Exception ex) {
//            loggerError.error("session query", ex);
//            ex.printStackTrace();
//            StringWriter sw = new StringWriter();
//            PrintWriter pw = new PrintWriter(sw);
//            ex.printStackTrace(pw);
//            result.setException(sw.toString());
//            result.setResultType(RadiusCoaResult.UNKNOWN);
//            return result;
//        } finally {
//            if (radiusClient != null) {
//                radiusClient.close();
//            }
//        }
//    }
    public RadiusResult activateService(String radiusServer, String username, String password, String ciscoAccountInfo, String service) {
        AttributeFactory.loadAttributeDictionary("net.jradius.dictionary.AttributeDictionaryImpl");
        RadiusResult result = new RadiusResult();
        RadiusClient radiusClient = null;
        try {
            radiusClient = new RadiusClient(InetAddress.getByName(radiusServer), // InetAddress - Address of remote RADIUS Server
                    sharedSecret, port, port, 10); // String - Shared Secret for remote RADIUS Server
            AttributeList lista = new AttributeList();
            lista.add(new Attr_UserName(username));
            if (password != null) {
                Attr_CiscoSubscriberPassword b = new Attr_CiscoSubscriberPassword();
                b.setValue(makeCiscoSubscriberPasswordValue2(radiusClient, password));
                lista.add(b);
            }
            Attr_CiscoAVPair a = new Attr_CiscoAVPair();
            a.setValue("subscriber:command=activate-service");
            lista.add(a, false);
            a = new Attr_CiscoAVPair();
            a.setValue("subscriber:service-name=" + service);
            lista.add(a, false);
//            a = new Attr_CiscoAVPair();
//            a.setValue("subscriber:precedence=<precedence-number>");
//            lista.add(a);
            a = new Attr_CiscoAVPair();
            a.setValue("subscriber:activation-mode=replace-all");
            lista.add(a, false);
            lista.add(new Attr_CiscoAccountInfo("S" + ciscoAccountInfo));
            CoARequest request = new CoARequest(radiusClient, lista);
            logger.debug("Sending logon:\n" + request.toString());
            RadiusPacket reply = radiusClient.changeOfAuth((CoARequest) request, 1);
            ManagerStatsRadiusCoA.getInstance().incrementActivateService();
            logger.debug("reply: " + (reply != null ? reply.getCode() : "null"));
            result.setReply(reply);
            if (reply != null) {
                if (reply.getCode() == 44) {
                    //ACK
                    logger.debug("Received logon ack:\n" + reply.toString());
                    result.setResultType(RadiusCoaResult.LOGON);
                    return result;
                } else {
                    //ANACK
                    RadiusAttribute att = reply.getAttributes().get("Reply-Message");
                    if (att != null) {
                        if (att.getValue().toString().equals("Memory or internal error")) {
                            result.setResultType(RadiusCoaResult.MEMORY_OR_INTERNAL_ERROR);
                            logger.debug("Received logon nack1:\n" + reply.toString());
                            return result;
                        } else if (att.getValue().toString().equals("No valid Session")) {
                            result.setResultType(RadiusCoaResult.INVALID_USERNAME_AND_PASSWORD);
                            logger.debug("Received logon nack2:\n" + reply.toString());
                            return result;
                        } else {
                            logger.debug("Received logon nack3:\n" + reply.toString());
                            result.setResultType(RadiusCoaResult.UNKNOWN);
                            return result;
                        }
                    } else {
                        result.setResultType(RadiusCoaResult.UNKNOWN);
                        return result;
                    }
                }
            } else {
                result.setResultType(RadiusCoaResult.UNKNOWN);
                return result;
            }
        } catch (Exception ex) {
            loggerError.error("Error en activateService.", ex);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            result.setException(sw.toString());
            result.setResultType(RadiusCoaResult.UNKNOWN);
            return result;
        } finally {
            if (radiusClient != null) {
                radiusClient.close();
            }
        }
    }

    public RadiusResult deactivateService(String radiusServer, String username, String password, String ciscoAccountInfo, String service) {
        AttributeFactory.loadAttributeDictionary("net.jradius.dictionary.AttributeDictionaryImpl");
        RadiusResult result = new RadiusResult();
        RadiusClient radiusClient = null;
        try {
            radiusClient = new RadiusClient(InetAddress.getByName(radiusServer), // InetAddress - Address of remote RADIUS Server
                    sharedSecret, port, port, 10); // String - Shared Secret for remote RADIUS Server
            AttributeList lista = new AttributeList();
            lista.add(new Attr_UserName(username));
            if (password != null) {
                Attr_CiscoSubscriberPassword b = new Attr_CiscoSubscriberPassword();
                b.setValue(makeCiscoSubscriberPasswordValue2(radiusClient, password));
                lista.add(b);
            }
            Attr_CiscoAVPair a = new Attr_CiscoAVPair();
            a.setValue("subscriber:command=deactivate-service");
            lista.add(a, false);
            a = new Attr_CiscoAVPair();
            a.setValue("subscriber:service-name=" + service);
            lista.add(a, false);
            lista.add(new Attr_CiscoAccountInfo("S" + ciscoAccountInfo));
            CoARequest request = new CoARequest(radiusClient, lista);
            logger.debug("Sending logon:\n" + request.toString());
            RadiusPacket reply = radiusClient.changeOfAuth((CoARequest) request, 1);
            ManagerStatsRadiusCoA.getInstance().incrementDeactivateService();
            logger.debug("reply: " + (reply != null ? reply.getCode() : "null"));
            result.setReply(reply);
            if (reply != null) {
                if (reply.getCode() == 44) {
                    //ACK
                    logger.debug("Received logon ack:\n" + reply.toString());
                    result.setResultType(RadiusCoaResult.LOGON);
                    return result;
                } else {
                    //ANACK
                    RadiusAttribute att = reply.getAttributes().get("Reply-Message");
                    if (att != null) {
                        if (att.getValue().toString().equals("Memory or internal error")) {
                            result.setResultType(RadiusCoaResult.MEMORY_OR_INTERNAL_ERROR);
                            logger.debug("Received logon nack1:\n" + reply.toString());
                            return result;
                        } else if (att.getValue().toString().equals("No valid Session")) {
                            result.setResultType(RadiusCoaResult.INVALID_USERNAME_AND_PASSWORD);
                            logger.debug("Received logon nack2:\n" + reply.toString());
                            return result;
                        } else {
                            logger.debug("Received logon nack3:\n" + reply.toString());
                            result.setResultType(RadiusCoaResult.UNKNOWN);
                            return result;
                        }
                    } else {
                        result.setResultType(RadiusCoaResult.UNKNOWN);
                        return result;
                    }
                }
            } else {
                result.setResultType(RadiusCoaResult.UNKNOWN);
                return result;
            }
        } catch (Exception ex) {
            loggerError.error("Error en deactivate", ex);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            result.setException(sw.toString());
            result.setResultType(RadiusCoaResult.UNKNOWN);
            return result;
        } finally {
            if (radiusClient != null) {
                radiusClient.close();
            }
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