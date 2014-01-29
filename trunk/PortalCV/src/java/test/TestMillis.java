/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.Calendar;

/**
 *
 * @author bruno
 */
public class TestMillis {

    public TestMillis() {
        solver2();
    }

    public static void main(String args[]) {
        new TestMillis();
    }

    private void solver() {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(clientLogin.getDateLastLogin());
        long aux = 1352904526000L;

        //30*24*60*1000
        System.out.println("aux : " + aux);
//        System.out.println("(cal.getTimeInMillis() + (30 * 24 * 60 * 60 * 1000)): " + (aux + (30 * 24 * 60 * 60 * 1000)) + ". Calendar.getInstance().getTimeInMillis(): " + Calendar.getInstance().getTimeInMillis());
//        if ((aux + (30 * 24 * 60 * 60 * 1000)) > Calendar.getInstance().getTimeInMillis()) {
//            
//        }
//        
        long aux3 = (30 * 24 * 60 * 60 * 1000L);
        long aux2 = (aux + 1);
        System.out.println("aux2: " + aux2);
        System.out.println("aux3: " + aux3);
        System.out.println("result: " + (aux3 + aux2));
    }

    private void solver2() {
        Integer defaultAutoLoginValidTimeInMinutes = 60 * 24 * 30;
        long aux = System.currentTimeMillis() - 1378071177000L;
        int validTime;
        validTime = defaultAutoLoginValidTimeInMinutes;
        System.out.println("aux: " + aux + ". otraParte: " + (1000L * 60 * validTime));
        if (aux < (1000L * 60 * validTime)) {
            System.out.println("valido");
        } else {
            System.out.println("fuera de fecha");
        }
    }
}
