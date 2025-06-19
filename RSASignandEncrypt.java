import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSASignandEncrypt {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        KeyPairGenerator key_gen = KeyPairGenerator.getInstance("RSA");
        key_gen.initialize(2048);

        // Sending from Alice to Bob!
        KeyPair alice_key_pair = key_gen.generateKeyPair();
        PrivateKey alice_pvt_key = alice_key_pair.getPrivate();
        PublicKey alice_pub_key = alice_key_pair.getPublic();

        KeyPair bob_key_pair = key_gen.generateKeyPair();
        PrivateKey bob_pvt_key = bob_key_pair.getPrivate();
        PublicKey bob_pub_key = bob_key_pair.getPublic();

        // ----------------Alice----------------------
        String message = "Sample secret message";
        System.out.println();
        System.out.println("Plain Text: " + message);
        System.out.println();
        
        Cipher cipher = Cipher.getInstance("RSA");
        byte[] byte_msg = message.getBytes(StandardCharsets.UTF_8);
        
        // Alice hashes the message with SHA-256
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        byte[] message_hash = sha256.digest(byte_msg);
        String hash_string = Base64.getEncoder().encodeToString(message_hash);
        System.out.println("=============Alice================");
        System.out.println("SHA256 hash of the message: " + hash_string);
        System.out.println();

        // Alice signs the hash with Alice's private key
        cipher.init(Cipher.ENCRYPT_MODE, alice_pvt_key);
        byte[] signed_hash = cipher.doFinal(message_hash);
        String signed_hash_string = Base64.getEncoder().encodeToString(signed_hash);
        System.out.println("Signed hash: " + signed_hash_string);
        System.out.println();

        // Alice encrypts the message with Bob's public key
        cipher.init(Cipher.ENCRYPT_MODE, bob_pub_key);
        byte[] cipher_text = cipher.doFinal(byte_msg);

        String encoded_msg = Base64.getEncoder().encodeToString(cipher_text);
        System.out.println("Cipher Text: " + encoded_msg);
        System.out.println();

        // <<<<<<<<<<<<<<Transmit>>>>>>>>>>>>>>>>>>>>>>>>
        // Receiver side
        // ------------------Bob--------------------------

        // Bob decrypts the message
        cipher.init(Cipher.DECRYPT_MODE, bob_pvt_key);
        byte[] decrypted_message = cipher.doFinal(cipher_text);
        String dec_plain_text = new String(decrypted_message, StandardCharsets.UTF_8);
        System.out.println("==============Bob=================");
        System.out.println("Decrypted Plain Text: " + dec_plain_text);
        System.out.println();

        // Bob verifies the signature
        byte[] hash_bob = sha256.digest(decrypted_message);
        String hash_bob_string = Base64.getEncoder().encodeToString(hash_bob);
        System.out.println("Hash calculated by Bob: " + hash_bob_string);
        System.out.println();
        
        cipher.init(Cipher.DECRYPT_MODE, alice_pub_key);
        byte[] decrypted_signature = cipher.doFinal(signed_hash);
        String decrypted_signature_string = Base64.getEncoder().encodeToString(decrypted_signature);
        System.out.println("Decrypted signature: " + decrypted_signature_string);
        System.out.println();

        boolean valid = MessageDigest.isEqual(hash_bob, decrypted_signature);
        System.out.println("Signature valid? : " + valid);
        
    }
}
