/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.common;

import Dal.InvoiceDao;
import Model.Customer;
import Model.Invoice;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author thang
 */
public class SendConfirmController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String customerIdRaw = request.getParameter("customerId");

        int customerId = Integer.parseInt(customerIdRaw);
        String invoiceIdRaw = request.getParameter("invoiceId");
        if (invoiceIdRaw == null) {
            response.sendRedirect("home");
            return;
        }

        int invoiceId = Integer.parseInt(invoiceIdRaw);

        InvoiceDao ivd = new InvoiceDao();
        ivd.updateIsConfirmInvoice(invoiceId);
        int requirementId = Integer.parseInt(request.getParameter("requirementId"));

//        List<Invoice> invoices = ivd.getInvoiceByCustomerId(customerId);

//        for (Invoice invoice : invoices) {
//            boolean isConfirmed = ivd.checkIsConfirm(invoice.getInvoiceId());
//            invoice.setConfirmed(isConfirmed); 
//        }
//
//        request.setAttribute("list", invoices);
        response.sendRedirect("payment?requirementId=" + requirementId);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
        
        String customerIdRaw = request.getParameter("customerId");
        int requirementId = Integer.parseInt(request.getParameter("requirementId"));
        int customerId = Integer.parseInt(customerIdRaw);

        String userEmail = request.getParameter("email"); // Lấy email người dùng nhập
        String invoiceId = request.getParameter("invoiceId"); // Lấy ID hóa đơn
        String confirmLink = "http://localhost:9999/SWP391_Group1/sendconfirm?invoiceId=" + invoiceId + "&customerId=" + customerId +"&requirementId="
                + requirementId;

        // Cấu hình SMTP server (Gmail)
        final String senderEmail = "thangditto2231977@gmail.com";  // Thay bằng email của bạn
        final String senderPassword = "zkdq kzsm alyz ynaq";  // Thay bằng mật khẩu ứng dụng

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
            message.setSubject("Xác nhận thanh toán VNPay");
            message.setText("Nhấn vào link sau để xác nhận thanh toán: " + confirmLink);

            Transport.send(message);
            request.setAttribute("msg", "Email xác nhận đã được gửi!");
            response.sendRedirect("payment?requirementId=" + requirementId);
        } catch (MessagingException e) {
            throw new ServletException("Lỗi gửi email", e);
        }
        } catch (Exception e) {
            response.sendRedirect("404.jsp");
        }
    }
}
