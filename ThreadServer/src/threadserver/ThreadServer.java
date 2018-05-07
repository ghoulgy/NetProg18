/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadserver;
import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextInputDialog;

class ClientHandler extends Thread {
//class ClientHandler implements Runnable {
    Socket ss;
    Scanner input = new Scanner (System.in);
    DataOutputStream dout;
    DataInputStream din;

    public ClientHandler(Socket ss, DataInputStream din, DataOutputStream dout) {
        this.ss = ss;
        this.dout = dout;
        this.din = din;
    }
    
    public void run() {
        String msgRcv = "";
        
        // While Loop
        do {
            try {
                
                msgRcv = (String)din.readUTF();
                System.out.println("Client: " + msgRcv);
                
                if(msgRcv.equals("Exit")) {
                    System.out.println("Close...");
                    break;
                }
                
                System.out.print("Please enter your message: ");
                String msg = input.nextLine();
                dout.writeUTF(msg);
                dout.flush();  
            } catch(Exception e) {}
            
        } while(!msgRcv.equals("Exit"));
        
        try {
            dout.close();
            din.close();
            ss.close();
        } catch(Exception e) {}
        // While Loop
        
//        try {
//            // Rcv
//            msgRcv = din.readUTF();
//            System.out.println("Client: " + msgRcv);
//            
//            // Send
//            System.out.print("Please enter your message: ");
//            String msg = input.nextLine();
//            dout.writeUTF(msg);
////            dout.flush();
//            
//            // Rcv
//            msgRcv = din.readUTF();
//            System.out.println("Client: " + msgRcv);
//            
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            din.close();
//            dout.close();
//            ss.close();
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
    }
}

/**
 *
 * @author user
 */
public class ThreadServer {
    private String type;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        ServerSocket ss = new ServerSocket(7777);
        System.out.println("Waiting for connection ...");
        Socket s;
        
        while(true) {
            s = null;
            try {
                s = ss.accept();

                System.out.println("Got New Client Request!");

                DataOutputStream dout = new DataOutputStream(s.getOutputStream());
                DataInputStream din = new DataInputStream(s.getInputStream());

                ClientHandler ch = new ClientHandler(s, din, dout);

                Thread t = new Thread(ch);
                t.start();
            } catch (Exception e) {
                s.close();
                e.printStackTrace();
            }       
            
        }
                        
    }
    
    
}
