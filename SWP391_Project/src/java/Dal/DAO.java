/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Model.User;
import Validation.UserValidation;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author thang
 */
public class DAO extends DBContext {
    private UserValidation uv = new UserValidation();

    public User Login(String userName, String passWord, String Role) {
        String sql = "SELECT *\n"
                + "  FROM [dbo].[Users]\n"
                + "  where Username = ? and PasswordHash = ? and [Role] = ?";
        try {
            PreparedStatement p = connection.prepareStatement(sql);
            p.setString(1, userName);
            p.setString(2, passWord);
            p.setString(3, Role);

            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                User u = new User(rs.getString(2), rs.getString(3), rs.getString(4));
                return u;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void Register(String userName, String passWord, String Role) {
        String sql = "INSERT INTO [dbo].[Users]\n"
                + "           ([Username]\n"
                + "           ,[PasswordHash]\n"
                + "           ,[Role])\n"
                + "     VALUES\n"
                + "           (?,?,?)";
        try {
            PreparedStatement p = connection.prepareStatement(sql);
            p.setString(1, userName);
            p.setString(2, passWord);
            p.setString(3, Role);
            p.executeUpdate();
        } catch (Exception e) {
        }
    }

    public boolean checkAccountExisted(String userName) {
        String sql = "select * from Users where Username = ? ";
        try {
            PreparedStatement p = connection.prepareStatement(sql);
            p.setString(1, userName);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }

    public void UpdatePassword(String password, String username) {
        String sql = "UPDATE [dbo].[Users]\n"
                + "   SET\n"
                + "      [PasswordHash] = ?\n"
                + "      \n"
                + " WHERE Username = ?";
        
        try {
            
            PreparedStatement p = connection.prepareStatement(sql);
            p.setString(1, uv.encode(password));
            p.setString(2, username);
            p.executeUpdate();
        } catch (Exception e) {
        }
    }
}
