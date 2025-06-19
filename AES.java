import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        byte[] key_array = new byte[] {'T', 'h', 'e', 'B', 'e', 's', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y'};
        SecretKeySpec sec_key = new SecretKeySpec(key_array, "AES");

        System.out.println("Key Phrase: " + new String(sec_key.getEncoded()));
        
        Cipher aes = Cipher.getInstance("AES/CBC/PKCS5Padding");

        // AES block size is 16 bytes, so IV must be 16 bytes
        String aes_iv_str = "AAAAAAAAAAAAAAAA"; // 16 bytes
        IvParameterSpec aes_iv = new IvParameterSpec(aes_iv_str.getBytes());
        
        // AES Encryption
        aes.init(Cipher.ENCRYPT_MODE, sec_key, aes_iv);
        
        String aes_msg = "Hello AES World!"; // Can be any length with PKCS5Padding
        byte[] aes_byte_msg = aes_msg.getBytes();
        System.out.println("AES Plain Text = " + aes_msg);
        
        byte[] aes_enc_text = aes.doFinal(aes_byte_msg);
        System.out.println("AES Encrypted Text = " + new String(aes_enc_text));
        
        String aes_encoded_enc_text = Base64.getEncoder().encodeToString(aes_enc_text);
        System.out.println("AES Encrypted Encoded Text = " + aes_encoded_enc_text);
        
        // AES Decryption
        aes.init(Cipher.DECRYPT_MODE, sec_key, aes_iv);
        byte[] aes_dec_text = aes.doFinal(aes_enc_text);
        System.out.println("AES Decrypted Text = " + new String(aes_dec_text));
    }
}