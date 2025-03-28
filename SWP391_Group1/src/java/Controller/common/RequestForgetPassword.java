/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.common;

import Dal.TokenForgetDao;
import Model.Customer;
import Model.Staff;
import Model.TokenForgetPassword;
import Utils.ResetService;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author thang
 */
public class RequestForgetPassword extends HttpServlet {

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
            out.println("<title>Servlet RequestForgetPassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RequestForgetPassword at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("requestPassword.jsp").forward(request, response);
    }

    ResetService r = new ResetService();
    TokenForgetDao tfd = new TokenForgetDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        Staff s = tfd.GetStaffByEmail(email);
        Customer c = tfd.GetCustomerByEmail(email);

        if (c == null && s == null) {
            request.setAttribute("mess", "email is not existed");
            request.getRequestDispatcher("requestPassword.jsp").forward(request, response);
            return;
        }
        if (c != null) {
            String token = r.generateToken();
            String linkReset = "http://localhost:9999/SWP391_Group1/resetforget?token=" + token;

            TokenForgetPassword tokenforget = new TokenForgetPassword(c.getCustomerId(),
                    false, token, r.expireDateTime());
            boolean isInsert = tfd.insertTokenForget(tokenforget);
            if (!isInsert) {
                request.setAttribute("mess", "have error in server");
                request.getRequestDispatcher("requestPassword.jsp").forward(request, response);
                return;
            }
            boolean isSendEmail = r.sendEmail(email, linkReset, c.getUsername());
            if (!isSendEmail) {
                request.setAttribute("mess", "can not send request");
                request.getRequestDispatcher("requestPassword.jsp").forward(request, response);
                return;
            }
            request.setAttribute("mess", "send request success");
            request.getRequestDispatcher("requestPassword.jsp").forward(request, response);
        }
        if (s != null) {
            String token = r.generateToken();
            String linkReset = "http://localhost:9999/SWP391_Group1/resetforget?token=" + token;
            TokenForgetPassword tokenforget = new TokenForgetPassword(s.getStaffId(), false, token, r.expireDateTime());
            boolean isInsert = tfd.insertStaffTokenForget(tokenforget);
            if (!isInsert) {
                request.setAttribute("mess", "have error in server");
                request.getRequestDispatcher("requestPassword.jsp").forward(request, response);
                return;
            }
            boolean isSendEmail = r.sendEmail(email, linkReset, s.getUsername());
            if (!isSendEmail) {
                request.setAttribute("mess", "can not send request");
                request.getRequestDispatcher("requestPassword.jsp").forward(request, response);
                return;
            }
            request.setAttribute("mess", "send request success");
            request.getRequestDispatcher("requestPassword.jsp").forward(request, response);
        }
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
