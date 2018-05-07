/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverwhile;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class ServerWhile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        ServerSocket ss = new ServerSocket(7776);
        Scanner input = new Scanner(System.in);
        System.out.println("Server is waiting the client");
        Socket s = ss.accept();
        System.out.println("Server is connected");
        DataInputStream dis = new DataInputStream(s.getInputStream());
        String str = (String)dis.readUTF();
        System.out.println("Client: " + str);
        boolean shouldCont = true;
        
//        System.out.print("Please enter message to client : ");
//        String str2 = input.nextLine();
//        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
//        dout.writeUTF(str2);
//        dout.flush();
//        System.out.println("Server-2 is connected");

//        while(!str.equals("x")) {
        try {
            dis = new DataInputStream(s.getInputStream());
            str= (String)dis.readUTF();
            System.out.println("Client : " + str);
        } catch (Exception e) {
    
        }
//            System.out.print("Please enter message to client : ");
//            str2 = input.nextLine();
//            dout = new DataOutputStream(s.getOutputStream());
//            dout.writeUTF(str2);
//            dout.flush();

//            if(str.equals("x")) {
//                shouldCont = true;
//                System.out.println("Server Connection close");
//                break;
//            } else {
//                shouldCont = false;
//            }

//        }
        
        System.out.println("Server Connection close");
        input.close();
//        dout.close();
        dis.close();
        s.close();
    }
    
}
