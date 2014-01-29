/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua_parser;

import java.io.IOException;

/**
 *
 * @author bruno
 */
public class TestParser {

    public TestParser() {
        try {
//            String uaString = "Mozilla/5.0 (iPhone; CPU iPhone OS 5_1_1 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1 Mobile/9B206 Safari/7534.48.3";
            String uaString = "Mozilla/5.0 (X11; Linux i686) AppleWebKit/536.11 (KHTML, like Gecko) Chrome/20.0.1132.57 Safari/536.11";

            Parser uaParser = new Parser();
            Client c = uaParser.parse(uaString);

            System.out.println(c.userAgent.family); // => "Mobile Safari"
            System.out.println(c.userAgent.major);  // => "5"
            System.out.println(c.userAgent.minor);  // => "1"

            System.out.println(c.os.family);        // => "iOS"
            System.out.println(c.os.major);         // => "5"
            System.out.println(c.os.minor);         // => "1"

            System.out.println(c.device.family);    // => "iPhone"
            System.out.println(c.device.isMobile);  // => true
            System.out.println(c.device.isSpider);  // => false
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TestParser();
    }
}
