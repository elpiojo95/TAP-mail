package patterns;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class OperationCipher implements BodyEncriptionStrategy{
    private Cipher cipher;
    private java.security.Key aesKey;
    private String key = "IWantToPassTAP12"; // 128 bit key

    public OperationCipher(){
        this.aesKey = new javax.crypto.spec.SecretKeySpec(key.getBytes(), "AES");
        try {
            this.cipher = Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

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

    @Override
    public String decrypt(String bodyEncripted) {
        byte[] encrypted = Base64.getDecoder().decode(bodyEncripted.getBytes());
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
