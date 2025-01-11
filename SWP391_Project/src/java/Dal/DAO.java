/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Model.Role;
import Model.User;
import Validation.UserValidation;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thang
 */
public class DAO extends DBContext {

    private UserValidation uv = new UserValidation();

    //dùng cho login và register
    public User Login(String userName, String passWord, int Role) {
        String sql = "select * from Users u join Roles r on u.RoleID = r.RoleID\n"
                + "	where u.Username = ? and u.PasswordHash = ? and r.RoleID = ?";
        try {
            PreparedStatement p = connection.prepareStatement(sql);
            p.setString(1, userName);
            p.setString(2, passWord);
            p.setInt(3, Role);

            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                User u = new User(rs.getString(2), rs.getString(3), rs.getInt(4));
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
                + "           ,[RoleID])\n"
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

    public void RegisterCustomer(int userId, String fullName, String email, String phone) {
        String sql = "INSERT INTO [dbo].[Customers]\n"
                + "           ([UserID] \n"
                + "           ,[FullName]\n"
                + "           ,[Email]\n"
                + "           ,[Phone])\n"
                + "     VALUES\n"
                + "           (?,?,?,?)";
        try {
            PreparedStatement p = connection.prepareStatement(sql);
            p.setInt(1, userId);
            p.setString(2, fullName);
            p.setString(3, email);
            p.setString(4, phone);
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

    public User getUser(String userName) {
        String sql = "select * from Users where Username = ? ";
        try {
            PreparedStatement p = connection.prepareStatement(sql);
            p.setString(1, userName);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
            }
        } catch (Exception e) {

        }
        return null;
    }

    public List<Role> ReadListRole() {
        List<Role> list = new ArrayList<>();
        String sql = "	select * from Roles";
        try {
            PreparedStatement p = connection.prepareStatement(sql);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                list.add(new Role(rs.getInt(1), rs.getString(2)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    // dùng cho reset password và change password
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
