/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author user
 */
public class Inet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        InetAddress local = null;
        String localAddre = null;
        try {
            local = InetAddress.getLocalHost();
            localAddre = local.getHostAddress();
        } catch (UnknownHostException e) {
            System.err.println("Identity Crisis!");
            System.exit(0);
        }
        String strAddress = local.getHostName();
        System.out.println("Local Host = " + strAddress);
        System.out.println("Local Host = " + localAddre);
        
        // Another 1
        try {
            InetAddress address = InetAddress.getByName("www.utm.my");
            System.out.println("IP address: " + address.toString());
        } catch (UnknownHostException e) {
            System.err.println("Cannot Find the IP of given host.");
        }
    }
    
}
