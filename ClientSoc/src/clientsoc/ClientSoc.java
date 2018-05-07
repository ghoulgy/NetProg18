/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientsoc;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author user
 */
public class ClientSoc {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner (System.in);
        Socket s = new Socket("localhost", 7775);
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        String str = "";
        DataInputStream dis = null;
        
        do {
            System.out.print("Please enter your message: ");
            String msg = input.nextLine();
            dout.writeUTF(msg);
            dout.flush();
            try {
                dis = new DataInputStream(s.getInputStream());
                str =(String)dis.readUTF();
                if(str.equals("quit")){
                    break;
                }
                System.out.println("Data from server is " + str);
            } catch (Exception e) {}
        } while(!str.equals("quit"));

        System.out.println("Client Connection close");
        input.close();
        dout.close();
        dis.close();
        s.close();
    }
}
