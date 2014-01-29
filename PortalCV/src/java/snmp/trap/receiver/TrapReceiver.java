/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snmp.trap.receiver;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Vector;

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

public class TrapReceiver implements CommandResponder {

    private MultiThreadedMessageDispatcher dispatcher;
    private Snmp snmp = null;
    private Address listenAddress;
    private ThreadPool threadPool;
    private String[] args;

    public TrapReceiver(String[] args) {
        if (args != null && args.length != 4) {
            System.out.println("Wrong params (address username authpwd privacypwd). Ejemplo: 127.0.0.1/162 anuj temptemptemptemp temptemptemptemp");
        }
        this.args = args;
    }

    private void init() throws UnknownHostException, IOException {
        threadPool = ThreadPool.create("Trap", 2);
        dispatcher = new MultiThreadedMessageDispatcher(threadPool, new MessageDispatcherImpl());
        if (args != null && args.length >= 1) {
            listenAddress = new UdpAddress(args[0]);
        } else {
            listenAddress = new UdpAddress("127.0.0.1/162");
        }
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
        if (args != null && args.length == 4) {
            userName = args[1];
            authenticationPwd = args[2];
            privacyPwd = args[3];
        }

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
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public synchronized void processPdu(final CommandResponderEvent respEvnt) {
        if (respEvnt != null && respEvnt.getPDU() != null) {
            final Vector recVBs = respEvnt.getPDU().getVariableBindings();
            for (int i = 0; i < recVBs.size(); i++) {
                final VariableBinding recVB = (VariableBinding) recVBs.elementAt(i);
                System.out.println(recVB.getOid() + " : " + recVB.getVariable());
            }

        }
    }

    public static void main(final String[] args) {
        final TrapReceiver server = new TrapReceiver(args);
        server.run();
    }
}