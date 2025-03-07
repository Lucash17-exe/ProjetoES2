package config.algorithms;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;

public abstract class Encrypt {

    private static SecretKey getSecretKey(String key, String algorithm) {
        byte[] keyBytes = key.getBytes();
        return new SecretKeySpec(keyBytes, algorithm);
    }

    public static String encryptPassword(String password, String secretKey, String algorithm) throws Exception {
        SecretKey key = getSecretKey(secretKey, algorithm);
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(password.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decryptPassword(String encryptedPassword, String secretKey, String algorithm) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        SecretKey key = getSecretKey(secretKey, algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
        return new String(decryptedBytes);
    }
}
