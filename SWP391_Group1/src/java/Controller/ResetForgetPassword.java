/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dal.TokenForgetDao;
import Model.Customer;
import Model.Staff;
import Model.TokenForgetPassword;
import Validation.ResetService;
import Validation.Validation;
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
public class ResetForgetPassword extends HttpServlet {

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
            out.println("<title>Servlet ResetForgetPassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResetForgetPassword at " + request.getContextPath() + "</h1>");
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
    ResetService r = new ResetService();
    TokenForgetDao tfd = new TokenForgetDao();
    Validation v = new Validation();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String token = request.getParameter("token");
        if (token != null) {
            TokenForgetPassword tokenForget = tfd.getTokenPassword(token);
            if (tokenForget == null) {
                request.setAttribute("mess", "token invalid");
                request.getRequestDispatcher("requestPassword.jsp").forward(request, response);
                return;
            }
            if (tokenForget.isIsUsed()) {
                request.setAttribute("mess", "token is used");
                request.getRequestDispatcher("requestPassword.jsp").forward(request, response);
                return;
            }
            if (r.isExpireTime(tokenForget.getExpiryTime())) {
                request.setAttribute("mess", "token is expiry time");
                request.getRequestDispatcher("requestPassword.jsp").forward(request, response);
                return;
            }

            Staff s = tfd.getEmailByStaffId(tokenForget.getStaffId());
            Customer c = tfd.getEmailById(tokenForget.getCustomerId());

            if (c != null) {
                request.setAttribute("email", c.getEmail());
            }
            if (s != null) {
                request.setAttribute("email", s.getEmail());
            }
            request.getSession().setAttribute("token", tokenForget.getToken());

            request.getRequestDispatcher("resetPassword.jsp").forward(request, response);
        } else {
            //khi user ko login
            request.getRequestDispatcher("requestPassword.jsp").forward(request, response);
        }
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
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");

        if (!password.equals(confirmPassword)) {
            request.setAttribute("mess", "confirm password must same password");
            request.setAttribute("email", email);
            request.getRequestDispatcher("resetPassword.jsp").forward(request, response);
            return;
        }
        if(!v.checkHashOfPassword(password)){
            request.setAttribute("mess", "password is weak");
            request.setAttribute("email", email);
            request.getRequestDispatcher("resetPassword.jsp").forward(request, response);
            return;
        }
        String tokenStr = (String) request.getSession().getAttribute("token");
        TokenForgetPassword tokenForgetPassword = tfd.getTokenPassword(tokenStr);
        if (tokenForgetPassword == null) {
            request.setAttribute("mess", "token invalid");
            request.getRequestDispatcher("requestPassword.jsp").forward(request, response);
            return;
        }
        if (tokenForgetPassword.isIsUsed()) {
            request.setAttribute("mess", "token is used");
            request.getRequestDispatcher("requestPassword.jsp").forward(request, response);
            return;
        }
        if (r.isExpireTime(tokenForgetPassword.getExpiryTime())) {
            request.setAttribute("mess", "token is expiry time");
            request.getRequestDispatcher("requestPassword.jsp").forward(request, response);
            return;
        }

        tokenForgetPassword.setToken(tokenStr);
        tokenForgetPassword.setIsUsed(true);
        Staff s = tfd.GetStaffByEmail(email);
        Customer c = tfd.GetCustomerByEmail(email);
        String hashPassword = v.encode(password);
        if (c != null) {
            tfd.updatePassword(c.getCustomerId(), hashPassword);
            tfd.updateStatus(tokenForgetPassword);
        }
        if (s != null) {
            tfd.updateStaffPassword(s.getStaffId(), hashPassword);
            tfd.updateStatus(tokenForgetPassword);
        }

        request.getRequestDispatcher("Login.jsp").forward(request, response);
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
