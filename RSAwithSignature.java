import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSAwithSignature {
    public static void main(String[] args) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        // ---------Alice sends a message to Bob!------------

        // Generate Key-pairs for Alice and Bob
        KeyPairGenerator key_gen = KeyPairGenerator.getInstance("RSA");
        key_gen.initialize(2048);

        KeyPair alice_key_pair = key_gen.generateKeyPair();
        PrivateKey alice_pvt_key = alice_key_pair.getPrivate();
        PublicKey alice_pub_key = alice_key_pair.getPublic();

        KeyPair bob_key_pair = key_gen.generateKeyPair();
        PrivateKey bob_pvt_key = bob_key_pair.getPrivate();
        PublicKey bob_pub_key = bob_key_pair.getPublic();

        // ===================Alice=================
        String message = "Hey Bob! It's me, Alice!!!";
        byte[] message_bytes = message.getBytes(StandardCharsets.UTF_8);
        System.out.println();
        System.out.println("Original Message: " + message);
        System.out.println();

        // Alice signs the message
        Signature signer = Signature.getInstance("SHA256withRSA");
        signer.initSign(alice_pvt_key);
        signer.update(message_bytes);
        byte[] signature = signer.sign();
        String encoded_signature = Base64.getEncoder().encodeToString(signature);
        System.out.println("===================Alice=================");
        System.out.println("Signature: " + encoded_signature);
        System.out.println();

        // Alice encrypts the message with Bob's public key
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, bob_pub_key);
        byte[] encrypted_msg = cipher.doFinal(message_bytes);
        String encoded_cipher = Base64.getEncoder().encodeToString(encrypted_msg);
        System.out.println("Encrypted Message: " + encoded_cipher);
        System.out.println();

        // <<<<<<<<<<<<<<<Transmit>>>>>>>>>>>>>>>>>>>
        // Receiver's side
        // =================Bob=====================
        // Bob decrypts the message with his private key
        cipher.init(Cipher.DECRYPT_MODE, bob_pvt_key);
        byte[] decrypted_msg = cipher.doFinal(encrypted_msg);

        // Bob verifits signature using Alice's public key
        Signature verifier = Signature.getInstance("SHA256withRSA");
        verifier.initVerify(alice_pub_key);
        verifier.update(decrypted_msg);
        boolean isVerified = verifier.verify(signature);

        System.out.println("=================Bob=====================");
        System.out.println("Decrypted Message: " + new String(decrypted_msg, StandardCharsets.UTF_8));
        System.out.println();
        System.out.println("Signature Valid: " + isVerified);
        System.out.println();
    }
}
