/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Model.Customer;
import Model.Staff;
import Model.TokenForgetPassword;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author thang
 */
public class TokenForgetDao extends DBContext {

    private PreparedStatement p;
    private ResultSet rs;   

    public Staff GetStaffByEmail(String email) {
        String sql = "select StaffId , Username ,Password , Email from Staff where Email = ? ";

        try {
            p = connection.prepareStatement(sql);
            p.setString(1, email);
            rs = p.executeQuery();
            if (rs.next()) {
                return new Staff(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Customer GetCustomerByEmail(String email) {
        String sql = "select * from Customer where Email = ?";

        try {
            p = connection.prepareStatement(sql);
            p.setString(1, email);
            rs = p.executeQuery();
            if (rs.next()) {
                return new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertTokenForget(TokenForgetPassword tokenForget) {
        String sql = "INSERT INTO [dbo].[TokenForgetPassword]\n"
                + "           ([Token]\n"
                + "           ,[ExpiryTime]\n"
                + "           ,[IsUsed]\n"
                + "           ,[CustomerId]\n"
                + "           ,[StaffId])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?)";
        try {
            p = connection.prepareStatement(sql);
            p.setString(1, tokenForget.getToken());
            p.setTimestamp(2, Timestamp.valueOf(tokenForget.getExpiryTime())); // Sửa đúng kiểu dữ liệu
            p.setBoolean(3, tokenForget.isIsUsed());
            p.setInt(4, tokenForget.getCustomerId());
            p.setString(5, tokenForget.getStaffId());
            return p.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean insertStaffTokenForget(TokenForgetPassword tokenForget) {
        String sql = "INSERT INTO [dbo].[TokenForgetPassword]\n"
                + "           ([Token]\n"
                + "           ,[ExpiryTime]\n"
                + "           ,[IsUsed]\n"
                + "           \n"
                + "           ,[StaffId])\n"
                + "     VALUES\n"
                + "           (?,?,?,?)";
        try {
            p = connection.prepareStatement(sql);
            p.setString(1, tokenForget.getToken());
            p.setTimestamp(2, Timestamp.valueOf(tokenForget.getExpiryTime())); // Sửa đúng kiểu dữ liệu
            p.setBoolean(3, tokenForget.isIsUsed());
            p.setString(4, tokenForget.getStaffId());

            return p.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public TokenForgetPassword getTokenPassword(String token) {
        String sql = "select * from tokenForgetPassword where token = ? ";
        try {
            p = connection.prepareStatement(sql);
            p.setString(1, token);
            rs = p.executeQuery();
            while (rs.next()) {
                return new TokenForgetPassword(
                        rs.getInt(1),
                        rs.getInt(5),
                        rs.getBoolean(4),
                        rs.getString(2),
                        rs.getTimestamp(3).toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Customer getEmailById(int customerId) {
        String sql = "select Email from Customer where CustomerId = ? ";
        try {
            p = connection.prepareStatement(sql);
            p.setInt(1, customerId);
            rs = p.executeQuery();
            while (rs.next()) {
                return new Customer(0, null, null, rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Staff getEmailByStaffId(String staffId) {
        String sql = "select Email from Staff where StaffId = ? ";
        try {
            p = connection.prepareStatement(sql);
            p.setString(1, staffId);
            rs = p.executeQuery();
            while (rs.next()) {
                return new Staff(null, null, null, rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void updatePassword(int customerId, String password) {
        String sql = "UPDATE [dbo].[Customer]\n"
                + "   SET \n"
                + "      [Password] = ?\n"
                + "      \n"
                + " WHERE CustomerId = ?";
        try {
            p = connection.prepareStatement(sql);
            p.setString(1, password);
            p.setInt(2, customerId);
            p.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateStaffPassword(String staffId, String password) {
        String sql = "UPDATE [dbo].[Staff]\n"
                + "   SET \n"
                + "		[Password] = ?\n"
                + "     \n"
                + " WHERE StaffId = ?";
        try {
            p = connection.prepareStatement(sql);
            p.setString(1, password);
            p.setString(2, staffId);
            p.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateStatus(TokenForgetPassword token) {
        System.out.println("token = " + token);
        String sql = "UPDATE [dbo].[TokenForgetPassword]\n"
                + "   SET [IsUsed] = ?\n"
                + "     \n"
                + " WHERE Token = ?";
        try {
            PreparedStatement p = connection.prepareStatement(sql);
            p.setBoolean(1, token.isIsUsed());
            p.setString(2, token.getToken());
            p.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
