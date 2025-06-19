import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
// import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DES {
    public static void main(String[] args) throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, javax.crypto.IllegalBlockSizeException, javax.crypto.BadPaddingException, NoSuchPaddingException, InvalidAlgorithmParameterException {
        String key = "12345678";
        DESKeySpec key_spec = new DESKeySpec(key.getBytes());
        SecretKey sec_key = SecretKeyFactory.getInstance("DES").generateSecret(key_spec);

        // Alternative:
        // KeyGenerator key_gen = KeyGenerator.getInstance("DES");
        // SecretKey sec_key = key_gen.generateKey();

        byte[] key_byte = sec_key.getEncoded();
        String str_key = new String(key_byte);

        System.out.println("Secret Key = " + str_key);
        System.out.println("Secret Key Size = " + str_key.length());

        Cipher des;
        des = Cipher.getInstance("DES/CBC/NoPadding");   // to match the block size, to prevent chosen plaintext attack

        String iv_str = "AAAAAAAA";
        IvParameterSpec iv = new IvParameterSpec(iv_str.getBytes());

        des.init(Cipher.ENCRYPT_MODE, sec_key, iv);

        String msg = "DearDearDearDear";
        byte[] byte_msg = msg.getBytes();
        System.out.println("Plain Text = " + msg);

        // Encryption
        byte[] enc_text = des.doFinal(byte_msg);
        System.out.println("Encrypted Text = " + new String(enc_text));

        String encoded_enc_text = Base64.getEncoder().encodeToString(enc_text);
        System.out.println("Encrypted Encoded Text = " + encoded_enc_text);

        // Decryption
        des.init(Cipher.DECRYPT_MODE, sec_key, iv);
        byte[] dec_text = des.doFinal(enc_text);
        System.out.println("Decrypted Text = " + new String(dec_text));


        // ===============================
        // AES
        
    }
}
