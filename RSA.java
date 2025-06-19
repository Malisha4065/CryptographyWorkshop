import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSA {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        KeyPairGenerator key_gen = KeyPairGenerator.getInstance("RSA");
        key_gen.initialize(2048);
        KeyPair key_pair = key_gen.generateKeyPair();

        PrivateKey pvt_key = key_pair.getPrivate();
        PublicKey pub_key = key_pair.getPublic();

        System.out.println();
        System.out.println("Private Key: " + Base64.getEncoder().encodeToString(pvt_key.getEncoded()));
        System.out.println();
        System.out.println("Public Key: " + Base64.getEncoder().encodeToString(pub_key.getEncoded()));
        System.out.println();


        String message = "Sample secret message";
        System.out.println("Plain Text: " + message);
        System.out.println();

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pub_key);

        byte[] byte_msg = message.getBytes(StandardCharsets.UTF_8);
        byte[] cipher_text = cipher.doFinal(byte_msg);

        String encoded_msg = Base64.getEncoder().encodeToString(cipher_text);

        System.out.println("Cipher Text: " + encoded_msg);
        System.out.println();

        // Transmit
        // Receiver side
        cipher.init(Cipher.DECRYPT_MODE, pvt_key);

        byte[] decrypted_text = cipher.doFinal(cipher_text);
        String plain_text = new String(decrypted_text, StandardCharsets.UTF_8);
        
        System.out.println("Decrypted Text (Plain Text): " + plain_text);
    }
}
