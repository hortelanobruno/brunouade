/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snmp;

import entity.PortalcvAp;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.PDUFactory;
import org.snmp4j.util.TreeEvent;
import org.snmp4j.util.TreeUtils;

/**
 *
 * @author bruno
 */
public class SNMPGetAPsFromWSDL {

    private static Logger log = Logger.getLogger("portal");
    private static Logger logError = Logger.getLogger("portalError");
    private final static String OID_AP_MAC = "1.3.6.1.4.1.14179.2.2.1.1.1";
    private final static String OID_AP_NAME = "1.3.6.1.4.1.14179.2.2.1.1.3";
    private final static String OID_AP_LOCATION = "1.3.6.1.4.1.14179.2.2.1.1.4";
//    private final String OID_DISASSOCIATE = "1.3.6.1.4.1.14179.2.6.3.1";
//    private final String OID_DISASSOCIATE = "1.3.6.1.4.1.14179.2.6.3.1";

    public static PortalcvAp getSNMPFromWLC(String ip, String port, String comunity, String apMacOriginal) {
        UdpAddress targetAddress = new UdpAddress(ip + "/" + port);
        int version = SnmpConstants.version2c;
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString(comunity));
        target.setAddress(targetAddress);
        target.setVersion(version);
        target.setRetries(5);
        target.setTimeout(60000);
        TransportMapping transport;
        Snmp snmp;
        List lista;
        TreeEvent eventa;
        VariableBinding[] vbs;
        String lastoid = null;
        int oid = 0;
        String apMac;
        boolean encontro = false;
        PortalcvAp ap = null;
        log.info("Obtaining AP info via SNMP. WLC ip: " + ip + ". WSDL port: " + port + ". WSDL comunity: " + comunity + ". AP-MAC: " + apMacOriginal);
        try {
            transport = new DefaultUdpTransportMapping(new UdpAddress());
            snmp = new Snmp(transport);
            transport.listen();
            PDUFactory as = new DefaultPDUFactory();
            TreeUtils tree = new TreeUtils(snmp, as);
            tree.setMaxRepetitions(5000);
            lista = tree.getSubtree(target, new OID(OID_AP_MAC));
            for (Object a : lista) {
                if (!encontro) {
                    eventa = (TreeEvent) a;
                    vbs = eventa.getVariableBindings();
                    if (vbs != null) {
                        for (VariableBinding vb : vbs) {
                            oid = vb.getOid().last();
                            lastoid = vb.getOid().toString().replace(OID_AP_MAC, "");
                            apMac = vb.getVariable().toString().toLowerCase().replaceAll(":", "").replaceAll("-", "");
                            if (apMac.equals(apMacOriginal)) {
                                encontro = true;
                                log.info("Encontro AP.");
                                break;
                            }
                        }
                    } else {
                    }
                }
            }
            //Obtengo info
            if (encontro) {
                PDU pdu;
                pdu = new PDU();
                pdu.setType(PDU.GET);
                pdu.add(new VariableBinding(new OID(OID_AP_NAME + lastoid)));
                pdu.add(new VariableBinding(new OID(OID_AP_LOCATION + lastoid)));


                PDU re = snmp.send(pdu, target).getResponse();
                ap = new PortalcvAp();
                ap.setSNMPAPIndex("" + oid);
                ap.setMac(apMacOriginal);

                if (re != null && re.getVariableBindings() != null && !re.getVariableBindings().isEmpty()) {
                    for (VariableBinding vb : re.getVariableBindings()) {
                        if (vb.getOid().toString().contains(OID_AP_NAME)) {
                            ap.setName(vb.getVariable().toString());
                        } else if (vb.getOid().toString().contains(OID_AP_LOCATION)) {
                            ap.setDireccion(vb.getVariable().toString());
                        }
                    }
                }
                log.info("FIN AP.");
            } else {
                log.info("NULL.");
                return null;
            }
            transport.close();
            snmp.close();
        } catch (Exception ex) {
            logError.error("Error en getSNMPFromWSDL. ", ex);
        }
        return ap;
    }

    public static List<PortalcvAp> getAllAPsFromWLC(String ip, String port, String comunity) {
        UdpAddress targetAddress = new UdpAddress(ip + "/" + port);
        int version = SnmpConstants.version2c;
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString(comunity));
        target.setAddress(targetAddress);
        target.setVersion(version);
        target.setRetries(2);
        target.setTimeout(5000);
        TransportMapping transport;
        Snmp snmp;
        List lista;
        TreeEvent eventa;
        VariableBinding[] vbs;
        int oid;
        String lastoid;
        String apMac;
        boolean encontro = false;
        List<PortalcvAp> aps = new ArrayList<PortalcvAp>();
        PortalcvAp ap;
        log.info("Obtaining All APs info via SNMP. WLC ip: " + ip + ". WSDL port: " + port + ". WSDL comunity: " + comunity + ".");
        try {
            transport = new DefaultUdpTransportMapping(new UdpAddress());
            snmp = new Snmp(transport);
            transport.listen();
            PDUFactory as = new DefaultPDUFactory();
            TreeUtils tree = new TreeUtils(snmp, as);
            tree.setMaxRepetitions(5000);
            lista = tree.getSubtree(target, new OID(OID_AP_MAC));
            PDU pdu;
            pdu = new PDU();
            pdu.setType(PDU.GET);
            PDU re;
            for (Object a : lista) {
                if (!encontro) {
                    eventa = (TreeEvent) a;
                    vbs = eventa.getVariableBindings();
                    if (vbs != null) {
                        for (VariableBinding vb : vbs) {
                            oid = vb.getOid().last();
                            lastoid = vb.getOid().toString().replace(OID_AP_MAC, "");
                            apMac = vb.getVariable().toString().toLowerCase().replaceAll(":", "").replaceAll("-", "");
                            pdu.add(new VariableBinding(new OID(OID_AP_NAME + lastoid)));
                            pdu.add(new VariableBinding(new OID(OID_AP_LOCATION + lastoid)));
                            re = snmp.send(pdu, target).getResponse();
                            ap = new PortalcvAp();
                            ap.setSNMPAPIndex("" + oid);
                            ap.setMac(apMac);
                            if (re != null && re.getVariableBindings() != null && !re.getVariableBindings().isEmpty()) {
                                for (VariableBinding vb2 : re.getVariableBindings()) {
                                    if (vb2.getOid().toString().contains(OID_AP_NAME)) {
                                        ap.setName(vb2.getVariable().toString());
                                    } else if (vb2.getOid().toString().contains(OID_AP_LOCATION)) {
                                        ap.setDireccion(vb2.getVariable().toString());
                                    }
                                }
                            }
                            pdu.clear();
                            aps.add(ap);
                        }
                    }
                }
            }
            transport.close();
            snmp.close();
        } catch (Exception ex) {
            logError.error("Error en getAllAPsFromWLC. ", ex);
        }
        return aps;
    }

    public static void main(String args[]) {
        List<PortalcvAp> aps = getAllAPsFromWLC("10.246.128.3", "161", "F1b3rt3lZ0n3");
        for (PortalcvAp ap : aps) {
            System.out.println(ap.toStringAll());
        }
    }
}
