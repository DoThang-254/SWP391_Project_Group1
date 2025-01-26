/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Repository.ILoginDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Model.Customer;

/**
 *
 * @author thang
 */
public class Dao extends DBContext implements ILoginDAO {

    @Override
    public Customer Login(String Username, String password) {
        String sql = "select * from Customer where Username = ? and Password = ? ";
        try {
            PreparedStatement p = connection.prepareStatement(sql);
            p.setString(1, Username);
            p.setString(2, password);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                return new Customer(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8), rs.getString(9), rs.getString(10));
            }
        } catch (Exception e) {
        }
        return null;
    }

}
