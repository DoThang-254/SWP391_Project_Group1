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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public boolean hasInvoice(int requirementId) {
        String sql = "SELECT * FROM Invoice WHERE RequirementId = ?";

        try {
            p = connection.prepareStatement(sql);
            p.setInt(1, requirementId);
            rs = p.executeQuery();
            return rs.next(); // Nếu có bản ghi, tức là đã tạo hóa đơn
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Mặc định là chưa có hóa đơn
    }

//    public List<WarrantyProcessing> getAllWarrantyProcess(String staffId) {
//        List<WarrantyProcessing> list = new ArrayList<>();
//        String sql = "select wp.* , wr.* , p.ProductId from WarrantyProcessing wp join WarrantyRequirement wr \n"
//                + "                 on  wr.RequirementId = wp.RequirementId join  Product p on p.ProductId = wr.ProductId \n"
//                + "                where wp.StaffId = ?";
//
//        try {
//            p = connection.prepareStatement(sql);
//            p.setString(1, staffId);
//            rs = p.executeQuery();
//            while (rs.next()) {
//                WarrantyProcessing wp = new WarrantyProcessing();
//                wp.setProcessingId(rs.getInt(1));
//                WarrantyRequirement wr = new WarrantyRequirement();
//                wr.setRequirementId(rs.getInt(2));
//                wr.setIsPay(rs.getString(16));
//                WarrantyForm form = new WarrantyForm();
//                form.setFormId(rs.getInt(17));
//                wr.setForm(form);
//                Product p = new Product();
//                p.setProductId(rs.getString(9));
//                wr.setProduct(p);
//                wp.setRequirement(wr);
//                Staff s = new Staff();
//                s.setStaffId(rs.getString(3));
//                wp.setStaff(s);
//                wp.setStatus(rs.getString(4));
//                wp.setIsAccept(rs.getString(7));
//wr.setHasInvoice(hasInvoice(wr.getRequirementId()));
//
//                list.add(wp);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return list;
    //       String sql = "SELECT wp.*, wr.*, p.ProductId, "
//                + "       CASE WHEN EXISTS (SELECT 1 FROM Invoice i WHERE i.RequirementId = wr.RequirementId) "
//                + "       THEN 1 ELSE 0 END AS hasInvoice "
//                + "FROM WarrantyProcessing wp "
//                + "JOIN WarrantyRequirement wr ON wr.RequirementId = wp.RequirementId "
//                + "JOIN Product p ON p.ProductId = wr.ProductId "
//                + "WHERE wp.StaffId = ?";
//
//        try {
//            p = connection.prepareStatement(sql);
//            p.setString(1, staffId);
//            rs = p.executeQuery();
//            while (rs.next()) {
//                WarrantyProcessing wp = new WarrantyProcessing();
//                wp.setProcessingId(rs.getInt(1));
//
//                WarrantyRequirement wr = new WarrantyRequirement();
//                wr.setRequirementId(rs.getInt(2));
//                wr.setIsPay(rs.getString(16));
//
//                // Gán trạng thái hasInvoice trực tiếp từ SQL
//                wr.setHasInvoice(rs.getBoolean("hasInvoice"));
//
//                WarrantyForm form = new WarrantyForm();
//                form.setFormId(rs.getInt(17));
//                wr.setForm(form);
//
//                Product p = new Product();
//                p.setProductId(rs.getString(9));
//                wr.setProduct(p);
//
//                wp.setRequirement(wr);
//                Staff s = new Staff();
//                s.setStaffId(rs.getString(3));
//                wp.setStaff(s);
//                wp.setStatus(rs.getString(4));
//                wp.setIsAccept(rs.getString(7));
//
//                list.add(wp);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
    public Set<Integer> getAllRequirementWithInvoice() {
        Set<Integer> invoiceSet = new HashSet<>();
        String sql = "SELECT DISTINCT RequirementId FROM Invoice";

        try {
            PreparedStatement p = connection.prepareStatement(sql);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                invoiceSet.add(rs.getInt("RequirementId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return invoiceSet;
    }

   
    public List<WarrantyProcessing> getAllWarrantyProcess(String staffId) {
        List<WarrantyProcessing> list = new ArrayList<>();

        // Lấy danh sách requirementId có hóa đơn
        Set<Integer> invoiceSet = getAllRequirementWithInvoice();

        String sql = "SELECT wp.*, wr.*, p.ProductId "
                + "FROM WarrantyProcessing wp "
                + "JOIN WarrantyRequirement wr ON wr.RequirementId = wp.RequirementId "
                + "JOIN Product p ON p.ProductId = wr.ProductId"
                + " WHERE wp.StaffId = ?";

        try {
            p = connection.prepareStatement(sql);
            p.setString(1, staffId);
            rs = p.executeQuery();

            while (rs.next()) {
                WarrantyProcessing wp = new WarrantyProcessing();
                wp.setProcessingId(rs.getInt(1));

                WarrantyRequirement wr = new WarrantyRequirement();
                wr.setRequirementId(rs.getInt(2));
                wr.setIsPay(rs.getString(15));

                WarrantyForm form = new WarrantyForm();
                form.setFormId(rs.getInt(16));
                wr.setForm(form);

                Product p = new Product();
                p.setProductId(rs.getString(8));
                wr.setProduct(p);

                wp.setRequirement(wr);
                Staff s = new Staff();
                s.setStaffId(rs.getString(3));
                wp.setStaff(s);
                wp.setStatus(rs.getString(4));
                wp.setIsAccept(rs.getString(7));
                wp.setReturnDate(rs.getDate(6));
                // Kiểm tra xem requirementId có trong danh sách đã có hóa đơn không
                wr.setHasInvoice(invoiceSet.contains(wr.getRequirementId()));

                list.add(wp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void updateStatusWarrantyProcess(int processId, String status, String returnDate) {
        String sql = "UPDATE [dbo].[WarrantyProcessing]\n"
                + "				SET [Status] = ? , \n"
                + "				ReturnDate = ?\n"
                + "                WHERE ProcessingId = ?";

        try {
            p = connection.prepareStatement(sql);
            p.setString(1, status);
            p.setString(2, returnDate);
            p.setInt(3, processId);

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

     public static void main(String[] args) {
        WarrantyProcessDao w = new WarrantyProcessDao();
        for (WarrantyProcessing x : w.processListByCustomerId(1, 1, 1)) {
            System.out.println(x.getProcessingId());
        }

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
                wr.setRegisterDate(rs.getDate(14));
                Product product = new Product();
                product.setProductId(rs.getString(8));
                wr.setProduct(product);
                WarrantyForm wf = new WarrantyForm();
                wf.setFormId(rs.getInt(16));
                wr.setForm(wf);
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
            System.out.println(e);
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
