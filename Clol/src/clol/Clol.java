/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clol;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class Clol {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Scanner input = new Scanner (System.in);
        Socket s = new Socket("localhost", 7775);
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        String str = "";
        DataInputStream dis = null;
        try {
            System.out.print("Please enter your message: ");
            String msg = input.nextLine();
            dout.writeUTF(msg);
        } catch(Exception e) {}

//         Three Way
        try {
            dis = new DataInputStream(s.getInputStream());
            str =(String)dis.readUTF();
            System.out.println("Data from server is " + str);
        } catch (Exception e) {}
        
        try {
            System.out.print("Please enter your message: ");
            String msg = input.nextLine();
            dout.writeUTF(msg);
        } catch(Exception e) {}
        
        input.close();
        dout.close();
//        dis.close();
        s.close();
    }
    
}
