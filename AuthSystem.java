import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.prefs.Preferences;

/**
 * StudyEazy Academic Management System
 * AES-256 Encryption Module (JCE-based implementation)
 *
 * Provides confidentiality for sensitive academic data.
 */

public class AuthSystem
 {

    private static SecretKey secretKey;

    // Stored key reference (simple persistence using Preferences)
    private static final String KEY_PREF = "studyEazyAESKey";

    // KEY INITIALISATION
 
    static 
    {
        try 
        {
            Preferences prefs = Preferences.userNodeForPackage(AuthSystem.class);

            String savedKey = prefs.get(KEY_PREF, null);

            if (savedKey == null) 
            {
                // Generate new key if none exists
                KeyGenerator keyGen = KeyGenerator.getInstance("AES");
                keyGen.init(256, new SecureRandom());

                secretKey = keyGen.generateKey();

                // Save key (Base64)
                String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
                prefs.put(KEY_PREF, encodedKey);

            }
            else 
            {
                // Load existing key
                byte[] decodedKey = Base64.getDecoder().decode(savedKey);
                secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
            }

        } 
        catch (Exception e) 
        {
            System.out.println("Key initialization error: " + e.getMessage());
        }
    }

    // ENCRYPTION

    public static String encrypt(String data)
    {
        try 
        {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] encrypted = cipher.doFinal(data.getBytes());

            return Base64.getEncoder().encodeToString(encrypted);

        } 
        catch (Exception e)
        {
            System.out.println("Encryption error: " + e.getMessage());
        }
        return null;
    }

    // DECRYPTION
    
    public static String decrypt(String encryptedData) 
    {
        try
        {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            byte[] decoded = Base64.getDecoder().decode(encryptedData);

            return new String(cipher.doFinal(decoded));

        } 
        catch (Exception e)
        {
            System.out.println("Decryption error: " + e.getMessage());
        }
        return null;
    }
}