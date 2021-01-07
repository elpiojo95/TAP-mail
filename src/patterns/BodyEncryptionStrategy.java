package patterns;

/**
 * Interface for the encryption of the body message
 * @author Leandro F. Gomez Racca
 * @author Miriam Gertrudix Pedrola
 */
public interface BodyEncryptionStrategy {
    String encrypt(String body);
    String decrypt(String bodyEncrypted);
}
