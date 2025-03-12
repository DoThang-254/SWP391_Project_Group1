/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Technician;

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
public class TechnicianVerifyForm extends HttpServlet {

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
            out.println("<title>Servlet TechnicianVerifyForm</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TechnicianVerifyForm at " + request.getContextPath() + "</h1>");
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int formId = Integer.parseInt(request.getParameter("formId"));
        int requirementId = Integer.parseInt(request.getParameter("requirementid"));
        String staffId = request.getParameter("staffid");
        wfd.updateTechVerify(formId);

        if (wfd.isTechVerified(formId)) {
            if (!wpd.isWarrantyProcessExists(requirementId)) { // Nếu chưa tồn tại, mới insert
                wpd.insertWarrantyProcess(requirementId, staffId);
                wrd.UpdateStatusRequest("Checked", requirementId);
            } else {
                System.out.println("Yêu cầu này đã được xử lý!");
            }
        } else if (!wfd.isTechVerified(formId)) {
            wrd.UpdateStatusRequest("Reject", requirementId);
        }

        response.sendRedirect("technicianrequest");
        return;
    }

    /**
     * Handles the HTTP <code>POST</code> method.
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action"); // Lấy hành động của form
        int formId = Integer.parseInt(request.getParameter("formId"));
        int requirementId = Integer.parseInt(request.getParameter("requirementid"));
        if ("confirm".equals(action)) {

            String staffId = request.getParameter("staffid");

            response.sendRedirect("techverify?formId=" + formId + "&requirementid=" + requirementId + "&staffid=" + staffId);
            return;
        } else if ("reject".equals(action)) {
            wfd.updateTechUnverify(formId); // Cập nhật Verified = false (0)
            wrd.UpdateStatusRequest("Rejected", requirementId);
            return;
        }

        response.sendRedirect("technicianrequest");
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
