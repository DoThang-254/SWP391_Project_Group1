/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Model.Transaction;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thang
 */
public class TransactionDao extends DBContext {

    PreparedStatement p;
    ResultSet rs ;
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
    public static void main(String[] args) {
        TransactionDao t1 = new TransactionDao();
        for (Transaction t : t1.GetAllTransaction(1)) {
            System.out.println(t.getCustomerId());
        }
    }
    public List<Transaction> GetAllTransaction(int CustomerId) {
        String sql = "select * from [Transaction] where CustomerId = ?";
        List<Transaction> list = new ArrayList<>();
        try {
            p = connection.prepareStatement(sql);
            p.setInt(1, CustomerId);

            rs = p.executeQuery();
            if (rs.next()) {
                Transaction t = new Transaction();
                t.setTransactionId(rs.getInt(1));
                t.setCustomerId(rs.getInt(2));
                t.setInvoiceId(rs.getInt(3));
                t.setAmount(rs.getLong(4));
                t.setPaymentMethod(rs.getString(5));
                t.setPaymentStatus(rs.getString(6));
                t.setTransactionDate(rs.getDate(7));
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();  
        }
        return list;
    }

}
