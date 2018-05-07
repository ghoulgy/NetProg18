/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fdchat;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


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
//        System.out.print("Enter your msg to client: ");
//        stdin = new BufferedReader(new InputStreamReader(System.in));
                
                // Send
                Thread t1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        stdin = new BufferedReader(new InputStreamReader(System.in));

                        System.out.print("Enter your msg to Server: ");
                        try {
                            while((msg = stdin.readLine()) != null) {
                                pw.println(msg);
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
