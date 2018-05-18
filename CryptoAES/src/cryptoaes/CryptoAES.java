/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptoaes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;
import org.bouncycastle.crypto.engines.AESEngine;

import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

/**
 *
 * @author user
 */
public class CryptoAES {
    PaddedBufferedBlockCipher encryptCipher = null;
    PaddedBufferedBlockCipher decryptCipher = null;

    // Buffer used to transport the bytes from one stream to another
    byte[] buf = new byte[16];              //input buffer
    byte[] obuf = new byte[512];            //output buffer
    // The key
    byte[] key = null;
    // The initialization vector needed by the CBC mode
    byte[] IV =  null;

    // The default block size
    public static int blockSize = 16;

    public CryptoAES(String k){
        //default 128, 192 or 256 bit key
        key = k.getBytes();

        //default IV vector with all bytes to 0
        IV = new byte[blockSize];
    }
    public CryptoAES(byte[] keyBytes){
        //get the key
        key = new byte[keyBytes.length];
        System.arraycopy(keyBytes, 0 , key, 0, keyBytes.length);

        //default IV vector with all bytes to 0
        IV = new byte[blockSize];
    }

    public CryptoAES(byte[] keyBytes, byte[] iv){
        //get the key
        key = new byte[keyBytes.length];
        System.arraycopy(keyBytes, 0 , key, 0, keyBytes.length);

        //get the IV
        IV = new byte[blockSize];
        System.arraycopy(iv, 0 , IV, 0, iv.length);
    }

    public void InitCiphers(){
        //create the ciphers
        // AES block cipher in CBC mode with padding
        encryptCipher = new PaddedBufferedBlockCipher(
                new CBCBlockCipher(new AESEngine()));

        decryptCipher =  new PaddedBufferedBlockCipher(
                new CBCBlockCipher(new AESEngine()));

        //create the IV parameter
        ParametersWithIV parameterIV =
                new ParametersWithIV(new KeyParameter(key),IV);

        encryptCipher.init(true, parameterIV);
        decryptCipher.init(false, parameterIV);
    }

    public void ResetCiphers() {
        if(encryptCipher!=null)
            encryptCipher.reset();
        if(decryptCipher!=null)
            decryptCipher.reset();
    }

    public void CBCEncrypt(InputStream in, OutputStream out) throws Exception {
        // Bytes written to out will be encrypted
        // Read in the cleartext bytes from in InputStream and
        //      write them encrypted to out OutputStream

        //optionaly put the IV at the beggining of the cipher file
        //out.write(IV, 0, IV.length);

        int noBytesRead = 0;        //number of bytes read from input
        int noBytesProcessed = 0;   //number of bytes processed

        while ((noBytesRead = in.read(buf)) >= 0) {
            //System.out.println(noBytesRead +" bytes read");

            noBytesProcessed = encryptCipher.processBytes(buf, 0, noBytesRead, obuf, 0);
//            System.out.println(noBytesProcessed +" bytes processed");
            out.write(obuf, 0, noBytesProcessed);
        }

        //System.out.println(noBytesRead +" bytes read");
        noBytesProcessed = encryptCipher.doFinal(obuf, 0);

        //System.out.println(noBytesProcessed +" bytes processed");
        out.write(obuf, 0, noBytesProcessed);

        out.flush();

        in.close();
        out.close();
    }
    
    public void CBCDecrypt(InputStream in, OutputStream out) throws Exception {
        int noBytesRead = 0;        //number of bytes read from input
        int noBytesProcessed = 0;   //number of bytes processed

        while ((noBytesRead = in.read(buf)) >= 0) {
            //System.out.println(noBytesRead +" bytes read");
            noBytesProcessed = decryptCipher.processBytes(buf, 0, noBytesRead, obuf, 0);
            //System.out.println(noBytesProcessed +" bytes processed");
            out.write(obuf, 0, noBytesProcessed);
        }
        //System.out.println(noBytesRead +" bytes read");
        noBytesProcessed = decryptCipher.doFinal(obuf, 0);
        //System.out.println(noBytesProcessed +" bytes processed");
        out.write(obuf, 0, noBytesProcessed);

        out.flush();

        in.close();
        out.close();
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        String key;
        try {
            
            System.out.println("Enter Key (16, 24 or 32 char only): ");
            key = in.nextLine();
            // String key = "SECRET_1SECRET_2";
            FileInputStream fis = new FileInputStream(new File("clear.txt"));
            FileOutputStream fos = new FileOutputStream(new File("encrypt.txt"));
            
            // Initialize BC AES
            CryptoAES bc = new CryptoAES(key);
            bc.InitCiphers();
 
            //encryption
            bc.CBCEncrypt(fis, fos);
 
            fis = new FileInputStream(new File("encrypt.txt"));
            fos = new FileOutputStream(new File("clear_test.txt"));
 
            //decryption
            bc.CBCDecrypt(fis, fos);
            
            // Show the Encrypted Text in Hex form
            File file = new File("encrypt.txt");
            byte[] bytes = new byte[(int) file.length()];
            FileInputStream fis2 = new FileInputStream(new File("encrypt.txt"));
            fis2.read(bytes);
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02X ", b));
            }
            System.out.println("Encrypted Text (From Bytes To Hex):");
            System.out.println(sb.toString());
            
            fis2.close();
 
        } catch (Exception ex) {}
 
        System.out.println("Test done !");
    }
}
