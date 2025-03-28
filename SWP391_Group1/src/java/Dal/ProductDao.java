/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Model.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thang
 */
public class ProductDao extends DBContext {

    private PreparedStatement p;
    private ResultSet rs;
    public static void main(String[] args) {
        ProductDao p = new ProductDao();
        for (String a : p.GetBrandById(1)) {
            System.out.println(a);
        }
    }
    public Product GetProductById(String productId) {
        String sql = "select * from Product where ProductId = ? ";

        try {
            p = connection.prepareStatement(sql);

            p.setString(1, productId);
            rs = p.executeQuery();

            if (rs.next()) {
                return new Product(rs.getString(1), rs.getString(2), rs.getLong(4),
                        rs.getString(3), rs.getInt(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
     public List<String> GetBrandById(int customerId) {
        String sql = "select distinct Brand from Product where CustomerId = ? ";
        List<String> brands = new ArrayList<>();
        try {
            p = connection.prepareStatement(sql);
            p.setInt(1, customerId);
            rs = p.executeQuery();

            while(rs.next()) {
                brands.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brands;
    }
}
