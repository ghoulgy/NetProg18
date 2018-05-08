/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threatclient2;

import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ThreatClient2 {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        Socket s = new Socket("localhost", 7777);
        Scanner input = new Scanner (System.in);
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        DataInputStream din = new DataInputStream(s.getInputStream());
        boolean close = false;
//        String str = "";
//        String msg = "";
        
        // While Loop
//        do {
//            System.out.print("Please enter your message: ");
//            msg = input.nextLine();
//            dout.writeUTF(msg);
//            dout.flush();
//
//            try {
//                str =(String)din.readUTF();
//                if(str.equals("Exit")){
//                    break;
//                }
//                System.out.println("Server: " + str);
//            } catch (IOException e) {}
//
//        } while(!msg.equals("Exit"));
        // While Loop
        
        // FD Loop
        // Send
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                String msg,str = "";
                try {
                    System.out.println("Please enter your message: ");
                    do {
                        msg = input.nextLine();
                        dout.writeUTF(msg);
                        dout.flush();
                    } while(!msg.equals("Exit"));
                } catch (Exception ex) {}
            }
        });
        t1.start();
        
        // Rcv
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                String str = "";
                try {
                    do {
                        str =(String)din.readUTF();
                        if(str.equals("Exit")){
                            dout.writeUTF("Exit");
                            dout.flush();
                            break;
                        }
                        System.out.println("Server: " + str);
                    } while(!str.equals("Exit"));
                } catch(Exception e) {}
            }
        });
        t2.start();
        
        do {
            t1.join();
            t2.join();
            close = true;
        } while(!close);
        
        //FD Loop
        
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
//        din.close();
//        dout.close();
//        s.close();	
    } 
}

