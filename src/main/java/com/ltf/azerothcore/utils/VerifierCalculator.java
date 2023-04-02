package com.ltf.azerothcore.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 用于计算account表 verifier字段的工具类
 */
public class VerifierCalculator {

    private static final BigInteger G = BigInteger.valueOf(7);
    private static final BigInteger N = new BigInteger("894B645E89E1535BBDAD5B8B290650530801B18EBFBF5E8FAB3C82872A3E9BB7", 16);

    public static byte[] calculateVerifier(String username, String password,byte[] salt) throws NoSuchAlgorithmException {
        // Convert username and password to uppercase
        username = username.toUpperCase();
        password = password.toUpperCase();

        // Calculate h1 = SHA1("USERNAME:PASSWORD")
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update((username + ":" + password).getBytes());
        byte[] h1 = md.digest();

        // Calculate h2 = SHA1(salt || h1)
        md.reset();
        byte[] h2Input = concatByteArrays(salt, h1);
        md.update(h2Input);
        byte[] h2 = md.digest();

        // Treat h2 as an integer in little-endian order
        BigInteger h2Int = new BigInteger(1, reverseByteArray(h2));

        // Calculate (g ^ h2) % N
        BigInteger verifierInt = G.modPow(h2Int, N);

        // Convert the result back to a byte array in little-endian order
        byte[] verifier = verifierInt.toByteArray();
        if (verifier.length < 32) {
            byte[] paddedVerifier = new byte[32];
            System.arraycopy(verifier, 0, paddedVerifier, 32 - verifier.length, verifier.length);
            verifier = paddedVerifier;
        } else if (verifier.length > 32) {
            byte[] truncatedVerifier = new byte[32];
            System.arraycopy(verifier, verifier.length - 32, truncatedVerifier, 0, 32);
            verifier = truncatedVerifier;
        }
        return reverseByteArray(verifier);
    }

    private static byte[] concatByteArrays(byte[] a, byte[] b) {
        byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    private static byte[] reverseByteArray(byte[] a) {
        byte[] reversed = new byte[a.length];
        for (int i = 0; i < a.length; i++) {
            reversed[i] = a[a.length - 1 - i];
        }
        return reversed;
    }

}
