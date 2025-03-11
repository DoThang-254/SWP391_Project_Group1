/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Model.Invoice;
import Model.Product;
import Model.Staff;
import Model.WarrantyForm;
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

    public boolean isWarrantyProcessExists(int requirementId) {
        String sql = "SELECT COUNT(*) FROM WarrantyProcessing WHERE RequirementId = ?";
        try {
            p = connection.prepareStatement(sql);
            p.setInt(1, requirementId);
            rs = p.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Nếu có bản ghi => true
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void insertWarrantyProcess(int requirementId, String staffId) {
        String sql = "INSERT INTO [dbo].[WarrantyProcessing]\n"
                + "           ([RequirementId],[StaffId] , [Status])\n"
                + "     VALUES (?,?,?)";

        try {
            p = connection.prepareStatement(sql);
            p.setInt(1, requirementId);
            p.setString(2, staffId);
            p.setString(3, "Approved");

            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<WarrantyProcessing> getAllWarrantyProcess(String staffId) {
        List<WarrantyProcessing> list = new ArrayList<>();
        String sql = "select wp.* , wr.* , p.ProductId from WarrantyProcessing wp join WarrantyRequirement wr \n"
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
                wr.setIsPay(rs.getString(16));
                WarrantyForm form = new WarrantyForm();
                form.setFormId(rs.getInt(17));
                wr.setForm(form);
                Product p = new Product();
                p.setProductId(rs.getString(9));
                wr.setProduct(p);
                wp.setRequirement(wr);
                Staff s = new Staff();
                s.setStaffId(rs.getString(3));
                wp.setStaff(s);
                wp.setStatus(rs.getString(4));
                wp.setIsAccept(rs.getString(7));
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

    public void updateIsAcceptWarrantyProcess(int processId, String isAccept) {
        String sql = "UPDATE [dbo].[WarrantyProcessing]\n"
                + "   SET \n"
                + "      [IsAccept] = ?\n"
                + " WHERE ProcessingId = ?";

        try {
            p = connection.prepareStatement(sql);
            p.setString(1, isAccept);
            p.setInt(2, processId);
            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int GetTotalWarrantyProcess(int CustomerId) {
        String sql = "select count(*) from WarrantyProcessing wp join WarrantyRequirement wr\n"
                + "on wp.RequirementId = wr.RequirementId \n"
                + "where wr.CustomerId = ?";

        try {
            p = connection.prepareStatement(sql);
            p.setInt(1, CustomerId);

            rs = p.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);  // Lấy giá trị count(*) chính xác
            }
        } catch (Exception e) {
            e.printStackTrace();  // In lỗi ra để dễ debug
        }
        return 0;  // Trả về 0 nếu có lỗi
    }

    public List<WarrantyProcessing> processListByCustomerId(int index, int customerId, int amount) {
        List<WarrantyProcessing> list = new ArrayList<>();
        String sql = "select * from WarrantyProcessing wp join WarrantyRequirement wr \n"
                + "on wp.RequirementId = wr.RequirementId \n"
                + "where wr.CustomerId = ? order by wr.RegisterDate OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            p = connection.prepareStatement(sql);
            p.setInt(1, customerId);
            p.setInt(2, (index - 1) * amount);
            p.setInt(3, amount);
            rs = p.executeQuery();
            while (rs.next()) {
                WarrantyProcessing wp = new WarrantyProcessing();
                wp.setProcessingId(rs.getInt(1));
                WarrantyRequirement wr = new WarrantyRequirement();
                wr.setRequirementId(rs.getInt(2));
                wr.setRegisterDate(rs.getDate(15));
                wp.setRequirement(wr);
                Staff s = new Staff();
                s.setStaffId(rs.getString(3));
                wp.setStaff(s);
                wp.setStatus(rs.getString(4));
                wp.setNote(rs.getString(5));
                wp.setReturnDate(rs.getDate(6));
                wp.setIsAccept(rs.getString(7));

                list.add(wp);
            }
        } catch (Exception e) {
        }
        return list;
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

    public boolean checkFaultTypeInRequirement(String productId, int requirementId, int processId) {
        String sql = "select count(*) from WarrantyForm wf join WarrantyRequirement wr on wr.FormId = wf.FormId\n"
                + "join WarrantyProcessing wp on wp.RequirementId = wr.RequirementId\n"
                + "where wf.ProductId = ? and wr.RequirementId = ? and wf.FaultType = 'user' and wp.ProcessingId = ? ";

        try {
            p = connection.prepareStatement(sql);
            p.setString(1, productId);
            p.setInt(2, requirementId);
            p.setInt(3, processId);
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
