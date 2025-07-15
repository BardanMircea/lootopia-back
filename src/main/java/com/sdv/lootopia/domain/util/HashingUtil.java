package com.sdv.lootopia.domain.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashingUtil {

    public static String sha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encoded = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(encoded);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erreur lors du hachage SHA-256", e);
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b)); // format hexadécimal
        }
        return result.toString();
    }
}
