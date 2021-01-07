package patterns;

/**
 * Class to implement the operation to reverse decorator
 * @author Leandro F. Gomez Racca
 * @author Miriam Gertrudix Pedrola
 */
public class OperationReverse implements BodyEncryptionStrategy {
    /**
     * method to reverse the string
     * @param body body of the message
     * @return body reversed
     */
    public String reverseString(String body){
        StringBuffer bodyReversed = new StringBuffer(body).reverse();
        return bodyReversed.toString();
    }

    /**
     * method to encrypt the body with reversing
     * @param body body of the message
     * @return body of the message reversed
     */
    @Override
    public String encrypt(String body) {
        return reverseString(body);
    }

    /**
     * method to decrypt the body with reversing
     * @param bodyEncrypted body of the message reversed
     * @return body of the message
     */
    @Override
    public String decrypt(String bodyEncrypted) {
        return reverseString(bodyEncrypted);
    }
}
