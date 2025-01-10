import java.security.MessageDigest;
import java.util.Base64;

public class NewClass {
    public static String encode(String mk) {
        String salt = "sa@gjielskdjjjiela;s"; // Chuỗi salt cố định (nên dùng salt ngẫu nhiên cho mỗi mật khẩu)
        String result = null;
        try {
            mk += salt;  // Kết hợp mật khẩu với salt
            MessageDigest md = MessageDigest.getInstance("SHA-1"); // Tạo instance của SHA-1
            byte[] hash = md.digest(mk.getBytes("UTF-8")); // Băm dữ liệu với SHA-1
            result = Base64.getEncoder().encodeToString(hash); // Mã hóa kết quả sang Base64
        } catch (Exception e) {
            e.printStackTrace(); // In lỗi để tiện debug
        }
        return result; // Trả về chuỗi mật khẩu đã mã hóa
    }
    
    public static void main(String[] args) {
        String password = "mypassword123";
        String encodedPassword = encode(password);
        System.out.println("Mật khẩu đã mã hóa: " + encodedPassword);
    }
}
