cjhia ani/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steraming;

import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;

/**
 *
 * @author user
 */
public class Steraming {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream("a.txt");
            out = new FileOutputStream("b.txt");
            int c;
            while ((c=in.read()) != -1) {
                out.write(c);
                System.out.println(c);
            }
        } finally {
            if(in != null) {
                in.close();
            }
            
            if(out != null) {
                out.close();
            }
        }
    }
    
}
