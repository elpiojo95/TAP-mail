package patterns;


public class OperationReverse implements BodyEncriptionStrategy {
    @Override
    public String encrypt(String body) {
        return reverseString(body);
    }

    @Override
    public String decrypt(String bodyEncripted) {
        return reverseString(bodyEncripted);
    }

    public String reverseString(String body){
        StringBuffer bodyReversed = new StringBuffer(body).reverse();
        return bodyReversed.toString();
    }
}
