/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Model.Product;
import Model.Staff;
import Model.WarrantyForm;
import Model.WarrantyProcessing;
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

    public static void main(String[] args) {
        CustomerDao c = new CustomerDao();
        Product newProduct = new Product(null, null, 0, null, 1);

//        List<WarrantyInformation> list = c.WarrantyProductInformation(1, 1, "Laptop A", newProduct, null, null , null);
//
//        List<WarrantyForm> list = c.ProductDetail(1, "P001");
//        for (WarrantyForm w : list) {
//            System.out.println(w.getProduct().getProductId());
//        }
        System.out.println(c.GetTotalProductDetail(1, "P001"));
    }

    public int GetTotalProductByProductId(int CustomerId, String search, Product product) {
        String sql = "SELECT count(*) FROM Product p JOIN Customer c ON c.CustomerId = p.CustomerId WHERE c.CustomerId = ? ";

        if (search != null && !search.trim().isEmpty()) {
            sql += " AND (p.ProductId LIKE ? or p.ProductName like ? )";
        }

        if (product.getBrand() != null && !product.getBrand().trim().isEmpty()) {
            sql += " AND p.Brand = ? ";
        }

        try {
            p = connection.prepareStatement(sql);
            p.setInt(1, CustomerId);

            int index = 2;
            if (search != null && !search.trim().isEmpty()) {
                String searchPattern = "%" + search.replaceAll("\\s+", "") + "%";
                p.setString(index++, searchPattern);
                p.setString(index++, searchPattern);
            }

            if (product.getBrand() != null && !product.getBrand().trim().isEmpty()) {
                p.setString(index++, product.getBrand());
            }

            rs = p.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);  // Lấy giá trị count(*) chính xác
            }
        } catch (Exception e) {
            e.printStackTrace();  // In lỗi ra để dễ debug
        }
        return 0;  // Trả về 0 nếu có lỗi
    }

    public List<Product> SearchingProductByProductId(int index, int CustomerId, String search, Product product, String sort, String order, String priceRange, int amount) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product p JOIN Customer c ON c.CustomerId = p.CustomerId WHERE c.CustomerId = ? ";

        if (search != null && !search.trim().isEmpty()) {
            sql += " AND (p.ProductId LIKE ? or p.ProductName like ? )";
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

        sql += " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            p = connection.prepareStatement(sql);
            p.setInt(1, CustomerId);

            int paramIndex = 2;
            if (search != null && !search.trim().isEmpty()) {
                String searchPattern = "%" + search.replaceAll("\\s+", " ") + "%";
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

            p.setInt(paramIndex++, (index - 1) * amount);
            p.setInt(paramIndex, amount);
            rs = p.executeQuery();

            while (rs.next()) {
                list.add(new Product(rs.getString(1), rs.getString(2), rs.getLong(4),
                        rs.getString(3), rs.getInt(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int GetTotalProductDetail(int customerId, String productId) {
        String sql = "SELECT count(*)\n"
                + "FROM Product p JOIN Customer c ON c.CustomerId = p.CustomerId \n"
                + "join WarrantyForm wf on wf.ProductId = p.ProductId\n"
                + "WHERE c.CustomerId = ? and p.ProductId = ? and wf.Verified = 1";

        try {
            p = connection.prepareStatement(sql);
            p.setInt(1, customerId);
            p.setString(2, productId);
            rs = p.executeQuery();

            while (rs.next()) {

                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<WarrantyForm> ProductDetail(int index, int customerId, String productId, int amount) {
        List<WarrantyForm> list = new ArrayList<>();
        String sql = "SELECT wf.* , p.ProductName , p.Brand , p.Price , p.CustomerId\n"
                + "FROM Product p JOIN Customer c ON c.CustomerId = p.CustomerId \n"
                + "join WarrantyForm wf on wf.ProductId = p.ProductId\n"
                + "WHERE c.CustomerId = ? and p.ProductId = ? and wf.Verified = 1\n"
                + "order by p.ProductId \n"
                + "offset ? rows fetch next ? rows only";

        try {
            p = connection.prepareStatement(sql);
            p.setInt(1, customerId);
            p.setString(2, productId);
            p.setInt(3, (index - 1) * amount);
            p.setInt(4, amount);
            rs = p.executeQuery();

            while (rs.next()) {

                list.add(new WarrantyForm(rs.getInt(1), rs.getDate(3), rs.getDate(4),
                        rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getBoolean(8), new Product(rs.getString(2), rs.getString(9),
                        rs.getString(10))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
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

//    public List<WarrantyProcessing> WarrantyProductInformation(int index, int CustomerId, String search, Product product, String sort, String order, String priceRange) {
//        List<WarrantyInformation> list = new ArrayList<>();
//        String sql = "select w.* , p.ProductName , p.Brand , s.FirstName , s.LastName , s.Phone , s.StaffId\n"
//                + "from Product p join WarrantyInformation w on p.ProductId = w.ProductId\n"
//                + "join Staff s on s.StaffId = w.StaffId\n"
//                + "where p.CustomerId = ? and w.Status = 'In Progress'";
//
//        if (search != null && !search.trim().isEmpty()) {
//            sql += " AND (p.ProductId LIKE ? or p.ProductName like ? )";
//        }
//        if (product.getBrand() != null && !product.getBrand().trim().isEmpty()) {
//            sql += " and p.Brand = ? ";
//        }
//
//        if (priceRange != null && !priceRange.trim().isEmpty()) {
//            if (priceRange.equals("20000+")) {
//                sql += " AND p.Price >= 20000 ";
//            } else {
//                sql += " AND p.Price BETWEEN ? AND ? ";
//            }
//        }
//        if (sort != null && !sort.trim().isEmpty() && order != null && !order.trim().isEmpty()) {
//            sql += " ORDER BY p." + sort + " " + order;
//        } else {
//            sql += " ORDER BY w.InformationId ASC"; // Mặc định sắp xếp theo ProductId
//        }
//        sql += " OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY";
//
//        try {
//            p = connection.prepareStatement(sql);
//            p.setInt(1, CustomerId);
//
//            int paramIndex = 2;
//            if (search != null && !search.trim().isEmpty()) {
//                String searchPattern = "%" + search + "%";
//                p.setString(paramIndex++, searchPattern);
//                p.setString(paramIndex++, searchPattern);
//            }
//            if (product.getBrand() != null && !product.getBrand().trim().isEmpty()) {
//                p.setString(paramIndex, product.getBrand());
//                paramIndex++;
//            }
//            if (priceRange != null && !priceRange.trim().isEmpty() && !priceRange.equals("20000+")) {
//                String[] range = priceRange.split("-");
//                p.setFloat(paramIndex, Float.parseFloat(range[0]));
//                paramIndex++;
//                p.setFloat(paramIndex, Float.parseFloat(range[1]));
//                paramIndex++;
//            }
//            p.setInt(paramIndex, (index - 1) * 10);
//            rs = p.executeQuery();
//
//            while (rs.next()) {
//                Staff s = new Staff(rs.getString(6), rs.getString(9),
//                        rs.getString(10), rs.getString(11), rs.getString(12));
//                Product newProduct = new Product(rs.getString(5), rs.getString(7), rs.getString(8));
//                list.add(
//                        new WarrantyInformation(rs.getInt(1), rs.getString(2),
//                                rs.getString(3), rs.getDate(4), newProduct,
//                                s));
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
}
