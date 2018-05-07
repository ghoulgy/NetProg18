/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fdchatc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author user
 */
public class FDChatC {
    
    public static void main(String[] args) throws IOException {
        BufferedReader in, stdin;
        Socket s = new Socket("localhost", 7775);
        PrintWriter out = new PrintWriter(s.getOutputStream(), true); 

        System.out.println("Enter your msg to Server: ");
        
        // Send
        Thread t1 = new Thread(new Runnable() {
            String msg = "";
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            public void run() {
                try {
                    while((msg = stdin.readLine()) != null) {
                        out.println("Client: " + msg);
                        out.flush();
                        Thread.sleep(500);
                    }
                } catch (Exception ex) {}
            }
        });
        t1.start();
        
        // Rcv
        Thread t2 = new Thread(new Runnable() {
            String msg = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            public void run() {
                try {
                    while((msg = in.readLine()) != null) {
                        System.out.println(msg);
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
