/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fdchatc;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author user
 */
public class FDChatC {
    
    public static void main(String[] args) throws IOException {
        BufferedReader in, stdin;
        Socket s = new Socket("localhost", 7775);
        PrintWriter out = new PrintWriter(s.getOutputStream(), true); 
//        String msg = "";
        in = new BufferedReader(new InputStreamReader(s.getInputStream()));
//        stdin = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter your msg to Server: ");
//        try {
//            while((msg = stdin.readLine()) != null) {
//                out.println(msg);
//                out.flush();
//                
////                System.out.print("Enter your msg to Server: \n");
//                System.out.println(in.readLine());
//                
////                stdin = new BufferedReader(new InputStreamReader(System.in));
//            }
//        } catch (IOException ex) {}
        
        Thread t1 = new Thread(new Runnable() {
            String msg = "";
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            public void run() {
                try {
                    while((msg = stdin.readLine()) != null) {
                        out.println(msg);
                        out.flush();
                        Thread.sleep(500);
                        //                System.out.print("Enter your msg to Server: \n");
//                        System.out.println(in.readLine());
                    }
                } catch (Exception ex) {}
            }
        });
        t1.start();
        
        Thread t2 = new Thread(new Runnable() {
            String msg = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            public void run() {
                try {
                    while((msg = in.readLine()) != null) {
                        System.out.println(msg);
//                                pw.println("Server: " + msg);
//                                pw.flush();
                        Thread.sleep(500);
                    }
                } catch (Exception ex) {}
            }
        });
        t2.start();
        
        while(true) {
            try {
                t1.join();
                t2.join();
            } catch (Exception e) {}
        }
        
    }
    
}
