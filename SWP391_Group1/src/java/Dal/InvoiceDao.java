/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Model.Customer;
import Model.Invoice;
import Model.Product;
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
public class InvoiceDao extends DBContext {

    private PreparedStatement p;
    private ResultSet rs;

    public Invoice getInvoiceByRequirementId(int requirementId) {
        String sql = "select i.* , p.CustomerId , p.ProductId from Product p join WarrantyRequirement wr on p.ProductId = wr.ProductId\n"
                + "join Invoice i on i.RequirementId = wr.RequirementId\n"
                + "where wr.RequirementId = ?";

        try {
            p = connection.prepareStatement(sql);
            p.setInt(1, requirementId);
            rs = p.executeQuery();
            if (rs.next()) {
                Invoice i = new Invoice();
                i.setInvoiceId(rs.getInt(1));
                WarrantyRequirement wr = new WarrantyRequirement();
                wr.setRequirementId(rs.getInt(2));
                Product p = new Product();
                p.setProductId(rs.getString(8));
                wr.setProduct(p);
                Customer c = new Customer();
                c.setCustomerId(rs.getInt(7));
                wr.setCustomer(c);
                i.setRequirement(wr);
                i.setPrice(rs.getLong(3));
                i.setStatus(rs.getString(4));
                i.setNote(rs.getString(5));
                i.setConfirmed(rs.getBoolean(6));
                return i ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Invoice> getInvoiceByCustomerId(int customerId) {
        List<Invoice> list = new ArrayList<>();
        String sql = "select i.* , p.CustomerId , p.ProductId from Product p join WarrantyRequirement wr on p.ProductId = wr.ProductId\n"
                + "join Invoice i on i.RequirementId = wr.RequirementId\n"
                + "where p.CustomerId = ? and i.Status = 'unpaid'";

        try {
            p = connection.prepareStatement(sql);
            p.setInt(1, customerId);
            rs = p.executeQuery();
            while (rs.next()) {
                Invoice i = new Invoice();
                i.setInvoiceId(rs.getInt(1));
                WarrantyRequirement wr = new WarrantyRequirement();
                wr.setRequirementId(rs.getInt(2));
                Product p = new Product();
                p.setProductId(rs.getString(8));
                wr.setProduct(p);
                Customer c = new Customer();
                c.setCustomerId(rs.getInt(7));
                wr.setCustomer(c);
                i.setRequirement(wr);
                i.setPrice(rs.getLong(3));
                i.setStatus(rs.getString(4));
                i.setNote(rs.getString(5));
                i.setConfirmed(rs.getBoolean(6));
                list.add(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void createInvoie(int requirementId) {
        String sql = "INSERT INTO [dbo].[Invoice]\n"
                + "           ([RequirementId]\n"
                + "           )\n"
                + "     VALUES\n"
                + "           (?)";

        try {
            p = connection.prepareStatement(sql);
            p.setInt(1, requirementId);

            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public Invoice getInvoiceByRequirementId(int requirementId) {
//        String sql = "  select * from Invoice where RequirementId = ?";
//
//        try {
//            p = connection.prepareStatement(sql);
//            p.setInt(1, requirementId);
//            rs = p.executeQuery();
//            if (rs.next()) {
//                Invoice i = new Invoice();
//                i.setInvoiceId(rs.getInt(1));
//                WarrantyRequirement wr = new WarrantyRequirement();
//                wr.setRequirementId(rs.getInt(2));
//                i.setRequirement(wr);
//                i.setPrice(rs.getLong(3));
//                i.setStatus(rs.getString(4));
//                i.setNote(rs.getString(5));
//
//                return i;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public void updateInvoie(long price, String note, int invoiceId) {
        String sql = "UPDATE [dbo].[Invoice]\n"
                + "   SET [Price] = ?\n"
                + "      \n"
                + "      ,[Note] = ?\n"
                + " WHERE InvoiceId = ? ";

        try {
            p = connection.prepareStatement(sql);
            p.setLong(1, price);
            p.setString(2, note);
            p.setInt(3, invoiceId);
            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStatusInvoie(int invoiceId) {
        String sql = "UPDATE [dbo].[Invoice]\n"
                + "   SET \n"
                + "    \n"
                + "      [Status] = 'Paid'\n"
                + "      \n"
                + "     \n"
                + " WHERE InvoiceId = ? ";

        try {
            p = connection.prepareStatement(sql);

            p.setInt(1, invoiceId);
            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateIsConfirmInvoice(int invoiceId) {
        try {

            String sql = "UPDATE Invoice SET IsConfirmed = 1 WHERE InvoiceId = ?";
            p = connection.prepareStatement(sql);
            p.setInt(1, invoiceId);
            return p.executeUpdate() > 0;

        } catch (SQLException e) {
        }
        return false;
    }

    public boolean checkIsConfirm(int invoiceId) {
        String sql = " select IsConfirmed from Invoice where invoiceId = ?";

        try {
            p = connection.prepareStatement(sql);
            p.setInt(1, invoiceId);
            rs = p.executeQuery();
            if (rs.next()) {

                return rs.getBoolean(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
}
