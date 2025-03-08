/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Customer;

import Dal.WarrantyFormDao;
import Dal.WarrantyProcessDao;
import dao.WarrantyRequirementDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author thang
 */
public class VerifyForm extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet VerifyForm</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VerifyForm at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    WarrantyProcessDao wpd = new WarrantyProcessDao();
    WarrantyRequirementDAO wrd = new WarrantyRequirementDAO();
    WarrantyFormDao wfd = new WarrantyFormDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int formId = Integer.parseInt(request.getParameter("formId"));
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        String productId = request.getParameter("productid");
        int requirementId = Integer.parseInt(request.getParameter("requirementid"));
        String staffId = request.getParameter("staffid");
        WarrantyFormDao wfd = new WarrantyFormDao();
        wfd.updateVerify(formId);
        if (wfd.isVerified(formId)) {

            wpd.insertWarrantyProcess(requirementId, staffId);
            //update request thành approved
            wrd.UpdateStatusRequest("Approved", requirementId);
        } else {
            wrd.UpdateStatusRequest("Rejected", requirementId);
            
        }
        response.sendRedirect("customerrequest");
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
//        String userEmail = request.getParameter("email"); // Lấy email người dùng nhập
//        String formId = request.getParameter("formId"); // Lấy ID hóa đơn
//        String customerId = request.getParameter("customerId");
//        String productId = request.getParameter("productId");
//        String requirementId = request.getParameter("requirementid");
//        String staffId = request.getParameter("staffid");
//        String confirmLink = "http://localhost:9999/SWP391_Group1/verifyform?formId=" + formId + "&customerId=" + customerId + "&productid=" + productId
//                + "&requirementid=" + requirementId + "&staffid=" + staffId;
//
//        // Cấu hình SMTP server (Gmail)
//        final String senderEmail = "thangditto2231977@gmail.com";  // Thay bằng email của bạn
//        final String senderPassword = "zkdq kzsm alyz ynaq";  // Thay bằng mật khẩu ứng dụng
//
//        Properties properties = new Properties();
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.host", "smtp.gmail.com");
//        properties.put("mail.smtp.port", "587");
//
//        Session session = Session.getInstance(properties, new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(senderEmail, senderPassword);
//            }
//        });
//
//        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(senderEmail));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
//            message.setSubject("Xác nhận Đồng ý điều khoản bảo hành");
//            message.setText("Nhấn vào link sau để xác nhận thanh toán: " + confirmLink);
//
//            Transport.send(message);
//
//            request.setAttribute("msg", "Email xác nhận đã được gửi!");
//            response.sendRedirect("customerrequest");
//        } catch (MessagingException e) {
//            throw new ServletException("Lỗi gửi email", e);
//        }
        String action = request.getParameter("action"); // Lấy hành động của form
        int formId = Integer.parseInt(request.getParameter("formId"));
        int requirementId = Integer.parseInt(request.getParameter("requirementid"));
        if ("confirm".equals(action)) {
            // Xác nhận qua Email
            String userEmail = request.getParameter("email");
            String customerId = request.getParameter("customerId");
            String productId = request.getParameter("productId");
            String staffId = request.getParameter("staffid");
            String confirmLink = "http://localhost:9999/SWP391_Group1/verifyform?formId=" + formId
                    + "&customerId=" + customerId + "&productid=" + productId
                    + "&requirementid=" + requirementId + "&staffid=" + staffId;

            // Gửi email xác nhận
            final String senderEmail = "thangditto2231977@gmail.com";
            final String senderPassword = "zkdq kzsm alyz ynaq";

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
                message.setSubject("Xác nhận Đồng ý điều khoản bảo hành");
                message.setText("Nhấn vào link sau để xác nhận: " + confirmLink);
                Transport.send(message);
                request.setAttribute("msg", "Email xác nhận đã được gửi!");
            } catch (MessagingException e) {
                throw new ServletException("Lỗi gửi email", e);
            }
        } else if ("reject".equals(action)) {
            // Hủy xác nhận, cập nhật trạng thái yêu cầu bảo hành thành "Rejected"
            wfd.updateUnverify(formId); // Cập nhật Verified = false (0)
            wrd.UpdateStatusRequest("Rejected", requirementId);
            
        }

        response.sendRedirect("customerrequest");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
