/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serversoc;

import java.io.DataInputStream;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author user
 */
public class ServerSoc {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(7775);
        Scanner input = new Scanner(System.in);
        System.out.println("Server is waiting the client");
        Socket s = ss.accept();
        System.out.println("Server is connected");
        String str = "";
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        DataInputStream dis = null;

        do {
            System.out.print("Please enter message to client : ");
            str = input.nextLine();
            
            dout.writeUTF(str);
            dout.flush();
            
            try {
                dis = new DataInputStream(s.getInputStream());
                str= (String)dis.readUTF();
                if(str.equals("quit")) {
                    break;
                }
                System.out.println("Client : " + str);
            } catch (Exception e) {
                
            }        
            
        } while(!str.equals("quit"));
        
        //Sent Client to close connection
        dout.writeUTF("quit");
        
        System.out.println("Server Connection close");
        input.close();
        dout.close();
        dis.close();
        s.close();
        
    }
    
}
