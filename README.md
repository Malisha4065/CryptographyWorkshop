# Cryptography Implementation in Java

This project contains implementations of various cryptographic algorithms and utilities in Java. It demonstrates symmetric encryption (AES, DES, 3DES), asymmetric encryption (RSA), and basic cryptographic utilities.

## Files Overview

### Core Cryptographic Implementations

#### [`AES.java`](AES.java)
Implementation of the Advanced Encryption Standard (AES) symmetric encryption algorithm.
- Uses AES-128 with CBC mode and PKCS5 padding
- Demonstrates key generation, encryption, and decryption
- Includes Base64 encoding for encrypted output
- Uses a 16-byte initialization vector (IV) for CBC mode

#### [`DES.java`](DES.java)
Implementation of the Data Encryption Standard (DES) symmetric encryption algorithm.
- Uses DES with CBC mode and no padding (requires input to match block size)
- Demonstrates key generation from a string
- Shows encryption and decryption processes
- Includes Base64 encoding for encrypted output

#### [`TDES.java`](TDES.java)
Implementation of Triple DES (3DES) encryption algorithm.
- Uses DESede (Triple DES) with CBC mode and PKCS5 padding
- Demonstrates key generation from a custom 24-byte key
- Shows encryption and decryption with proper padding
- Includes Base64 encoding for encrypted output

#### [`RSA.java`](RSA.java)
Implementation of RSA asymmetric encryption algorithm.
- Generates 2048-bit RSA key pairs
- Demonstrates public key encryption and private key decryption
- Shows key pair generation and usage
- Includes Base64 encoding for encrypted messages

#### [`RSASignandEncrypt.java`](RSASignandEncrypt.java)
Advanced RSA implementation combining digital signatures and encryption.
- Generates separate key pairs for Alice and Bob
- Demonstrates the concept of signing with private key and encrypting with public key
- Creates a signed message digest with SHA256 for verification
- Shows a hybrid approach for secure communication

### Utility Classes

#### [`Basic.java`](Basic.java)
Collection of basic cryptographic and mathematical utilities.
- **`isPrime(int x)`**: Checks if a number is prime
- **`isCoPrime(BigInteger x, BigInteger y)`**: Checks if two numbers are coprime
- **`totient(BigInteger x)`**: Calculates Euler's totient function
- **`euclidean()`**: Implements the Euclidean algorithm for finding GCD

## Features

- **Symmetric Encryption**: AES, DES, and 3DES implementations
- **Asymmetric Encryption**: RSA encryption and key pair generation
- **Digital Signatures**: RSA-based signing (in progress)
- **Mathematical Utilities**: Prime checking, GCD calculation, totient function
- **Base64 Encoding**: All encrypted outputs are Base64 encoded for safe transmission
- **Multiple Cipher Modes**: CBC mode with proper initialization vectors

## Usage

To run any of the implementations:

```bash
javac <filename>.java
java <classname>
```

For example:
```bash
javac AES.java
java AES
```

## Security Notes

- The implementations use hardcoded keys and IVs for demonstration purposes
- In production environments, use secure random key generation
- The DES algorithm is considered insecure for modern applications
- Always use proper padding and initialization vectors
- RSA key size of 2048 bits is currently considered secure

## Dependencies

This project uses standard Java cryptography libraries:
- `javax.crypto.*` for symmetric encryption
- `java.security.*` for asymmetric encryption and key management
- `java.util.Base64` for encoding
- `java.math.BigInteger` for large number operations

## Educational Purpose

This project is designed for educational purposes to demonstrate:
- Different types of encryption algorithms
- Key generation and management
- Proper use of initialization vectors and padding
- Basic cryptographic mathematical operations