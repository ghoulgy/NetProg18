/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientwhile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class ClientWhile {
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Scanner input = new Scanner (System.in);
        Socket s = new Socket("localhost", 7776);
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        boolean shouldContinue = true;
        
        System.out.print("Please enter your command : ");
        String msg = input.nextLine();
        dout.writeUTF(msg);
        dout.flush();
        
//        DataInputStream dis = new DataInputStream(s.getInputStream());
//        DataInputStream dis = new DataInputStream(System.in);
//        String str =(String)dis.readUTF();
//        System.out.println("Data from server is " + str);
        
//        do {
//            System.out.print("Please enter your command : ");
//            msg = input.nextLine();
//            dout.writeUTF(msg);
//            dout.flush();

//            dis = new DataInputStream(s.getInputStream());
//            str =(String)dis.readUTF();			
//            System.out.println("Data from server is " + str);
            
//            if (str=="x") {
//                System.out.println("Server close connection");	
//                break;
//            }
//
//            if(msg.length()==0) {
//                shouldContinue = true;
//                System.out.println("Client stop communicate");
//            } else {
//                shouldContinue = false;
//            }

//        }while(!str.equals("x"));


        input.close();
        dout.close();
//        dis.close();
        s.close();
    }
    
}
