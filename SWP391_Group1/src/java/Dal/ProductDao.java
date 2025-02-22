/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Model.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author thang
 */
public class ProductDao extends DBContext {

    private PreparedStatement p;
    private ResultSet rs;
    public static void main(String[] args) {
        ProductDao p = new ProductDao();
        System.out.println(p.GetProductById("P001").getProductId());
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
}
