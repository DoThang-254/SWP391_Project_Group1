import java.security.SecureRandom;
import java.util.Base64;

public class TokenGenerator {
    public static String generateResetToken() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[6]; // Token dài 24 byte
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
    
    public static void main(String[] args) {
        String token = generateResetToken();
        System.out.println("Token đặt lại mật khẩu: " + token);
    }
}
