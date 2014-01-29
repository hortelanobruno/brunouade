/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package radiusserver;

import entity.PortalcvWlc;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Properties;
import javax.ejb.EJBException;
import javax.naming.InitialContext;
import org.apache.log4j.Logger;
import org.tinyradius.packet.AccessRequest;
import org.tinyradius.packet.AccountingRequest;
import org.tinyradius.packet.RadiusPacket;
import org.tinyradius.util.RadiusException;
import org.tinyradius.util.RadiusServer;
import session.RadiusServerFacadeLocal;
import stats.ManagerStatsClicks;
import stats.ManagerStatsRadiusAccounting;
import util.IPSUtils;

/**
 *
 * @author bruno
 */
public class RadiusServerImpl extends RadiusServer {

    private Logger logger = Logger.getLogger("radiusServer");
    private Logger loggerEvents = Logger.getLogger("radiusServerEvents");
    private String ip = "0.0.0.0";
    private String portAuthPort = "2012";
    private String portAcctPort = "2013";
    private RadiusServerFacadeLocal testEJB;
    private List wlcs;
    private Long ipInicial;
    private Long ipFinal;
    private String rangoValido = "172.17.0.0/19";

//    public static void main(String args[]) {
//        Long ipInicial;
//        Long ipFinal;
//        String rangoValido = "172.17.0.0/19";
//        String ip = "172.17.2.45";
//        Long[] ips = IPSUtils.getIpEnd(rangoValido);
//        if (ips == null) {
//            Long auxlong = IPSUtils.formatIpToLong(rangoValido);
//            ipInicial = auxlong;
//            ipFinal = auxlong;
//        } else {
//            ipInicial = ips[0];
//            ipFinal = ips[1];
//        }
//        System.out.println("ipInicial: " + ipInicial + ". ipFinal: " + ipFinal);
//        Long aux2 = IPSUtils.formatIpToLong(ip);
//        if (ipInicial <= aux2 && aux2 <= ipFinal) {
//            System.out.println("valido");
//        } else {
//            System.out.println("no valido");
//        }
//    }
    public RadiusServerImpl() {
        super();
        Long[] ips = IPSUtils.getIpEnd(rangoValido);
        if (ips == null) {
            Long auxlong = IPSUtils.formatIpToLong(rangoValido);
            ipInicial = auxlong;
            ipFinal = auxlong;
        } else {
            ipInicial = ips[0];
            ipFinal = ips[1];
        }
    }

    public RadiusServerImpl(String ip, String portAuthPort, String portAcctPort) {
        super();
        this.ip = ip;
        this.portAuthPort = portAuthPort;
        this.portAcctPort = portAcctPort;

        // Setea los valores long limites del range
        Long[] ips = IPSUtils.getIpEnd(rangoValido);
        if (ips == null) {
            Long auxlong = IPSUtils.formatIpToLong(rangoValido);
            ipInicial = auxlong;
            ipFinal = auxlong;
        } else {
            ipInicial = ips[0];
            ipFinal = ips[1];
        }
    }

    public void start() {
        try {
            initRemoteInterface();
            logger.info("Starting Radius Server. Ip: " + ip + ". portAuthPort: " + portAuthPort + ". portAcctPort: " + portAcctPort + ".");
            this.setListenAddress(InetAddress.getByName(ip));
            this.setAuthPort(Integer.parseInt(portAuthPort));
            this.setAcctPort(Integer.parseInt(portAcctPort));
            this.start(true, true);
            logger.info("Server started.");
        } catch (Exception ex) {
            logger.error("Error en start.", ex);
        }
    }

    private void initRemoteInterface() {
        try {
            //NewSessionBean2 : [java:global/PortalCV/NewSessionBean2!session.NewSessionBean2Local, java:global/PortalCV/NewSessionBean2]
            logger.info("Initing initRemoteInterface...");
            Properties props = new Properties();

            props.put("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
            props.put("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
            props.put("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
            props.put("org.omg.CORBA.ORBInitialHost", "localhost");
            props.put("org.omg.CORBA.ORBInitialPort", "3700");
            InitialContext ctx = new InitialContext(props);
            testEJB = (RadiusServerFacadeLocal) ctx.lookup("session.RadiusServerFacadeLocal");
            logger.info("Inited initRemoteInterface.");
        } catch (Exception ex) {
            logger.error("Error en initRemoteInterface.", ex);
        }
    }

    @Override
    public String getSharedSecret(InetSocketAddress client) {
//        logger.debug("getSharedSecret. client.getAddress().getHostAddress(): " + client.getAddress().getHostAddress() + ". wlcs: " + wlcs);
        PortalcvWlc wlc;
        if (wlcs != null && !wlcs.isEmpty()) {
            for (Object obj : wlcs) {
                wlc = (PortalcvWlc) obj;
                if (wlc.getIp().equals(client.getAddress().getHostAddress())) {
                    return wlc.getSecret();
                }
            }
        }
        if ("10.0.0.210".equals(client.getAddress().getHostAddress())) {
            return "callis";
        }
        logger.debug("secret not found. client.getAddress().getHostAddress(): " + client.getAddress().getHostAddress());
        return null;
//        if (client.getAddress().getHostAddress().equals("127.0.0.1")) {
//            return "testing123";
//        } else if (client.getAddress().getHostAddress().equals("10.246.128.2")) {
//            return "callis";
//        } else {
//            
//        }
    }

    @Override
    public String getUserPassword(String userName) {
        logger.debug("getUserPassword. userName: " + userName);
        if (userName.equals("mw")) {
            return "test";
        } else {
            return null;
        }
    }

    // Adds an attribute to the Access-Accept packet
    public RadiusPacket accessRequestReceived(AccessRequest accessRequest, InetSocketAddress client)
            throws RadiusException {
        logger.debug("Received Access-Request:\n" + accessRequest);
        RadiusPacket packet = super.accessRequestReceived(accessRequest, client);
        if (packet.getPacketType() == RadiusPacket.ACCESS_ACCEPT) {
            packet.addAttribute("Reply-Message", "Welcome " + accessRequest.getUserName() + "!");
        }
        ManagerStatsRadiusAccounting.getInstance().incrementAccStart();
        if (packet == null) {
            logger.debug("Ignore packet.");
        } else {
            logger.debug("Answer:\n" + packet);
        }
        return packet;
    }

    @Override
    public RadiusPacket accountingRequestReceived(AccountingRequest accountingRequest, InetSocketAddress client) throws RadiusException {
        RadiusPacket packet = null;
        String statusType;
        String cpeIP;
        String cpeMAC;
        String apMAC;
        String sessionId;
        try {
            long aux = System.currentTimeMillis();
            packet = super.accountingRequestReceived(accountingRequest, client);

            if (packet.getPacketType() == RadiusPacket.ACCESS_ACCEPT) {
                packet.addAttribute("Reply-Message", "Welcome " + accountingRequest.getUserName() + "!");
            }


            statusType = accountingRequest.getAttributeValue("Acct-Status-Type");
            cpeIP = accountingRequest.getAttributeValue("Framed-IP-Address");
            cpeMAC = accountingRequest.getAttributeValue("Calling-Station-Id");
            apMAC = accountingRequest.getAttributeValue("Called-Station-Id");
            sessionId = accountingRequest.getAttributeValue("Acct-Session-Id");


            if (cpeIP != null) {
                //Filtrar por IP 172.17.0.0/19
                Long aux2 = IPSUtils.formatIpToLong(cpeIP);
                if (ipInicial <= aux2 && aux2 <= ipFinal) {
                    logger.debug("Received Accounting-Request:\n" + accountingRequest);
                    logger.debug("CLIENT IP: " + client.getHostName());
                    logger.debug("BRUNOLI Framed-IP-Address: " + cpeIP);
                    logger.debug("BRUNOLI Calling-Station-Id: " + cpeMAC);
                    logger.debug("BRUNOLI Called-Station-Id: " + apMAC);
                    logger.debug("BRUNOLI Acct-Status-Type: " + statusType);
                    logger.debug("BRUNOLI Acct-Session-Id: " + sessionId);



                    if (!apMAC.equalsIgnoreCase("000000000000")) {
                        if (statusType.equalsIgnoreCase("start")) {
                            //Start
                            ManagerStatsRadiusAccounting.getInstance().incrementAcctStart();
                            testEJB.acctStartWLC(cpeIP, cpeMAC, apMAC, sessionId);
                        } else if (statusType.equalsIgnoreCase("alive")) {
                            //Alive
                            ManagerStatsRadiusAccounting.getInstance().incrementAcctAlive();
                            testEJB.acctAliveWLC(cpeIP, cpeMAC, apMAC, sessionId);
                        } else if (statusType.equalsIgnoreCase("stop")) {
                            //Stop
                            ManagerStatsRadiusAccounting.getInstance().incrementAcctStop();
                            testEJB.acctStopWLC(cpeIP, cpeMAC, apMAC, sessionId);
                        } else {
                            //other
                        }
                    } else {
                        if (statusType.equalsIgnoreCase("stop")) {
                            //Stop
                            ManagerStatsRadiusAccounting.getInstance().incrementAcctStop();
                            testEJB.acctStopWLC(cpeIP, cpeMAC, apMAC, sessionId);
                        } else {
                            loggerEvents.info("Descartando evento. Acct " + statusType + " cpeIP: " + cpeIP + " cpeMAC: " + cpeMAC + " apMAC: " + apMAC + ". sessionId: " + sessionId + ".");
                        }
                    }

                    if (packet == null) {
                        logger.debug("Ignore packet.");
                    } else {
                        logger.debug("Answer:\n" + packet);
                    }

                    aux = System.currentTimeMillis() - aux;
                    loggerEvents.info("Acct " + statusType + " cpeIP: " + cpeIP + " cpeMAC: " + cpeMAC + " apMAC: " + apMAC + ". sessionId: " + sessionId + ". Tardo: " + aux);
                } else {
                    loggerEvents.info("Descartando Acct. cpeIP: " + cpeIP + " cpeMAC: " + cpeMAC + " apMAC: " + apMAC + ". sessionId: " + sessionId + ".");
                }
            } else {
                loggerEvents.info("Descartando Acct. cpeIP: " + cpeIP + " cpeMAC: " + cpeMAC + " apMAC: " + apMAC + ". sessionId: " + sessionId + ".");
            }
        } catch (EJBException ex) {
            try {
                logger.error("Error en accountingRequestReceived.", ex);
                initRemoteInterface();
            } catch (Exception ex1) {
                logger.error("Error en accountingRequestReceived2.", ex1);
            }
        } catch (Exception ex) {
            logger.error("Error en accountingRequestReceived.", ex);
        }
        return packet;
    }

    public void close() {
        try {
            logger.info("closing...");
            this.stop();
            ManagerStatsClicks.getInstance().stop();
            ManagerStatsRadiusAccounting.getInstance().stop();
            logger.info("closed.");
        } catch (Exception ex) {
            logger.error("Error en close.", ex);
        }
    }

    public void setWLCs(List wlcs) {
        this.wlcs = wlcs;
    }
}
