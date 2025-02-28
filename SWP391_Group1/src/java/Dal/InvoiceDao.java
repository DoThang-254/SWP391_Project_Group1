/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Model.Invoice;
import Model.WarrantyRequirement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author thang
 */
public class InvoiceDao extends DBContext {

    private PreparedStatement p;
    private ResultSet rs;

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

    public Invoice getInvoiceByRequirementId(int requirementId) {
        String sql = "  select * from Invoice where RequirementId = ?";

        try {
            p = connection.prepareStatement(sql);
            p.setInt(1, requirementId);
            rs = p.executeQuery();
            if (rs.next()) {
                Invoice i = new Invoice();
                i.setInvoiceId(rs.getInt(1));
                WarrantyRequirement wr = new WarrantyRequirement();
                wr.setRequirementId(rs.getInt(2));
                i.setRequirement(wr);
                i.setPrice(rs.getLong(3));
                i.setStatus(rs.getString(4));
                i.setNote(rs.getString(5));
                return i;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateInvoie(long price , String note , int invoiceId) {
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
}
