/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trytry;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Trytry {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scan = new Scanner(System.in);
        System.out.println("How many files for copy");
        int num = scan.nextInt();
        copyfiles[] cpf = new copyfiles[num];
        
        for(int i=0; i < num; i++) {
            System.out.print("Filename " + i+1 + ":");
            String name = scan.next();
            cpf[i] = new copyfiles(name); 
        }
        
        for(int i=0; i < num; i++) {
            cpf[i].start();
        }         
    }
    
}

class copyfiles extends Thread {
    String filename, destname;
    Thread t;
    FileInputStream in = null;
    FileOutputStream out = null;
    
    public copyfiles(String name) {
        this.filename = name;
        this.destname = name + "-duplicated";
    }
    
    public void run(){
        System.out.println();

        try {
            out = new FileOutputStream(destname);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(copyfiles.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println(filename + " to " + destname + " is started.");
        
        try {
            int c;
            while((c = in.read()) != -1) {
                out.write(c);
            }
        } catch (IOException ex) {
            Logger.getLogger(copyfiles.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    Logger.getLogger(copyfiles.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    Logger.getLogger(copyfiles.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        System.out.println("copy is finished");
    }
    
    public void start() {
        System.out.println();
        try {
            in = new FileInputStream(filename);
            t = new Thread (this, filename);
            t.start();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(copyfiles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
