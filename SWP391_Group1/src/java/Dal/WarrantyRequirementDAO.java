package dao;

import Dal.DBContext;
import Model.WarrantyRequirement;
import java.sql.*;

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
}
