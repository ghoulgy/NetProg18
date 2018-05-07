/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fdchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class ChatThread extends Thread {
    Socket s;
    BufferedReader br;
    PrintWriter pw;
    BufferedReader stdin;
    String msg;
    
    public ChatThread(Socket s, BufferedReader br, PrintWriter pw) throws IOException {
        this.s = s;
        this.br = br;
        this.pw = pw;    
    }
    
    public void run() {                
        // Send
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                stdin = new BufferedReader(new InputStreamReader(System.in));

                System.out.println("Enter your msg to Server: ");
                try {
                    while((msg = stdin.readLine()) != null) {
                        pw.println("Server: " + msg);
                        pw.flush();
                        Thread.sleep(500);
                    }
                } catch (Exception ex) {}

            }
        });
        t1.start();

        // Rcv
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while((msg = br.readLine()) != null) {
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
/**
 *
 * @author user
 */
public class FDChat {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        ServerSocket ss = new ServerSocket(7775);
        Socket s = ss.accept();
        System.out.println("Got New Client Request!");
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);    
                
        ChatThread ct = new ChatThread(s, in, out);
        Thread t = new Thread(ct);
        t.start();
    }
    
}
