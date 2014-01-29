/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snmp;

import java.util.Calendar;
import java.util.List;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.VariableBinding;

/**
 *
 * @author bruno
 */
public class TestSNMP {

    public TestSNMP() {
        solver();
    }

    public static void main(String args[]) {
        new TestSNMP();
    }

    private void solver() {
//        .1.3.6.1.4.1.14179.2.1.4.1.2  moblie station ip (index mac)
//
//        .1.3.6.1.4.1.14179.2.1.4.1.4  mobile station ap mac
//
//        .1.3.6.1.4.1.14179.2.2.1.1.3  ap name
//
//        .1.3.6.1.4.1.14179.2.2.1.1.4  ap location
//
//        .1.3.6.1.4.1.14179.2.2.1.1.19 ap ip address


        String ip = "10.246.128.2";//10.246.128.2 10.246.128.3
        String community = "F1b3rt3lZ0n3"; //F1b3rt3lZ0n3



        String clientIP = "172.17.24.241";


        SnmpUtility util = new SnmpUtility(SnmpUtility.VERSION_2C, ip);
        Object obj = util.get(new OID(".1.3.6.1.2.1.1.1.0"), community);
        System.out.println(obj);
//        printAll(obj, util, community);
        System.out.println("---------------------------------------------------");
        System.out.println(Calendar.getInstance().getTime());
        String mbMacInInt = null;
        String mbMac = null;

        List<VariableBinding> vbs = util.getSubtree(new OID(".1.3.6.1.4.1.14179.2.1.4.1.2"), community, 100);
        for (VariableBinding vb : vbs) {
            if (vb.getVariable().toString().equals(clientIP)) {
                mbMacInInt = vb.getOid().toString().replaceAll("1.3.6.1.4.1.14179.2.1.4.1.2.", "");
                System.out.println("Encontro mac: " + mbMacInInt);
                mbMac = macStringIntToMacHex(mbMacInInt);
                System.out.println("MB Mac: " + mbMac);
                break;
            }
        }
        obj = util.get(new OID(".1.3.6.1.4.1.14179.2.1.4.1.4." + mbMacInInt), community);
        System.out.println("MS APmac:" + obj);
        String apMac = obj.toString();
        String apMacInInt = macHexaToMacStringInt(obj.toString());

        obj = util.get(new OID(".1.3.6.1.4.1.14179.2.2.1.1.3." + apMacInInt), community);
        System.out.println("APName:" + obj);
        obj = util.get(new OID(".1.3.6.1.4.1.14179.2.2.1.1.4." + apMacInInt), community);
        System.out.println("APLocation:" + obj);
        obj = util.get(new OID(".1.3.6.1.4.1.14179.2.2.1.1.19." + apMacInInt), community);
        System.out.println("APIPAddress:" + obj);
        System.out.println(Calendar.getInstance().getTime());
        System.out.println("--------------------------------------------------------------------------------");
    }

    private void printAll(Object obj, SnmpUtility util, String community) {
        List<VariableBinding> vbs = util.getSubtree(new OID(".1.3.6.1.4.1.14179.2.1.4.1.2"), community, 100);
        for (VariableBinding vb : vbs) {
            System.out.println(vb.getOid() + ":=" + vb.getVariable().toString());
        }
        vbs = util.getSubtree(new OID(".1.3.6.1.4.1.14179.2.1.4.1.4"), community, 100);
        for (VariableBinding vb : vbs) {
            System.out.println(vb.getOid() + ":=" + vb.getVariable().toString());
        }
        vbs = util.getSubtree(new OID(".1.3.6.1.4.1.14179.2.2.1.1.3"), community, 100);
        for (VariableBinding vb : vbs) {
            System.out.println(vb.getOid() + ":=" + vb.getVariable().toString());
        }
        vbs = util.getSubtree(new OID(".1.3.6.1.4.1.14179.2.2.1.1.4"), community, 100);
        for (VariableBinding vb : vbs) {
            System.out.println(vb.getOid() + ":=" + vb.getVariable().toString());
        }
        vbs = util.getSubtree(new OID(".1.3.6.1.4.1.14179.2.2.1.1.19"), community, 100);
        for (VariableBinding vb : vbs) {
            System.out.println(vb.getOid() + ":=" + vb.getVariable().toString());
        }
    }

    public String macHexaToMacStringInt(String mac) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (String a : mac.split(":")) {
            if (i == 5) {
                sb.append(hexToInteger(a).toString());
            } else {
                sb.append(hexToInteger(a).toString()).append(".");
            }
            i++;
        }
        return sb.toString();
    }

    public Integer hexToInteger(String hexValue) {
        return Integer.parseInt(hexValue, 16);
    }

    public String integerToHex(Integer value, Integer charLength) {
        String hexString = Integer.toHexString(value);
        for (int i = hexString.length(); i < charLength; i++) {
            hexString = "0" + hexString;
        }
        return hexString;
    }

    private String macStringIntToMacHex(String mbMacInInt) {
        StringBuilder sb = new StringBuilder();
        for (String a : mbMacInInt.split("\\.")) {
            sb.append(integerToHex(Integer.valueOf(a), 2).toString());
        }
        return sb.toString();
    }
}
