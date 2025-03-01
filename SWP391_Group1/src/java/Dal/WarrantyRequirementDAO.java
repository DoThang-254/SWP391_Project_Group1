package dao;

import Dal.DBContext;
import Model.Customer;
import Model.Product;
import Model.Staff;
import Model.WarrantyRequirement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WarrantyRequirementDAO extends DBContext {

    private PreparedStatement p;
    private ResultSet rs;

    public static void main(String[] args) {
        WarrantyRequirementDAO d = new WarrantyRequirementDAO();
        boolean check = d.hasPendingRequest("P001");
        System.out.println(check);
    }

    public boolean hasPendingRequest(String productId) {
        String sql = "SELECT COUNT(*) FROM WarrantyRequirement WHERE ProductId = ? AND Status = 'Pending'";

        try {
            p = connection.prepareStatement(sql);

            p.setString(1, productId);
            rs = p.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void insertWarrantyRequirement(WarrantyRequirement request) {
        String sql = "INSERT INTO WarrantyRequirement (ProductId, CustomerId, Status,Description ,RegisterDate , Ispay) VALUES (?, ?, ?, ? , ? , ?)";

        try {
            p = connection.prepareStatement(sql);
            p.setString(1, request.getProduct().getProductId());
            p.setInt(2, request.getCustomer().getCustomerId());
            p.setString(3, request.getStatus());
            p.setString(4, request.getDescription());
            p.setDate(5, new java.sql.Date(request.getRegisterDate().getTime()));
            p.setString(6, request.isIsPay());

            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<WarrantyRequirement> GetAllRequest() {
        List<WarrantyRequirement> list = new ArrayList<>();
        String sql = "SELECT * FROM WarrantyRequirement ";

        try {
            p = connection.prepareStatement(sql);

            rs = p.executeQuery();

            while (rs.next()) {
                WarrantyRequirement wr = new WarrantyRequirement();
                wr.setRequirementId(rs.getInt(1));
                Product p = new Product();
                p.setProductId(rs.getString(2));
                wr.setProduct(p);
                Staff s = new Staff();
                s.setStaffId(rs.getString(4));
                wr.setStaff(s);
                Customer c = new Customer();
                c.setCustomerId(rs.getInt(3));
                wr.setCustomer(c);
                wr.setStatus(rs.getString(5));
                wr.setDescription(rs.getString(6));
                wr.setRegisterDate(rs.getDate(8));
                wr.setIsPay(rs.getString(9));
                list.add(wr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void UpdateStatusRequest(String status, int requirementId) {
        String sql = "UPDATE [dbo].[WarrantyRequirement]\n"
                + "   SET \n"
                + "      [Status] = ?\n"
                + "      \n"
                + " WHERE RequirementId = ? ";

        try {
            p = connection.prepareStatement(sql);

            p.setString(1, status);
            p.setInt(2, requirementId);
            p.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void UpdateStaffRequest(String staffId, int requirementId, String status) {
        String sql = "UPDATE [dbo].[WarrantyRequirement]\n"
                + "   SET \n"
                + "		StaffId = ? , [Status] = ?\n"
                + "      \n"
                + " WHERE RequirementId = ? ";

        try {
            p = connection.prepareStatement(sql);

            p.setString(1, staffId);
            p.setString(2, status);
            p.setInt(3, requirementId);

            p.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<WarrantyRequirement> GetAllRequestByStaffId(String staffId) {
        List<WarrantyRequirement> list = new ArrayList<>();
        String sql = "SELECT * FROM WarrantyRequirement WHERE StaffId = ? ";

        try {
            p = connection.prepareStatement(sql);
            p.setString(1, staffId);
            rs = p.executeQuery();

            while (rs.next()) {
                WarrantyRequirement wr = new WarrantyRequirement();
                wr.setRequirementId(rs.getInt(1));
                Product p = new Product();
                p.setProductId(rs.getString(2));
                wr.setProduct(p);
                Staff s = new Staff();
                s.setStaffId(rs.getString(4));
                wr.setStaff(s);
                wr.setStatus(rs.getString(5));
                wr.setDescription(rs.getString(6));
                list.add(wr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
