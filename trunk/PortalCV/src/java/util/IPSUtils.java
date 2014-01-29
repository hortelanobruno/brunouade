/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author bruno
 */
public class IPSUtils {

    public static final int longSizeToParse = 4;

    public static Long[] getIpEnd(String ipAddrees) {
        String[] ipMask = ipAddrees.split("/");
        Long[] ipComplete = new Long[2];
        if (ipMask.length > 1) {
            int SingleIpMask = ((longSizeToParse * 8) - Integer.parseInt(ipMask[1]));
            if (SingleIpMask != 0) {
                ipComplete[0] = formatIpToLong(ipMask[0]);
                ipComplete[1] = ((ipComplete[0]) | ((int) (Math.pow(2, SingleIpMask)) - 1));
                return ipComplete;
            }
        }
        return null;
    }

    public static long formatIpToLong(String ipAddress) throws IllegalArgumentException {
        Pattern pattern = Pattern.compile(":?((\\d{1,3}).(\\d{1,3}).(\\d{1,3}).(\\d{1,3})(/(\\d{1,2}))?);?");
        Matcher match = pattern.matcher(ipAddress);
        long responseValue = 0;
        if (match.matches()) {
            int octeto = 8;
            for (int i = 0; i <= 3; i++) {
                responseValue += (long) ((Long.parseLong(match.group(i + 2)) << (octeto * (3 - i))));
            }
            return responseValue;
        }
        throw new IllegalArgumentException();
    }

    public static Long ipToLong(String addr) {
        String[] addrArray = addr.split("\\.");
        long num = 0;
        for (int i = 0; i < addrArray.length; i++) {
            int power = 3 - i;
            num += ((Long.parseLong(addrArray[i]) % 256 * Math.pow(256, power)));
        }
        return num;
    }
}
