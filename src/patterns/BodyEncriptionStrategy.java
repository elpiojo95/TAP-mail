package patterns;

public interface BodyEncriptionStrategy {
    String encrypt(String body);
    String decrypt(String bodyEncripted);
}
