package patterns;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * class to implement the operations to Cipher decorator
 * @author Leandro F. Gomez Racca
 * @author Miriam Gertrudix Pedrola
 */
public class OperationCipher implements BodyEncryptionStrategy {
    private Cipher cipher;
    private java.security.Key aesKey;

    /**
     * method to cipher the body of the message
     */
    public OperationCipher(){
        // 128 bit key
        String key = "IWantToPassTAP12";
        this.aesKey = new javax.crypto.spec.SecretKeySpec(key.getBytes(), "AES");
        try {
            this.cipher = Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    /**
     * method to encrypt the body with cipher
     * @param body body of the message
     * @return body ciphered
     */
    @Override
    public String encrypt(String body) {
        byte[] encrypted = new byte[0];
        try {
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            encrypted = cipher.doFinal(body.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(encrypted);
    }

    /**
     * method to decrypt the body with cipher
     * @param bodyEncrypted body of the message ciphered
     * @return body of the message deciphered
     */
    @Override
    public String decrypt(String bodyEncrypted) {
        byte[] encrypted = Base64.getDecoder().decode(bodyEncrypted.getBytes());
        String decrypted = null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            decrypted = new String(cipher.doFinal(encrypted));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decrypted;

    }
}
