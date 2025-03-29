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

//    public boolean hasPendingRequest(String productId) {
//        String sql = "SELECT COUNT(*) FROM WarrantyRequirement WHERE ProductId = ? AND (Status = 'Pending' or Status = 'Uncheck' or Status = 'In Repair')";
//
//        try {
//            p = connection.prepareStatement(sql);
//
//            p.setString(1, productId);
//            rs = p.executeQuery();
//
//            if (rs.next()) {
//                return rs.getInt(1) > 0;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
    public boolean hasPendingRequest(String productId) {
        String sql = "SELECT COUNT(*) FROM WarrantyRequirement WHERE ProductId = ? AND Status <> 'Completed'";

        try {
            p = connection.prepareStatement(sql);
            p.setString(1, productId);
            rs = p.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // Nếu có bất kỳ yêu cầu nào chưa hoàn thành thì trả về true
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Nếu không có yêu cầu nào chưa hoàn thành thì trả về false
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
        String sql = "INSERT INTO WarrantyRequirement (ProductId, CustomerId, Status,Description ,RegisterDate , Ispay , [ImageUrl]) VALUES (?, ?, ?, ? , ? , ? , ?)";

        try {
            p = connection.prepareStatement(sql);
            p.setString(1, request.getProduct().getProductId());
            p.setInt(2, request.getCustomer().getCustomerId());
            p.setString(3, request.getStatus());
            p.setString(4, request.getDescription());
            p.setDate(5, new java.sql.Date(request.getRegisterDate().getTime()));
            p.setString(6, request.getIsPay());
            p.setString(7, request.getImg());
            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int GetTotalRequest(String search) {
        String sql = "SELECT count(*) FROM WarrantyRequirement wr join Customer c on wr.CustomerId = c.CustomerId";
        if (search != null && !search.trim().isEmpty()) {
            sql += " where c.FirstName like ? or c.LastName like ?";
        }
        try {
            p = connection.prepareStatement(sql);
            int index = 1;
            if (search != null && !search.trim().isEmpty()) {
                String searchPattern = "%" + search/*.replaceAll("\\s+", "")*/.trim() + "%";
                p.setString(index++, searchPattern);
                p.setString(index, searchPattern);
            }
            rs = p.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<WarrantyRequirement> GetAllRequest(int index, int amount, String search) {
        List<WarrantyRequirement> list = new ArrayList<>();
        String sql = "SELECT * FROM WarrantyRequirement wr join Customer c on wr.CustomerId = c.CustomerId\n";
        if (search != null && !search.trim().isEmpty()) {
            sql += " where c.FirstName like ? or c.LastName like ?";
        }
        sql += " order by wr.RequirementId\n"
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            p = connection.prepareStatement(sql);
            int param = 1;
            if (search != null && !search.trim().isEmpty()) {
                String searchPattern = "%" + search/*.replaceAll("\\s+", "")*/.trim() + "%";
                p.setString(param++, searchPattern);
                p.setString(param++, searchPattern);
            }
            p.setInt(param++, (index - 1) * amount);
            p.setInt(param, amount);
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
                c.setFirstName(rs.getString(14));
                c.setLastName(rs.getString(15));
                wr.setCustomer(c);
                wr.setStatus(rs.getString(5));
                wr.setDescription(rs.getString(6));
                wr.setImg(rs.getString(7));
                wr.setRegisterDate(rs.getDate(8));
                wr.setIsPay(rs.getString(9));
                list.add(wr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Staff ViewStaff(String staffId) {
        String sql = "select * from Staff where StaffId = ?";

        try {
            p = connection.prepareStatement(sql);
            p.setString(1, staffId);
            rs = p.executeQuery();

            while (rs.next()) {

                Staff s = new Staff();
                s.setStaffId(rs.getString(1));
                s.setFirstName(rs.getString(4));
                s.setLastName(rs.getString(5));
                s.setEmail(rs.getString(6));
                s.setPhone(rs.getString(7));
                s.setGender(rs.getString(8));
                s.setBirthDate(rs.getDate(9));
                return s;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
                wr.setImg(rs.getString(7));
                wr.setRegisterDate(rs.getDate(8));
                wr.setIsPay(rs.getString(9));
                WarrantyForm form = new WarrantyForm();
                form.setFormId(rs.getInt(10));
                form.setStatus(rs.getString(16));
                form.setFaultType(rs.getString(17));
                form.setTechnicianVerify(rs.getString(18));
                form.setImgUrl(rs.getString(19));
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
                wr.setImg(rs.getString(7));
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

    public void UpdateTechVerifyForm(int formId) {
        String sql = "UPDATE [dbo].[WarrantyForm]\n"
                + "   SET \n"
                + "      [TechnicianVerify] = 'yes'\n"
                + "     \n"
                + " WHERE FormId = ? ";

        try {
            p = connection.prepareStatement(sql);

            p.setInt(1, formId);
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

    public int GetTotalWarrantyRequest(int customerId, String faultType, String search) {
        String sql = " select count(*) from WarrantyRequirement wr join WarrantyForm wf on wr.FormId = wf.FormId join Product p on p.ProductId = wr.ProductId\n"
                + " join Customer c on c.CustomerId = wr.CustomerId join Invoice i on i.RequirementId = wr.RequirementId\n"
                + " where wr.CustomerId = ?";
        if (faultType != null && !faultType.trim().isEmpty()) {
            sql += " and FaultType = ?";
        }
        if (search != null && !search.trim().isEmpty()) {
            sql += " AND (p.ProductId LIKE ? or p.ProductName like ? )";
        }
        try {
            p = connection.prepareStatement(sql);
            p.setInt(1, customerId);
            int param = 2;
            if (faultType != null && !faultType.trim().isEmpty()) {
                p.setString(param++, faultType);
            }
            if (search != null && !search.trim().isEmpty()) {
                String searchPattern = "%" + search/*.replaceAll("\\s+", "")*/.trim() + "%";
                p.setString(param++, searchPattern);
                p.setString(param, searchPattern);
            }
            rs = p.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        WarrantyRequirementDAO d = new WarrantyRequirementDAO();
        for (WarrantyRequirement w : d.GetAllRequestByCustomerId(1, 1, 4, "", "")) {
            System.out.println(w.getInvoiceId());
        }
    }
    
    public List<WarrantyRequirement> GetAllRequestByCustomerId(int index, int customerId, int amount, String faultType, String search) {
        List<WarrantyRequirement> list = new ArrayList<>();
        String sql = " select wr.* , wf.* , c.Email , i.* , p.ProductName from WarrantyRequirement wr join WarrantyForm wf on wr.FormId = wf.FormId join Product p on p.ProductId = wr.ProductId\n"
                + "                 join Customer c on c.CustomerId = wr.CustomerId join Invoice i on i.RequirementId = wr.RequirementId\n"
                + "                 where wr.CustomerId = ?\n";
        if (faultType != null && !faultType.trim().isEmpty()) {
            sql += " and FaultType = ?";
        }
        if (search != null && !search.trim().isEmpty()) {
            sql += " AND (p.ProductId LIKE ? or p.ProductName like ? )";
        }
        sql += " order by wr.requirementId desc , wr.registerDate desc\n"
                + " offset ? rows fetch next ? rows only";

        try {
            p = connection.prepareStatement(sql);
            p.setInt(1, customerId);
            int param = 2;
            if (faultType != null && !faultType.trim().isEmpty()) {
                p.setString(param++, faultType);
            }
            if (search != null && !search.trim().isEmpty()) {
                String searchPattern = "%" + search/*.replaceAll("\\s+", "")*/.trim() + "%";
                p.setString(param++, searchPattern);
                p.setString(param++, searchPattern);
            }
            p.setInt(param++, (index - 1) * amount);
            p.setInt(param, amount);

            rs = p.executeQuery();

            while (rs.next()) {
                WarrantyRequirement wr = new WarrantyRequirement();
                wr.setRequirementId(rs.getInt(1));
                Product p = new Product();
                p.setProductId(rs.getString(2));
                p.setProductName(rs.getString(26));
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
                form.setFaultType(rs.getString(16));
                form.setTechnicianVerify(rs.getString(17));
                form.setStatus(rs.getString(15));
                wr.setForm(form);
                wr.setIsPay(rs.getString(9));
                wr.setInvoiceId(rs.getInt(21));
                wr.setInvoiceStatus(rs.getString(23));

                list.add(wr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    

    public int GetTotalHistoryWarrantyRequest(int customerId, String status, String search) {
        String sql = "select count(*) from WarrantyRequirement wr join Product p on p.ProductId = wr.ProductId\n"
                + "join Customer c on c.CustomerId = wr.CustomerId where wr.CustomerId = ?";
        if (status != null && !status.trim().isEmpty()) {
            sql += " and wr.[Status] = ?";
        }

        if (search != null && !search.trim().isEmpty()) {
            sql += " AND (p.ProductId LIKE ? or p.ProductName like ? )";
        }
        try {
            p = connection.prepareStatement(sql);
            p.setInt(1, customerId);
            int index = 2;

            if (status != null && !status.trim().isEmpty()) {
                p.setString(index++, status);
            }
            if (search != null && !search.trim().isEmpty()) {
                String searchPattern = "%" + search.trim() + "%";
                p.setString(index++, searchPattern);
                p.setString(index, searchPattern);
            }
            rs = p.executeQuery();

            if (rs.next()) {

                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<WarrantyRequirement> historyRequestByCustomerId(int index, int customerId, int amount, String status, String search) {
        List<WarrantyRequirement> list = new ArrayList<>();
        String sql = " select wr.* , c.Email , p.ProductName from WarrantyRequirement wr join Product p on p.ProductId = wr.ProductId\n"
                + "                 join Customer c on c.CustomerId = wr.CustomerId\n"
                + "                 where wr.CustomerId = ?\n";
        if (status != null && !status.trim().isEmpty()) {
            sql += " and wr.[Status] = ?";
        }
        if (search != null && !search.trim().isEmpty()) {
            sql += " AND (p.ProductId LIKE ? or p.ProductName like ? )";
        }
        sql += " order by wr.requirementId desc , wr.registerDate desc\n"
                + " offset ? rows fetch next ? rows only";

        try {
            p = connection.prepareStatement(sql);
            p.setInt(1, customerId);
            int x = 2;
            if (status != null && !status.trim().isEmpty()) {
                p.setString(x++, status);

            }
            if (search != null && !search.trim().isEmpty()) {
                String searchPattern = "%" + search.trim() + "%";
                p.setString(x++, searchPattern);
                p.setString(x++, searchPattern);
            }
            p.setInt(x++, (index - 1) * amount);
            p.setInt(x, amount);

            rs = p.executeQuery();

            while (rs.next()) {
                WarrantyRequirement wr = new WarrantyRequirement();
                wr.setRequirementId(rs.getInt(1));
                Product p = new Product();
                p.setProductId(rs.getString(2));
                p.setProductName(rs.getString(12));
                wr.setProduct(p);
                Staff s = new Staff();
                s.setStaffId(rs.getString(4));
                wr.setStaff(s);
                wr.setStatus(rs.getString(5));
                wr.setDescription(rs.getString(6));
                Customer cust = new Customer();
                cust.setCustomerId(rs.getInt(3));
                cust.setEmail(rs.getString(11));
                wr.setCustomer(cust);
                wr.setRegisterDate(rs.getDate(8));
                wr.setIsPay(rs.getString(9));

                list.add(wr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
