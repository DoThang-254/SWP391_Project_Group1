/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Model.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thang
 */
public class CustomerDao extends DBContext {

    public List<Product> ReadAllProductByCustomerId(int CustomerId) {
        List<Product> list = new ArrayList<>();
        String sql = "select * from  Product p join Customer c on c.CustomerId = p.CustomerId where c.CustomerId = ? ";
        try {
            PreparedStatement p = connection.prepareStatement(sql);
            p.setInt(1, CustomerId);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getString(1), rs.getString(2), rs.getDate(3),
                        rs.getFloat(4), rs.getString(5), rs.getDate(6), rs.getInt(7)));

            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> SearchingWarrantyInformation(int CustomerId, String ProductId) {
        List<Product> list = new ArrayList<>();
        String sql = "select * from  Product p join Customer c on c.CustomerId = p.CustomerId where c.CustomerId = ? and p.ProductId = ? ";
        try {
            PreparedStatement p = connection.prepareStatement(sql);
            p.setInt(1, CustomerId);
            p.setString(2, ProductId);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getString(1), rs.getString(2), rs.getDate(3),
                        rs.getFloat(4), rs.getString(5), rs.getDate(6), rs.getInt(7)));

            }
        } catch (Exception e) {
        }
        return list;
    }
}
