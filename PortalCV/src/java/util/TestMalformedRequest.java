/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author bruno
 */
public class TestMalformedRequest {

    public TestMalformedRequest() {
        solver();
    }

    public static void main(String args[]) {
        new TestMalformedRequest();
    }

    private void solver() {
        try {
            Socket socket = new Socket("10.0.0.25", 80);
            socket.setSoTimeout(30000);

//            PrintWriter pw = new PrintWriter(socket.getOutputStream());
//            pw.print("asdfasdfasdfsqqweq");
//            pw.flush();
//            Thread.sleep(35 * 1000);
//            pw.close();

            OutputStream output = socket.getOutputStream();
            output.write("asdfasdfasdfsqqweq".getBytes());
            Thread.sleep(35 * 1000);
            output.flush();
            output.close();


            socket.close();
            System.out.println("FIN");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
