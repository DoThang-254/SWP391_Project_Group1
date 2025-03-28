/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author thang
 */
public class Validation {

    public boolean checkMatching(String password, String cPassword) {
        if (password.equals(cPassword)) {
            return true;
        }
        return false;
    }

    public String encode(String password) {
        String salt = "sa@gjielskdjjjiela;s";
        String rs = null;

        try {
            password += salt;
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] hash = md.digest(password.getBytes("UTF-8"));
            rs = Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
        }
        return rs;

    }

    public boolean checkHashOfPassword(String password) {
        if (password.length() < 8) {
            return false;
        }

        // Ít nhất 1 ký tự viết hoa
        boolean hasUppercase = Pattern.compile("[A-Z]").matcher(password).find();
        // Ít nhất 1 ký tự viết thường
        boolean hasLowercase = Pattern.compile("[a-z]").matcher(password).find();
        // Ít nhất 1 chữ số
        boolean hasDigit = Pattern.compile("[0-9]").matcher(password).find();
        // Ít nhất 1 ký tự đặc biệt
        boolean hasSpecialChar = Pattern.compile("[@#$%^&+=!~(){}\\[\\]\\-]").matcher(password).find();

        // Kiểm tra tất cả các điều kiện
        return hasUppercase && hasLowercase && hasDigit || hasSpecialChar;
    }

    public boolean isValidEmail(String email) {
        // Regex kiểm tra email hợp lệ
        String emailRegex = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";

        // Biên dịch regex
        Pattern pattern = Pattern.compile(emailRegex);

        // So khớp với email đầu vào
        Matcher matcher = pattern.matcher(email);

        return matcher.matches(); // Trả về true nếu email hợp lệ
    }

    public boolean isValidVietnamesePhoneNumber(String phoneNumber) {
        // Xóa khoảng trắng nếu có
        phoneNumber = phoneNumber.trim();

        // Regex kiểm tra số điện thoại Việt Nam hợp lệ
        String phoneRegex = "^(0[235789])[0-9]{8}$";

        // Biên dịch regex
        Pattern pattern = Pattern.compile(phoneRegex);

        // So khớp với số điện thoại đầu vào
        Matcher matcher = pattern.matcher(phoneNumber);

        return matcher.matches(); // Trả về true nếu hợp lệ
    }
}
