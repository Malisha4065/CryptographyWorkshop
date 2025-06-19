import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class TDES {
    public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException{
        String custom_key = "uor@8001uor@8001uor@8001";

        DESedeKeySpec key_spec = new DESedeKeySpec(custom_key.getBytes());
        SecretKeyFactory key_fac = SecretKeyFactory.getInstance("DESede");
        SecretKey key = key_fac.generateSecret(key_spec);

        byte[] key_byte = key.getEncoded();
        String key_str = new String(key_byte);

        System.out.println("DES key: " + key_str);
        System.out.println("DES key length: " + key_str.length());

        // Sender Side
        //IvParameterSpec iv = new IvParameterSpec(new byte[8]);

        Cipher des;

        des = Cipher.getInstance("DESede/CBC/NoPadding");

        String iv_str = "aaaaaaaaaaaaaaaaaa";
        IvParameterSpec iv = new IvParameterSpec(iv_str.getBytes());



        
        
    }
}
