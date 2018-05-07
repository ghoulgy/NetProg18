/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author user
 */
public class Test1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
//        FileInputStream in = new FileInputStream("test.txt");
//        BufferedInputStream buffIn = null;
//        InputStreamReader cin = null;

        try {
            f1 = new FileOutputStream("a.txt");
            f2 = new FileOutputStream("b.txt");
        } catch(IOException err) {
            err.printStackTrace();
        } finally {
            
        }
// OUTPUT FILE
//        try{    
//             FileOutputStream fout=new FileOutputStream("testout.txt");    
//             String s="Welcome to javaTpoint.";    
//             byte b[]=s.getBytes();//converting string into byte array    
//             fout.write(b);    
//             fout.close();    
//             System.out.println("success...");    
//            }catch(Exception e){System.out.println(e);}  

// READ FILE
//        FileReader fr = new FileReader("test.txt");
//        BufferedReader br = new BufferedReader(fr);
//        String buffer;
//        String text = "";
//        
//        while((buffer = br.readLine()) != null) {
//            System.out.println(buffer);
//            text += buffer;
//        }
//        br.close();
//        fr.close();

//        
//        try {
//            cin = new InputStreamReader(System.in);
//            char c;
//            do {
//                c = (char)cin.read();
//                System.out.println(c);
//            
//            buffIn = new BufferedInputStream(in);
//            int ch;
//
//            while((ch=buffIn.read()) != -1) {
//                System.out.println(ch);
//            }    
//        } finally {
//            in.close();    
//        }
    }
    
}