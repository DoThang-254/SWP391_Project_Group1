/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Model.Product;
import Model.Staff;
import Model.WarrantyInformation;
import Repository.ICustomerDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thang
 */
public class CustomerDao extends DBContext implements ICustomerDAO {

    private PreparedStatement p;
    private ResultSet rs;

    @Override
    public int GetTotalProductByProductId(int CustomerId, Product product) {
        String sql = "SELECT count(*) FROM Product p JOIN Customer c ON c.CustomerId = p.CustomerId WHERE c.CustomerId = ? ";

        if (product.getProductId() != null && !product.getProductId().trim().isEmpty()) {
            sql += " AND p.ProductId LIKE ? ";
        }

        if (product.getBrand() != null && !product.getBrand().trim().isEmpty()) {
            sql += " AND p.Brand = ? ";
        }

        try {
            PreparedStatement p = connection.prepareStatement(sql);
            p.setInt(1, CustomerId);

            int index = 2;
            if (product.getProductId() != null && !product.getProductId().trim().isEmpty()) {
                p.setString(index++, "%" + product.getProductId() + "%");
            }

            if (product.getBrand() != null && !product.getBrand().trim().isEmpty()) {
                p.setString(index++, product.getBrand());
            }

            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);  // Lấy giá trị count(*) chính xác
            }
        } catch (Exception e) {
            e.printStackTrace();  // In lỗi ra để dễ debug
        }
        return 0;  // Trả về 0 nếu có lỗi
    }

    public List<Product> SearchingProductByProductId(int index, int CustomerId, Product product, String sort, String order, String priceRange) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product p JOIN Customer c ON c.CustomerId = p.CustomerId WHERE c.CustomerId = ? ";

        if (product.getProductId() != null && !product.getProductId().trim().isEmpty()) {
            sql += " AND p.ProductId LIKE ? ";
        }
        if (product.getBrand() != null && !product.getBrand().trim().isEmpty()) {
            sql += " AND p.Brand = ? ";
        }

        if (priceRange != null && !priceRange.trim().isEmpty()) {
            if (priceRange.equals("20000+")) {
                sql += " AND p.Price >= 20000 ";
            } else {
                sql += " AND p.Price BETWEEN ? AND ? ";
            }
        }

        if (sort != null && !sort.trim().isEmpty() && order != null && !order.trim().isEmpty()) {
            sql += " ORDER BY p." + sort + " " + order;
        } else {
            sql += " ORDER BY p.ProductId ASC"; // Mặc định sắp xếp theo ProductId
        }

        sql += " OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY";

        try {
            p = connection.prepareStatement(sql);
            p.setInt(1, CustomerId);

            int paramIndex = 2;
            if (product.getProductId() != null && !product.getProductId().trim().isEmpty()) {
                p.setString(paramIndex, "%" + product.getProductId() + "%");
                paramIndex++;
            }
            if (product.getBrand() != null && !product.getBrand().trim().isEmpty()) {
                p.setString(paramIndex, product.getBrand());
                paramIndex++;
            }

            if (priceRange != null && !priceRange.trim().isEmpty() && !priceRange.equals("20000+")) {
                String[] range = priceRange.split("-");
                p.setFloat(paramIndex, Float.parseFloat(range[0]));
                paramIndex++;
                p.setFloat(paramIndex, Float.parseFloat(range[1]));
                paramIndex++;
            }

            p.setInt(paramIndex, (index - 1) * 10);
            rs = p.executeQuery();

            while (rs.next()) {
                list.add(new Product(rs.getString(1), rs.getString(2), rs.getDate(3),
                        rs.getFloat(4), rs.getString(5), rs.getDate(6), rs.getInt(7)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Product ProductDetail(int customerId, String productId) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product p JOIN Customer c ON c.CustomerId = p.CustomerId WHERE c.CustomerId = ? and p.ProductId = ?";

        try {
            p = connection.prepareStatement(sql);
            p.setInt(1, customerId);

            p.setString(2, productId);
            rs = p.executeQuery();

            while (rs.next()) {
                return new Product(rs.getString(1), rs.getString(2), rs.getDate(3),
                        rs.getFloat(4), rs.getString(5), rs.getDate(6), rs.getInt(7));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int GetTotalProductWarrantyByCustomerId(int CustomerId, String search, Product product) {
        String sql = "select count(*)\n"
                + "from Product p join WarrantyInformation w on p.ProductId = w.ProductId\n"
                + "join Staff s on s.StaffId = w.StaffId\n"
                + "where p.CustomerId = ? and w.Status = 'In Progress' ";

        if (search != null && !search.trim().isEmpty()) {
            sql += " AND (p.ProductId LIKE ? or p.ProductName like ? )";
        }
        if (product.getBrand() != null && !product.getBrand().trim().isEmpty()) {
            sql += " AND p.Brand = ? ";
        }

        try {
            PreparedStatement p = connection.prepareStatement(sql);
            p.setInt(1, CustomerId);

            int index = 2;
            if (search != null && !search.trim().isEmpty()) {
                String searchPattern = "%" + search + "%";
                p.setString(index++, searchPattern);
                p.setString(index++, searchPattern);
            }
            if (product.getBrand() != null && !product.getBrand().trim().isEmpty()) {
                p.setString(index++, product.getBrand());
            }

            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);  // Lấy giá trị count(*) chính xác
            }
        } catch (Exception e) {
            e.printStackTrace();  // In lỗi ra để dễ debug
        }
        return 0;  // Trả về 0 nếu có lỗi
    }

    public static void main(String[] args) {
        CustomerDao c = new CustomerDao();
        Product newProduct = new Product(null, null, null, 0, null, null, 1);

        List<WarrantyInformation> list = c.WarrantyProductInformation(1, 1, "Laptop A", newProduct, null, null , null);
        for (WarrantyInformation w : list) {
            System.out.println(w.getInformationId());
        }
        System.out.println(c.GetTotalProductWarrantyByCustomerId(1, "Laptop A", newProduct));
    }

    public List<WarrantyInformation> WarrantyProductInformation(int index, int CustomerId, String search, Product product, String sort, String order, String priceRange) {
        List<WarrantyInformation> list = new ArrayList<>();
        String sql = "select w.* , p.ProductName , p.Brand , s.FirstName , s.LastName , s.Phone , s.StaffId\n"
                + "from Product p join WarrantyInformation w on p.ProductId = w.ProductId\n"
                + "join Staff s on s.StaffId = w.StaffId\n"
                + "where p.CustomerId = ? and w.Status = 'In Progress'";

        if (search != null && !search.trim().isEmpty()) {
            sql += " AND (p.ProductId LIKE ? or p.ProductName like ? )";
        }
        if (product.getBrand() != null && !product.getBrand().trim().isEmpty()) {
            sql += " and p.Brand = ? ";
        }

        if (priceRange != null && !priceRange.trim().isEmpty()) {
            if (priceRange.equals("20000+")) {
                sql += " AND p.Price >= 20000 ";
            } else {
                sql += " AND p.Price BETWEEN ? AND ? ";
            }
        }
        if (sort != null && !sort.trim().isEmpty() && order != null && !order.trim().isEmpty()) {
            sql += " ORDER BY p." + sort + " " + order;
        } else {
            sql += " ORDER BY w.InformationId ASC"; // Mặc định sắp xếp theo ProductId
        }
        sql += " OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY";

        try {
            p = connection.prepareStatement(sql);
            p.setInt(1, CustomerId);

            int paramIndex = 2;
            if (search != null && !search.trim().isEmpty()) {
                String searchPattern = "%" + search + "%";
                p.setString(paramIndex++, searchPattern);
                p.setString(paramIndex++, searchPattern);
            }
            if (product.getBrand() != null && !product.getBrand().trim().isEmpty()) {
                p.setString(paramIndex, product.getBrand());
                paramIndex++;
            }
            if (priceRange != null && !priceRange.trim().isEmpty() && !priceRange.equals("20000+")) {
                String[] range = priceRange.split("-");
                p.setFloat(paramIndex, Float.parseFloat(range[0]));
                paramIndex++;
                p.setFloat(paramIndex, Float.parseFloat(range[1]));
                paramIndex++;
            }
            p.setInt(paramIndex, (index - 1) * 10);
            rs = p.executeQuery();

            while (rs.next()) {
                Staff s = new Staff(rs.getString(6), rs.getString(9),
                        rs.getString(10), rs.getString(11), rs.getString(12));
                Product newProduct = new Product(rs.getString(5), rs.getString(7), rs.getString(8));
                list.add(
                        new WarrantyInformation(rs.getInt(1), rs.getString(2),
                                rs.getString(3), rs.getDate(4), newProduct,
                                s));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
