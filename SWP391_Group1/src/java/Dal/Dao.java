/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Model.Customer;
import Model.Role;
import Model.Staff;

/**
 *
 * @author thang
 */
public class Dao extends DBContext {

    private PreparedStatement p;
    private ResultSet rs;

    public static void main(String[] args) {
        Dao d = new Dao();
        
        System.out.println(d.StaffLoginByEmail("thangmoneo2542004@gmail.com").getUsername());
    }

    public Customer LoginByEmail(String email) {
        String sql = "select * from Customer where email = ?";
        try {
            p = connection.prepareStatement(sql);
            p.setString(1, email);
            rs = p.executeQuery();
            if (rs.next()) {
                return new Customer(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getDate(9), rs.getString(10), rs.getString(11));
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public Staff StaffLoginByEmail(String email) {
        String sql = "select s.* , r.RoleName from Staff s join Role r on s.RoleId = r.RoleId where s.email = ?";
        try {
            p = connection.prepareStatement(sql);
            p.setString(1, email);
            rs = p.executeQuery();
            if (rs.next()) {
                Role r = new Role(rs.getInt(13), rs.getString(14));
                return new Staff(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getDate(9), rs.getString(11), rs.getInt(13), r);
            }
        } catch (Exception e) {
        }
        return null;
    }
    public Customer Login(String Username, String password) {
        String sql = "select * from Customer where Username = ? and Password = ?";
        try {
            p = connection.prepareStatement(sql);
            p.setString(1, Username);
            p.setString(2, password);
            rs = p.executeQuery();
            if (rs.next()) {
                return new Customer(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getDate(9), rs.getString(10), rs.getString(11));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Staff StaffLogin(String Username, String password) {
        String sql = "select s.* , r.RoleName from Staff s join Role r on s.RoleId = r.RoleId where s.Username = ? and s.Password = ?";
        try {
            p = connection.prepareStatement(sql);
            p.setString(1, Username);
            p.setString(2, password);
            rs = p.executeQuery();
            if (rs.next()) {
                Role r = new Role(rs.getInt(11), rs.getString(12));
                return new Staff(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getDate(9), rs.getString(10), rs.getInt(11), r);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public boolean checkAccountExisted(String userName) {
        String sql = "select * from Customer where Username = ? ";
        try {
            p = connection.prepareStatement(sql);
            p.setString(1, userName);
            rs = p.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }

    public boolean checkPhoneExisted(String phone) {
        String sql = "select * from Customer where Phone = ?  ";
        try {
            p = connection.prepareStatement(sql);
            p.setString(1, phone);
            rs = p.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }

    public boolean checkEmailExisted(String email) {
        String sql = "select * from Customer where Email = ?  ";
        try {
            p = connection.prepareStatement(sql);
            p.setString(1, email);
            rs = p.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }

    public boolean checkPhoneExistedInStaff(String phone) {
        String sql = "select phone from Staff where Phone = ? ";
        try {
            p = connection.prepareStatement(sql);
            p.setString(1, phone);
            rs = p.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }

    public boolean checkEmailExistedInStaff(String email) {
        String sql = "select Email from Staff where Email = ?  ";
        try {
            p = connection.prepareStatement(sql);
            p.setString(1, email);
            rs = p.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }

    public void RegisterCustomer(Customer c) {
        String sql = "INSERT INTO [dbo].[Customer]\n"
                + "           ([Username]\n"
                + "           ,[Password]\n"
                + "           ,[FirstName]\n"
                + "           ,[LastName]\n"
                + "           ,[Phone]\n"
                + "           ,[Email]\n"
                + "           ,[Gender]\n"
                + "           ,[BirthDate]\n"
                + "           ,[Status]\n"
                + "           ,[Address])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?,?)";
        try {
            p = connection.prepareStatement(sql);
            p.setString(1, c.getUsername());
            p.setString(2, c.getPassword());
            p.setString(3, c.getFirstName());
            p.setString(4, c.getLastName());
            p.setString(5, c.getPhone());
            p.setString(6, c.getEmail());
            p.setString(7, c.getGender());
            p.setDate(8, new java.sql.Date(c.getBirthDate().getTime()));
            p.setString(9, c.getStatus());
            p.setString(10, c.getAddress());

            p.executeUpdate();
        } catch (Exception e) {
        }
    }

}
