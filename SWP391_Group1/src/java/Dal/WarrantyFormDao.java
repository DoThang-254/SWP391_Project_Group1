/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Model.Product;
import Model.WarrantyForm;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author thang
 */
public class WarrantyFormDao extends DBContext {

    public boolean hasFormId(int requirementId) {
    String sql = "SELECT COUNT(*) FROM WarrantyRequirement WHERE requirementId = ? AND formId IS NOT NULL";
    try {
         p = connection.prepareStatement(sql) ;
        p.setInt(1, requirementId);
        try (ResultSet rs = p.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) > 0; // Trả về true nếu có formId
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

    
    public void updateUnverify(int formId) {
        String query = "UPDATE [dbo].[WarrantyForm] SET Verified = 'no' WHERE FormId = ?";
        try {
            p = connection.prepareStatement(query);
            p.setInt(1, formId);
            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTechUnverify(int formId) {
        String query = "UPDATE [dbo].[WarrantyForm] SET TechnicianVerify = 'no' WHERE FormId = ?";
        try {
            p = connection.prepareStatement(query);
            p.setInt(1, formId);
            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        WarrantyFormDao wfd = new WarrantyFormDao();
        System.out.println(wfd.getActiveWarrantyFormByProduct("P001").getProduct().getProductId());
    }
    private PreparedStatement p;

   public boolean hasActive(String productId) {
    String sql = "SELECT TOP 1 Status FROM WarrantyForm "
               + "WHERE productId = ? "
               + "ORDER BY FormId DESC"; // Lấy phiếu bảo hành mới nhất

    try {
        p = connection.prepareStatement(sql);
        p.setString(1, productId);
        ResultSet rs = p.executeQuery();
        if (rs.next()) {
            String status = rs.getString("Status");
            return "active".equalsIgnoreCase(status); // Chỉ trả về true nếu phiếu mới nhất là "active"
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false; // Trả về false nếu không có phiếu hoặc có lỗi
}

//    public boolean hasInactive(String productId) {
//        String sql = "SELECT TOP 1 FormId FROM WarrantyForm "
//                + "WHERE Status = 'inactive' and productId = ? "
//                + "ORDER BY FormId DESC";
//
//        try {
//            p = connection.prepareStatement(sql);
//            p.setString(1, productId);
//            ResultSet rs = p.executeQuery();
//            return rs.next();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false; // Lỗi xảy ra hoặc không có dữ liệu
//    }
//    public WarrantyForm hasActive(String productId) {
//        WarrantyForm warrantyForm = null;
//        String sql = "SELECT TOP 1 * FROM WarrantyForm \n"
//                + "WHERE  productId = ?\n"
//                + "ORDER BY FormId DESC , StartDate desc";
//
//        try {
//            p = connection.prepareStatement(sql);
//
//            p.setString(1, productId);
//            ResultSet rs = p.executeQuery();
//
//            if (rs.next()) {
//                warrantyForm = new WarrantyForm();
//                warrantyForm.setFormId(rs.getInt("FormId"));
//                Product p = new Product();
//                p.setProductId(rs.getString("ProductId"));
//                warrantyForm.setProduct(p);
//                warrantyForm.setStartDate(rs.getDate("StartDate"));
//                warrantyForm.setEndDate(rs.getDate("EndDate"));
//                warrantyForm.setStatus(rs.getString("Status"));
//            }
//        } catch (SQLException e) {
//        }
//        return warrantyForm;
//    }

    public WarrantyForm getActiveWarrantyFormByProduct(String productId) {
        WarrantyForm warrantyForm = null;
        String sql = "  SELECT * FROM WarrantyForm WHERE ProductId = ? AND EndDate >= GETDATE() and  Status = 'active' \n"
                + "  and Verified = 'yes' and TechnicianVerify = 'yes'\n"
                + "  ORDER BY EndDate DESC , FormId desc";

        try {
            p = connection.prepareStatement(sql);

            p.setString(1, productId);
            ResultSet rs = p.executeQuery();

            if (rs.next()) {
                warrantyForm = new WarrantyForm();
                warrantyForm.setFormId(rs.getInt("FormId"));
                Product p = new Product();
                p.setProductId(rs.getString("ProductId"));
                warrantyForm.setProduct(p);
                warrantyForm.setStartDate(rs.getDate("StartDate"));
                warrantyForm.setEndDate(rs.getDate("EndDate"));
                warrantyForm.setStatus(rs.getString("Status"));
            }
        } catch (SQLException e) {
        }
        return warrantyForm;
    }

    public void createWarrantyForm(String productId) {
        String sql = "INSERT INTO [dbo].[WarrantyForm]\n"
                + "           ([ProductId]\n"
                + "           ,[StartDate]\n"
                + "           ,[Status])\n"
                + "     VALUES\n"
                + "           (?,GETDATE(),'Inactive')";
        try {
            p = connection.prepareStatement(sql);
            p.setString(1, productId);

            p.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

//    public void updateVerified(int formId) {
//        String query = "UPDATE [dbo].[WarrantyForm]\n"
//                + "   SET \n"
//                + "      [Verified] = 1\n"
//                + " WHERE FormId = ? ";
//        try {
//
//            p = connection.prepareStatement(query);
//            p.setInt(1, formId);
//            p.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    public WarrantyForm getWarrantyFormByProductId(String productId) {
        WarrantyForm warrantyForm = null;
        String sql = "select * from WarrantyForm wf join Product p on p.ProductId = wf.ProductId\n"
                + "where p.ProductId = ? order by EndDate desc";

        try {
            p = connection.prepareStatement(sql);

            p.setString(1, productId);
            ResultSet rs = p.executeQuery();

            if (rs.next()) {
                warrantyForm = new WarrantyForm();
                warrantyForm.setFormId(rs.getInt("FormId"));
                Product p = new Product();
                p.setProductId(rs.getString("ProductId"));
                warrantyForm.setProduct(p);
                warrantyForm.setStartDate(rs.getDate("StartDate"));
                warrantyForm.setEndDate(rs.getDate("EndDate"));
                warrantyForm.setStatus(rs.getString("Status"));
                warrantyForm.setVerified(rs.getString(6));
                warrantyForm.setFaultType(rs.getString(7));
                warrantyForm.setImgUrl(rs.getString(8));
            }
        } catch (SQLException e) {
        }
        return warrantyForm;
    }

    public WarrantyForm getWarrantyFormByRequirementId(String productId, int requirementId) {
        WarrantyForm warrantyForm = null;
        String sql = "select * from WarrantyForm wf join WarrantyRequirement wr on wr.FormId = wf.FormId\n"
                + "where wf.ProductId = ? and wr.RequirementId = ?  \n";

        try {
            p = connection.prepareStatement(sql);

            p.setString(1, productId);
            p.setInt(2, requirementId);
            ResultSet rs = p.executeQuery();

            if (rs.next()) {
                warrantyForm = new WarrantyForm();
                warrantyForm.setFormId(rs.getInt("FormId"));
                Product p = new Product();
                p.setProductId(rs.getString("ProductId"));
                warrantyForm.setProduct(p);
                warrantyForm.setStartDate(rs.getDate("StartDate"));
                warrantyForm.setEndDate(rs.getDate("EndDate"));
                warrantyForm.setStatus(rs.getString("Status"));
                warrantyForm.setVerified(rs.getString(6));
                warrantyForm.setFaultType(rs.getString(7));
                warrantyForm.setImgUrl(rs.getString(8));
            }
        } catch (SQLException e) {
        }
        return warrantyForm;
    }

    public WarrantyForm getWarrantyForm(String productId, int formId) {
        WarrantyForm warrantyForm = null;
        String sql = "SELECT * FROM WarrantyRequirement wr join Product p on p.ProductId = wr.ProductId\n"
                + "join WarrantyForm wf on wf.ProductId = p.ProductId\n"
                + "where p.ProductId = ? and wf.FormId = ? ";

        try {
            p = connection.prepareStatement(sql);

            p.setString(1, productId);
            p.setInt(2, formId);
            ResultSet rs = p.executeQuery();

            if (rs.next()) {
                warrantyForm = new WarrantyForm();
                warrantyForm.setFormId(rs.getInt("FormId"));
                Product p = new Product();
                p.setProductId(rs.getString("ProductId"));
                warrantyForm.setProduct(p);
                warrantyForm.setStartDate(rs.getDate("StartDate"));
                warrantyForm.setEndDate(rs.getDate("EndDate"));
                warrantyForm.setStatus(rs.getString("Status"));
            }
        } catch (SQLException e) {
        }
        return warrantyForm;
    }

    public boolean canCreateWarrantyForm(int requirementId) {
        String sql = "SELECT FormId FROM WarrantyRequirement WHERE RequirementId = ?";
        try {
            PreparedStatement p = connection.prepareStatement(sql);
            p.setInt(1, requirementId);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                return rs.getInt("FormId") == 0; // Nếu FormId là NULL hoặc 0, thì có thể tạo
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public WarrantyForm getWarrantyFormbyProductId(String productId) { // lấy ra warranty formid mới nhất 
        WarrantyForm warrantyForm = null;
        String sql = "SELECT top 1 wf.* FROM WarrantyRequirement wr join Product p on p.ProductId = wr.ProductId\n"
                + "                join WarrantyForm wf on wf.ProductId = p.ProductId \n"
                + "                where p.ProductId = ?\n"
                + "                order by wf.formId desc";

        try {
            p = connection.prepareStatement(sql);

            p.setString(1, productId);
            ResultSet rs = p.executeQuery();

            if (rs.next()) {
                warrantyForm = new WarrantyForm();
                warrantyForm.setFormId(rs.getInt("FormId"));
                Product p = new Product();
                p.setProductId(rs.getString("ProductId"));
                warrantyForm.setProduct(p);
                warrantyForm.setStartDate(rs.getDate("StartDate"));
                warrantyForm.setEndDate(rs.getDate("EndDate"));
                warrantyForm.setStatus(rs.getString("Status"));
            }
        } catch (SQLException e) {
        }
        return warrantyForm;
    }

    public void UpdateFullFormId(WarrantyForm wf) {
        String sql = "UPDATE [dbo].[WarrantyForm]\n"
                + " SET \n"
                + " [EndDate] = ?\n"
                + " ,[FaultType] = ?\n"
                + " ,[ImageUrl] = ?\n"
                + " WHERE FormId = ? ";

        try {
            p = connection.prepareStatement(sql);
            java.sql.Date sqlEndDate = (wf.getEndDate() != null) ? new java.sql.Date(wf.getEndDate().getTime()) : null;

            p.setDate(1, sqlEndDate);
            p.setString(2, wf.getFaultType());
            p.setString(3, wf.getImgUrl());
            p.setInt(4, wf.getFormId());
            p.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public WarrantyForm getWarrantyFormbyFormId(int formId) {
        WarrantyForm warrantyForm = null;
        String sql = "select * from WarrantyForm where FormId = ?";

        try {
            p = connection.prepareStatement(sql);

            p.setInt(1, formId);
            ResultSet rs = p.executeQuery();

            if (rs.next()) {
                warrantyForm = new WarrantyForm();
                warrantyForm.setFormId(rs.getInt("FormId"));
                Product p = new Product();
                p.setProductId(rs.getString("ProductId"));
                warrantyForm.setProduct(p);
                warrantyForm.setStartDate(rs.getDate("StartDate"));
                warrantyForm.setEndDate(rs.getDate("EndDate"));
                warrantyForm.setStatus(rs.getString("Status"));
                warrantyForm.setVerified(rs.getString(6));
                warrantyForm.setFaultType(rs.getString(7));
                warrantyForm.setImgUrl(rs.getString(8));
            }
        } catch (SQLException e) {
        }
        return warrantyForm;
    }

    public void updateStatus(WarrantyForm wf) {
        String query = "UPDATE [dbo].[WarrantyForm]\n"
                + "   SET \n"
                + "      [Status] = 'active'\n"
                + "    \n"
                + " WHERE FormId = ?";
        try {

            p = connection.prepareStatement(query);
            p.setInt(1, wf.getFormId());
            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ResetForm(WarrantyForm wf) {
        String query = "UPDATE [dbo].[WarrantyForm]\n"
                + "   SET \n"
                + "\n"
                + "     \n"
                + "      [Verified] = 0 , status = 'inactive'\n"
                + " WHERE FormId = ? ";
        try {

            p = connection.prepareStatement(query);
            p.setInt(1, wf.getFormId());
            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateForm(String faultType, String img, int formId) {
        String query = "UPDATE [dbo].[WarrantyForm]\n"
                + "   SET \n"
                + "      [FaultType] = ?\n"
                + "      ,[ImageUrl] = ?\n"
                + " WHERE FormId = ?";
        try {

            p = connection.prepareStatement(query);
            p.setString(1, faultType);
            p.setString(2, img);
            p.setInt(3, formId);
            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateVerify(int formId) {
        String query = "UPDATE [dbo].[WarrantyForm]\n"
                + "   SET [Verified] = 'yes'\n"
                + "      \n"
                + " WHERE FormId = ? ";
        try {

            p = connection.prepareStatement(query);
            p.setInt(1, formId);
            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTechVerify(int formId) {
        String query = "UPDATE [dbo].[WarrantyForm]\n"
                + "   SET [TechnicianVerify] = 'yes'\n"
                + "      \n"
                + " WHERE FormId = ? ";
        try {

            p = connection.prepareStatement(query);
            p.setInt(1, formId);
            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isVerified(int formId) {
        String query = "SELECT Verified FROM [dbo].[WarrantyForm] WHERE FormId = ?";
        try {
            p = connection.prepareStatement(query);
            p.setInt(1, formId);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                String verified = rs.getString("Verified");
                return "yes".equalsIgnoreCase(verified);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isTechVerified(int formId) {
        String query = "SELECT TechnicianVerify FROM [dbo].[WarrantyForm] WHERE FormId = ?";
        try {
            p = connection.prepareStatement(query);
            p.setInt(1, formId);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                String techVerified = rs.getString("TechnicianVerify");
                return "yes".equalsIgnoreCase(techVerified);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Trả về false nếu có lỗi hoặc không tìm thấy formId
    }

}
