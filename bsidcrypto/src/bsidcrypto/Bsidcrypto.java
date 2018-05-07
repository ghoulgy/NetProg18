/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsidcrypto;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
/**
 *
 * @author user
 */
public class Bsidcrypto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        String SECRET = "Bi528nDlNBcX9BcCC+ZqGQo1Oz01+GOWSmvxRj7jg1g=";

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        byte[] salt = "SampleSalt".getBytes();
        String pin = "7498";
        SecretKeySpec key = new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(new PBEKeySpec(pin.toCharArray(), salt, 1000, 128)).getEncoded(), "AES");
        cipher.init(2,key);
        BASE64Decoder decoder = new BASE64Decoder();
        System.out.println(new String(cipher.doFinal(decoder.decodeBuffer(SECRET)),"UTF-8"));
    }
    
}
