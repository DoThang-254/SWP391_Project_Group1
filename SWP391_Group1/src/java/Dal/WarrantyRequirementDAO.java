package dao;

import Dal.DBContext;
import Model.Customer;
import Model.Invoice;
import Model.Product;
import Model.Staff;
import Model.WarrantyForm;
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
        String sql = "SELECT COUNT(*) FROM WarrantyRequirement WHERE ProductId = ? AND (Status = 'Pending' or Status = 'Uncheck')";

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

    public boolean hasUnPayRequest(String productId) {
        String sql = "SELECT * FROM WarrantyRequirement wr join Invoice i on i.RequirementId = wr.RequirementId\n"
                + "WHERE ProductId = ? and i.Status = 'unPaid'";

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
            p.setString(6, request.getIsPay());

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

    public List<WarrantyRequirement> GetAllRequestByStaffId(String staffId) {
        List<WarrantyRequirement> list = new ArrayList<>();
        String sql = "SELECT wr.* , s.Email , wf.* FROM WarrantyRequirement wr join Staff s on wr.StaffId = s.StaffId\n"
                + "join WarrantyForm wf on wf.FormId = wr.FormId\n"
                + "where s.StaffId = ?";

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
                s.setEmail(rs.getString(11));
                wr.setStaff(s);
                Customer c = new Customer();
                c.setCustomerId(rs.getInt(3));
                wr.setCustomer(c);
                wr.setStatus(rs.getString(5));
                wr.setDescription(rs.getString(6));
                wr.setRegisterDate(rs.getDate(8));
                wr.setIsPay(rs.getString(9));
                WarrantyForm form = new WarrantyForm();
                form.setFormId(rs.getInt(10));
                form.setStatus(rs.getString(16));
                form.setVerified(rs.getString(17));
                form.setFaultType(rs.getString(18));
                form.setTechnicianVerify(rs.getString(19));
                wr.setForm(form);
                list.add(wr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<WarrantyRequirement> GetAllRequestByStaffIdWithout(String staffId) {
        List<WarrantyRequirement> list = new ArrayList<>();
        String sql = "SELECT wr.* , s.Email FROM WarrantyRequirement wr join Staff s on wr.StaffId = s.StaffId\n"
                + "\n"
                + "where s.StaffId = ?";

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
                s.setEmail(rs.getString(11));
                wr.setStaff(s);
                Customer c = new Customer();
                c.setCustomerId(rs.getInt(3));
                wr.setCustomer(c);
                wr.setStatus(rs.getString(5));
                wr.setDescription(rs.getString(6));
                wr.setRegisterDate(rs.getDate(8));
                wr.setIsPay(rs.getString(9));
                WarrantyForm form = new WarrantyForm();
                form.setFormId(rs.getInt(10));
               
                wr.setForm(form);
                list.add(wr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void UpdateFormId(int formId, int requirementId) {
        String sql = "UPDATE [dbo].[WarrantyRequirement]\n"
                + "   SET \n"
                + "      [FormId] = ?\n"
                + " WHERE RequirementId = ?  ";

        try {
            p = connection.prepareStatement(sql);

            p.setInt(1, formId);
            p.setInt(2, requirementId);
            p.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public int GetTotalWarrantyRequest(int customerId) {
        String sql = " select count(*) from WarrantyRequirement wr join WarrantyForm wf on wr.FormId = wf.FormId \n"
                + " join Customer c on c.CustomerId = wr.CustomerId\n"
                + "                  where wr.CustomerId = ?";

        try {
            p = connection.prepareStatement(sql);
            p.setInt(1, customerId);
            rs = p.executeQuery();

            while (rs.next()) {

                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<WarrantyRequirement> GetAllRequestByCustomerId(int index, int customerId, int amount) {
        List<WarrantyRequirement> list = new ArrayList<>();
        String sql = " select wr.* , wf.* , c.Email , i.InvoiceId from WarrantyRequirement wr join WarrantyForm wf on wr.FormId = wf.FormId \n" +
"                 join Customer c on c.CustomerId = wr.CustomerId join Invoice i on i.RequirementId = wr.RequirementId\n" +
"                 where wr.CustomerId = ?\n" +
"                 order by wr.requirementId desc , wr.registerDate desc\n" +
"                 offset ? rows fetch next ? rows only";

        try {
            p = connection.prepareStatement(sql);
            p.setInt(1, customerId);
            p.setInt(2, (index - 1) * amount);
            p.setInt(3, amount);

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
                Customer cust = new Customer();
                cust.setCustomerId(rs.getInt(3));
                cust.setEmail(rs.getString(20));
                wr.setCustomer(cust);
                wr.setRegisterDate(rs.getDate(8));
                WarrantyForm form = new WarrantyForm();
                form.setFormId(rs.getInt(10));
                form.setVerified(rs.getString(16));
                form.setFaultType(rs.getString(17));
                form.setTechnicianVerify(rs.getString(18));
                wr.setForm(form);
                wr.setIsPay(rs.getString(9));
                wr.setInvoiceId(rs.getInt(21));
                list.add(wr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
