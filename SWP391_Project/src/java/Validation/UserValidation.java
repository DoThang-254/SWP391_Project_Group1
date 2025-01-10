/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validation;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.regex.Pattern;

/**
 *
 * @author thang
 */
public class UserValidation {
   public String checkRole(String Role){
       if(Role.equals("customer")){
           return "customer";
       }
       if(Role.equals("staff")){
           return "staff";
       }
       return "admin";
   }
   
   public boolean checkMatching(String password , String cPassword){
       if(password.equals(cPassword)){
           return true;
       }
       return false;
   }
   
     public String encode(String mk) {
        String salt = "sa@gjielskdjjjiela;s";
        String rs = null;
        mk += salt;
        try {
            
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] hash = salt.getBytes("UTF-8");
            rs = Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
        }
        return rs; 

    }
     
     public boolean checkHashOfPassword(String password){
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
}
