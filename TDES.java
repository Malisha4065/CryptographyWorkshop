import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class TDES {
    public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
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

        des = Cipher.getInstance("DESede/CBC/PKCS5Padding");    // Add padding

        String iv_str = "aaaaaaaa";
        IvParameterSpec iv = new IvParameterSpec(iv_str.getBytes());

        des.init(Cipher.ENCRYPT_MODE, key, iv);

        String msg = "ThisIsAMessage";
        byte[] byte_msg = msg.getBytes();
        System.out.println("Plain Text = " + msg);

        // Encryption
        byte[] enc_text = des.doFinal(byte_msg);
        
        String encoded_enc_text = Base64.getEncoder().encodeToString(enc_text);
        System.out.println("Encrypted Encoded Text = " + encoded_enc_text);

        // Decryption
        des.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] dec_text = des.doFinal(enc_text);
        System.out.println("Decrypted Text = " + new String(dec_text));
    }
}
