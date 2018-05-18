/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptodes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 *
 * @author user
 */
public class CryptoDES {
    
    public static void encrypt(String key, InputStream is, OutputStream os) throws Throwable {
        encryptOrDecrypt(key, Cipher.ENCRYPT_MODE, is, os);
    }

    public static void decrypt(String key, InputStream is, OutputStream os) throws Throwable {
        encryptOrDecrypt(key, Cipher.DECRYPT_MODE, is, os);
    }

    public static void encryptOrDecrypt(String key, int mode, InputStream is, OutputStream os) throws Throwable {

        DESKeySpec dks = new DESKeySpec(key.getBytes());
        SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
        SecretKey desKey = skf.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DES"); // DES/ECB/PKCS5Padding for SunJCE

        if (mode == Cipher.ENCRYPT_MODE) {
                cipher.init(Cipher.ENCRYPT_MODE, desKey);
                CipherInputStream cis = new CipherInputStream(is, cipher);
                doCopy(cis, os);
        } else if (mode == Cipher.DECRYPT_MODE) {
                cipher.init(Cipher.DECRYPT_MODE, desKey);
                CipherOutputStream cos = new CipherOutputStream(os, cipher);
                doCopy(is, cos);
        }
    }

    public static void doCopy(InputStream is, OutputStream os) throws IOException {
        byte[] bytes = new byte[64];
        int numBytes;
        while ((numBytes = is.read(bytes)) != -1) {
                os.write(bytes, 0, numBytes);
        }
        os.flush();
        os.close();
//        is.close();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        String key;
        
        try {
            System.out.println("Enter Key (16, 24 or 32 char only): ");
            key = in.nextLine(); // needs to be at least 8 characters for DES
            // String key = "squirrel123"; 

            // Encrypt text file
            FileInputStream fis = new FileInputStream("clear.txt");
            FileOutputStream fos = new FileOutputStream("clear_enc.txt");
            encrypt(key, fis, fos);
            fis.close();
            
            // Read encrypted file and print as hex from
            File file = new File("clear_enc.txt");
            FileInputStream fis2 = new FileInputStream("clear_enc.txt");
            byte[] bytes = new byte[(int) file.length()];
            fis2.read(bytes);
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02X ", b));
            }
            System.out.println("Encrypted Text (From Bytes To Hex):");
            System.out.println(sb.toString());
            
            // Decrypt text file
            FileInputStream fis3 = new FileInputStream("clear_enc.txt");
            FileOutputStream fos2 = new FileOutputStream("clear_dec.txt");
            decrypt(key, fis3, fos2);
            
            fis2.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        
        System.out.println("Test done!");
    }
         
}

