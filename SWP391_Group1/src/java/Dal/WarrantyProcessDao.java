/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Model.Invoice;
import Model.Product;
import Model.Staff;
import Model.WarrantyProcessing;
import Model.WarrantyRequirement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thang
 */
public class WarrantyProcessDao extends DBContext {

    private PreparedStatement p;
    private ResultSet rs;

    public void insertWarrantyProcess(int requirementId, String staffId) {
        String sql = "INSERT INTO [dbo].[WarrantyProcessing]\n"
                + "           ([RequirementId],[StaffId])\n"
                + "     VALUES (?,?)";

        try {
            p = connection.prepareStatement(sql);
            p.setInt(1, requirementId);
            p.setString(2, staffId);

            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<WarrantyProcessing> getAllWarrantyProcess(String staffId) {
        List<WarrantyProcessing> list = new ArrayList<>();
        String sql = "  select wp.* , wr.RequirementId , p.ProductId from WarrantyProcessing wp join WarrantyRequirement wr \n"
                + "  on  wr.RequirementId = wp.RequirementId join  Product p on p.ProductId = wr.ProductId\n"
                + "  where wp.StaffId = ?";

        try {
            p = connection.prepareStatement(sql);
            p.setString(1, staffId);
            rs = p.executeQuery();
            while (rs.next()) {
                WarrantyProcessing wp = new WarrantyProcessing();
                wp.setProcessingId(rs.getInt(1));
                WarrantyRequirement wr = new WarrantyRequirement();
                wr.setRequirementId(rs.getInt(2));
                Product p = new Product();
                p.setProductId(rs.getString(8));
                wr.setProduct(p);
                wp.setRequirement(wr);
                Staff s = new Staff();
                s.setStaffId(rs.getString(3));
                wp.setStaff(s);
                wp.setStatus(rs.getString(4));
                list.add(wp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void updateStatusWarrantyProcess(int processId, String status) {
        String sql = "UPDATE [dbo].[WarrantyProcessing]\n"
                + "   SET [Status] = ?\n"
                + "   WHERE ProcessingId = ? ";

        try {
            p = connection.prepareStatement(sql);
            p.setString(1, status);
            p.setInt(2, processId);

            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkIsPayinRequirement(int requirementId, int processId) {
        String sql = "select count(*) from WarrantyRequirement wr join WarrantyProcessing wp\n"
                + "on wr.RequirementId = wp.RequirementId\n"
                + "where wr.IsPay = 'yes' and wr.RequirementId = ? and wp.ProcessingId = ? ";

        try {
            p = connection.prepareStatement(sql);
            p.setInt(1, requirementId);
            p.setInt(2, processId);
            rs = p.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
