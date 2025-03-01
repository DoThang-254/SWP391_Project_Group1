/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author thang
 */
public class TransactionDao extends DBContext {

    PreparedStatement p;

    public void saveTransaction(int customerId, int invoiceId, long amount, String paymentMethod, String paymentStatus) {
        String sql = "INSERT INTO [Transaction] (CustomerId, InvoiceId, Amount, PaymentMethod, PaymentStatus, TransactionDate) VALUES (?, ?, ?, ?, ?, GETDATE())";

        try {
            p = connection.prepareStatement(sql);
            p.setInt(1, customerId);
            p.setInt(2, invoiceId);
            p.setLong(3, amount);
            p.setString(4, paymentMethod);
            p.setString(5, paymentStatus);
            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
