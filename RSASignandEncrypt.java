import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSASignandEncrypt {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        KeyPairGenerator key_gen = KeyPairGenerator.getInstance("RSA");
        key_gen.initialize(2048);

        KeyPair alice_key_pair = key_gen.generateKeyPair();

        PrivateKey alice_pvt_key = alice_key_pair.getPrivate();
        PublicKey alice_pub_key = alice_key_pair.getPublic();

        KeyPair bob_key_pair = key_gen.generateKeyPair();

        PrivateKey bob_pvt_key = bob_key_pair.getPrivate();
        PublicKey bob_pub_key = bob_key_pair.getPublic();

        // System.out.println("Private Key: " + new String(pvt_key.getEncoded()));
        // System.out.println("Public Key: " + new String(pub_key.getEncoded()));


        String message = "Sample secret message";
        System.out.println("Plain Text: " + message);
        
        Cipher cipher = Cipher.getInstance("RSA");
        byte[] byte_msg = message.getBytes(StandardCharsets.UTF_8);
        
        cipher.init(Cipher.ENCRYPT_MODE, alice_pvt_key);
        byte[] signed_text = cipher.doFinal(byte_msg);

        cipher.init(Cipher.ENCRYPT_MODE, bob_pub_key);
        byte[] cipher_text = cipher.doFinal(signed_text);


        System.out.println("Cipher Text: " + new String(cipher_text));
        String encoded_msg = Base64.getEncoder().encodeToString(cipher_text);

        System.out.println("Cipher Text: " + encoded_msg);

        // Transmit
        // Receiver side
        cipher.init(Cipher.DECRYPT_MODE, );

        byte[] decrypted_text = cipher.doFinal(cipher_text);
        String plain_text = new String(decrypted_text, StandardCharsets.UTF_8);
        
        System.out.println("Decrypted Text (Plain Text): " + plain_text);


        //

        //Signature publicSignature = Signature.getInstance("SHA256withRSA");
        
    }
}
