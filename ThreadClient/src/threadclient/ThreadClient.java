/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadclient;
import java.net.*;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class ThreadClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        Socket s = new Socket("localhost", 7777);
        Scanner input = new Scanner (System.in);
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        DataInputStream din = new DataInputStream(s.getInputStream());
        String str = "";
        String msg = "";
        
        // While Loop
        do {
            System.out.print("Please enter your message: ");
            msg = input.nextLine();
            dout.writeUTF(msg);
            dout.flush();

            try {
                str =(String)din.readUTF();
                if(str.equals("Exit")){
                    break;
                }
                System.out.println("Server: " + str);
            } catch (IOException e) {}

        } while(!msg.equals("Exit"));
        // While Loop
        
//        System.out.print("Please enter your message: ");
//        String msg = input.nextLine();
//        dout.writeUTF(msg);
//        dout.flush();
//        
//        // Send
//        try {
//            str =(String)din.readUTF();
////            if(str.equals("quit")){
////                break;
////            }
//            System.out.println("Server: " + str);
//        } catch (IOException e) {}
//        
//        // Rcv
//        System.out.print("Please enter your message: ");
//        msg = input.nextLine();
//        dout.writeUTF(msg);
//        dout.flush();
//        
//        // Send
//        try {
//            str =(String)din.readUTF();
//            System.out.println("Server: " + str);
//        } catch (IOException e) {}
            
//        dout.writeUTF("Hola");
//        dout.flush();
        din.close();
        dout.close();
        s.close();	
    }
    
}
