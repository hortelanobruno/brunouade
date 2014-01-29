/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snmp.trap.receiver;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.log4j.Logger;

import org.snmp4j.CommandResponder;
import org.snmp4j.CommandResponderEvent;
import org.snmp4j.MessageDispatcherImpl;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.mp.MPv1;
import org.snmp4j.mp.MPv2c;
import org.snmp4j.mp.MPv3;
import org.snmp4j.security.AuthMD5;
import org.snmp4j.security.PrivDES;
import org.snmp4j.security.SecurityModels;
import org.snmp4j.security.SecurityProtocols;
import org.snmp4j.security.USM;
import org.snmp4j.security.UsmUser;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.MultiThreadedMessageDispatcher;
import org.snmp4j.util.ThreadPool;
import session.RadiusServerFacadeLocal;

public class TrapReceiverIntegration implements CommandResponder {

    private Logger logger = Logger.getLogger("snmpTrap");
    private String ip = "10.0.0.6";
    private String port = "162";
    private MultiThreadedMessageDispatcher dispatcher;
    private Snmp snmp = null;
    private Address listenAddress;
    private ThreadPool threadPool;
    private RadiusServerFacadeLocal testEJB;
    private final String OID_DISASSOCIATE = "1.3.6.1.4.1.14179.2.6.3.1";
    private final String OID_ASSOCIATE = "1.3.6.1.4.1.14179.2.6.3.53";
    private final String OID_CPE_IP = "1.3.6.1.4.1.14179.2.6.2.43.0";
    private final String OID_CPE_MAC = "1.3.6.1.4.1.14179.2.6.2.34.0";
    private final String OID_AP_MAC = "1.3.6.1.4.1.14179.2.6.2.35.0";
    private final String OID_MSG_TYPE = "1.3.6.1.6.3.1.1.4.1.0";

    public TrapReceiverIntegration() {
        try {
            initRemoteInterface();
        } catch (Exception ex) {
            logger.error("Error en new TrapReceiverIntegration.", ex);
        }
    }

    public TrapReceiverIntegration(String ip, String port) {
        try {
            this.ip = ip;
            this.port = port;
            initRemoteInterface();
        } catch (Exception ex) {
            logger.error("Error en new TrapReceiverIntegration. ip: " + ip + ". port: " + port, ex);
        }
    }

    private void initRemoteInterface() throws NamingException {
        //NewSessionBean2 : [java:global/PortalCV/NewSessionBean2!session.NewSessionBean2Local, java:global/PortalCV/NewSessionBean2]
        Properties props = new Properties();

        props.put("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
        props.put("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
        props.put("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
        props.put("org.omg.CORBA.ORBInitialHost", "localhost");
        props.put("org.omg.CORBA.ORBInitialPort", "3700");
        InitialContext ctx = new InitialContext(props);
        testEJB = (RadiusServerFacadeLocal) ctx.lookup("session.RadiusServerFacadeLocal");
    }

    private void init() throws UnknownHostException, IOException {
        threadPool = ThreadPool.create("Trap", 2);
        dispatcher = new MultiThreadedMessageDispatcher(threadPool, new MessageDispatcherImpl());
        logger.info("Creating udp Address " + ip + "/" + port);
        listenAddress = new UdpAddress(ip + "/" + port);
        TransportMapping transport;

        transport = new DefaultUdpTransportMapping((UdpAddress) listenAddress);

        snmp = new Snmp(dispatcher, transport);
        snmp.getMessageDispatcher().addMessageProcessingModel(new MPv1());
        snmp.getMessageDispatcher().addMessageProcessingModel(new MPv2c());
        snmp.getMessageDispatcher().addMessageProcessingModel(new MPv3());
        final USM usm = new USM(SecurityProtocols.getInstance(), new OctetString(MPv3.createLocalEngineID()), 0);
        SecurityModels.getInstance().addSecurityModel(usm);
        String userName = "anuj";
        String authenticationPwd = "temptemptemptemp";
        String privacyPwd = "temptemptemptemp";
        byte[] localEngineId = MPv3.createLocalEngineID();
        //Add user to the USM
        snmp.getUSM().addUser(
                new OctetString(userName),
                new OctetString(localEngineId),
                new UsmUser(new OctetString(userName), AuthMD5.ID, new OctetString(authenticationPwd), PrivDES.ID,
                new OctetString(privacyPwd), new OctetString(localEngineId)));

        snmp.listen();
    }

    public void run() {
        try {
            init();
            snmp.addCommandResponder(this);
        } catch (Exception ex) {
            logger.error("Error en run TrapReceiverIntegration.", ex);
        }
    }

    @Override
    public synchronized void processPdu(final CommandResponderEvent respEvnt) {
        if (respEvnt != null && respEvnt.getPDU() != null) {
            final Vector recVBs = respEvnt.getPDU().getVariableBindings();
            Map<String, String> maps = new HashMap<String, String>();
            for (int i = 0; i < recVBs.size(); i++) {
                final VariableBinding recVB = (VariableBinding) recVBs.elementAt(i);
                maps.put(recVB.getOid().toString(), recVB.getVariable().toString());
            }
            //Procesar mensaje
            processMsg(maps);
        }
    }

    private void processMsg(Map<String, String> maps) {
        if (maps.containsKey(OID_MSG_TYPE)) {
            if (maps.get(OID_MSG_TYPE).equals(OID_ASSOCIATE)) {
                logger.info("Llego start.");
                if (maps.get(OID_CPE_IP).equalsIgnoreCase("0.0.0.0")) {
                    logger.info("Info with ip 0.0.0.0. OID_CPE_MAC: " + maps.get(OID_CPE_MAC) + ". OID_AP_MAC: " + maps.get(OID_AP_MAC));
                } else {
                    testEJB.acctStartWLC(maps.get(OID_CPE_IP), maps.get(OID_CPE_MAC), maps.get(OID_AP_MAC),"snmpblabla");
                }
            } else if (maps.get(OID_MSG_TYPE).equals(OID_DISASSOCIATE)) {
                logger.info("Llego stop");
                testEJB.acctStopWLC(maps.get(OID_CPE_IP), null, null,"snmpblabla");
            } else {
                logger.warn("Invalid msg type. Type: " + maps.get(OID_MSG_TYPE) + ". Map: " + maps);
            }
        } else {
            logger.warn("Invalid msg. Map: " + maps);
        }
    }
}