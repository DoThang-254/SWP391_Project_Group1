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

    public static void main(String[] args) {
        WarrantyFormDao wfd = new WarrantyFormDao();
        System.out.println(wfd.getActiveWarrantyFormByProduct("P001").getProduct().getProductId());
    }
    private PreparedStatement p;

    public WarrantyForm getActiveWarrantyFormByProduct(String productId) {
        WarrantyForm warrantyForm = null;
        String sql = "SELECT * FROM WarrantyForm WHERE ProductId = ? AND EndDate >= GETDATE() ORDER BY EndDate DESC";

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
                + "           ,[EndDate]\n"
                + "           ,[Verified])\n"
                + "     VALUES\n"
                + "           (?, GETDATE(), DATEADD(YEAR, 1, GETDATE()), 1)";
        try {
            p = connection.prepareStatement(sql);
            p.setString(1, productId);
            p.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateVerified(int formId) {
        String query = "UPDATE [dbo].[WarrantyForm]\n"
                + "   SET \n"
                + "      [Verified] = 1\n"
                + " WHERE FormId = ? ";
        try {

            p = connection.prepareStatement(query);
            p.setInt(1, formId);
            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
            }
        } catch (SQLException e) {
        }
        return warrantyForm;
    }

    public void updateStartDate(WarrantyForm wf) {
        String query = "UPDATE [dbo].[WarrantyForm]\n"
                + "   SET \n"
                + "      [StartDate] = getdate()\n"
                + "\n"
                + "     \n"
                + "      ,[Verified] = 0\n"
                + " WHERE FormId = ? ";
        try {

            p = connection.prepareStatement(query);
            p.setInt(1, wf.getFormId());
            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
