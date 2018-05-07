/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpylines;

import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.PrintWriter;


/**
 *
 * @author user
 */
public class Cpylines {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        BufferedReader inputStream = null;
        PrintWriter outputStream = null;
        
        try {
            inputStream = new BufferedReader(new FileReader("a.txt"));
            outputStream = new PrintWriter(new FileWriter("b.txt"));
            String l;
            while((l = inputStream.readLine()) != null) {
                outputStream.println(l);
                System.out.println(l);
            }
        } finally {
            inputStream.close();
            outputStream.close();
        }
    }
    
}
